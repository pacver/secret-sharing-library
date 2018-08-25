package SSLibrary;

import ffapl.java.classes.BInteger;
import ffapl.java.classes.PolynomialRC;
import ffapl.java.classes.RNG_Placebo;
import ffapl.java.exception.FFaplAlgebraicException;
import ffaplExtended.java.math.AlgorithmExtended;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static ffaplExtended.java.math.AlgorithmExtended.getPolynomialValues;
import static ffaplExtended.java.math.AlgorithmExtended.shareIsInvalid;

public class Participant implements Serializable{

    private final HashMap<String, BigInteger> _localSecretShare;
    private final HashMap<String, BigInteger> _localRndShare;
    private final HashMap<BigInteger, BigInteger> _othersSecretShares;
    private final HashMap<BigInteger, BigInteger> _othersRndShares;
    private BigInteger _secretId;
    private boolean _isPlayer;
    private boolean _isCombiner;
    private HashMap<BigInteger, BigInteger> _commitments;
    private SessionInfo _currentSessionInfo;
    private List<BigInteger> _supportOrdinates;
    private Operation _currentOperation;
    private MultipartyOperation _currentMultipartyOperation;

    /**
     * Constructor for a new participants
     */
    public Participant(boolean _isCombiner) {
        this._isPlayer = true;
        this._isCombiner = _isCombiner;
        _localSecretShare = new HashMap<>();
        _localRndShare = new HashMap<>();
        _commitments = new HashMap<>();
        _othersSecretShares = new HashMap<>();
        _othersRndShares = new HashMap<>();
    }

    public BigInteger getSecretId() {
        return _secretId;
    }

    public boolean IsCombiner() {
        return _isCombiner;
    }

    public boolean IsPlayer() {
        return _isPlayer;
    }

    /**
     * This method adds a secret share and a random share to the local shares of a participant.
     *
     * @throws SSLibException This method call is only valid, when there is a current session of type None that the participant joins.
     */
    public void addSimpleLocalShare(MultipartyOperationObject multipartyOperationObject) throws SSLibException {
        if (!_isPlayer)
            throw new SSLibException(SSLibExceptionType.UnauthorizedPlayerAction);

        if (_currentSessionInfo == null)
            throw new SSLibException(SSLibExceptionType.NoActiveSession);

        if (multipartyOperationObject.getShareType() != Operation.None) {
            return;
        }
        _currentOperation = multipartyOperationObject.getShareType();
        _currentMultipartyOperation = multipartyOperationObject.getMultipartyOperation();

        _localSecretShare.put(_currentMultipartyOperation.getOutputKey(), multipartyOperationObject.getSecret1());
        _localRndShare.put(_currentMultipartyOperation.getOutputKey(), multipartyOperationObject.getRandom1());

    }

    public void addSessionInfo(BigInteger secretId, SessionInfo sessionInfo) {
        _secretId = secretId;
        _currentSessionInfo = sessionInfo;
    }

    /**
     * This method adds two secret shares and two random shares to the local shares of a participant.
     *
     * @param share Object with secret share of first secret, of second secret, random share of first secret and random share of second secret
     * @throws SSLibException This method call is only valid when the participant currently joins a session of type Sum as a player.
     */
    public void addLocalSharesOfSums(MultipartyOperationObject share) throws SSLibException {
        if (_currentSessionInfo == null)
            throw new SSLibException(SSLibExceptionType.NoActiveSession);

        if (!_isPlayer)
            throw new SSLibException(SSLibExceptionType.UnauthorizedPlayerAction);

        if (share.getShareType() != Operation.Sum) {
            return;
        }
        _currentOperation = share.getShareType();
        _currentMultipartyOperation = share.getMultipartyOperation();

        BigInteger secret1 = GetLocalShareValue(share.getSecret1(), _currentMultipartyOperation.getInputKey1());
        BigInteger secret2 = GetLocalShareValue(share.getSecret2(), _currentMultipartyOperation.getInputKey2());

        _localSecretShare.put(_currentMultipartyOperation.getInputKey1(), secret1);

        _localSecretShare.put(_currentMultipartyOperation.getInputKey2(), secret2);

        if (share.getRandom1() != null)
            _localRndShare.put(_currentMultipartyOperation.getInputKey1(), share.getRandom1());

        if (share.getRandom2() != null)
            _localRndShare.put(_currentMultipartyOperation.getInputKey2(), share.getRandom2());
    }

    /**
     * This method adds two secret shares to the local shares of a participant and prepares the shares for other participants.
     *
     * @param share Object with local secret share of first secret and local secret share of second secret
     * @throws SSLibException          This method call is only valid when the participant currently joins a session of type Product as a player.
     * @throws FFaplAlgebraicException This method throws an exception of type FFaplAlgebraicException, when the calculation of the shares for other participants fails.
     */
    public void addLocalSharesOfProducts(MultipartyOperationObject share) throws FFaplAlgebraicException, SSLibException {
        if (_currentSessionInfo == null)
            throw new SSLibException(SSLibExceptionType.NoActiveSession);

        if (!_isPlayer)
            throw new SSLibException(SSLibExceptionType.UnauthorizedPlayerAction);

        if (share.getShareType() != Operation.Product)
            return;

        _currentOperation = share.getShareType();
        _currentMultipartyOperation = share.getMultipartyOperation();

        BigInteger secret1 = GetLocalShareValue(share.getSecret1(), _currentMultipartyOperation.getInputKey1());
        BigInteger secret2 = GetLocalShareValue(share.getSecret2(), _currentMultipartyOperation.getInputKey2());

        BInteger mul = new BInteger(secret1, _currentSessionInfo.getThread());
        mul = (BInteger) mul.multiply(secret2);
        mul = (BInteger) mul.mod(_currentSessionInfo.getModulo());

        computeSupportOrdinates(mul);

        _localSecretShare.put(_currentMultipartyOperation.getOutputKey(), _supportOrdinates.get(_secretId.intValue()));
    }

    private BigInteger GetLocalShareValue(BigInteger secret, String inputKey) throws SSLibException {
        if (secret != null)
            return secret;

        else if (!_localSecretShare.containsKey(inputKey))
            throw new SSLibException(SSLibExceptionType.LocalSharesMissing);

        else
            return _localSecretShare.get(inputKey);
    }

    /**
     * This method adds a commitment values of all players to the internal commitment list of the participant.
     *
     * @param commitments List of all commitment values broadcasted by the dealer
     * @throws SSLibException This method throws an error when the participant is no player, does currently join no None or Sum session or
     *                        the local share received from the dealer is invalid.
     */
    public void addCommitmentShares(HashMap<BigInteger, BigInteger> commitments) throws SSLibException {

        if (!_isPlayer)
            throw new SSLibException(SSLibExceptionType.UnauthorizedPlayerAction);

        if (_currentSessionInfo == null)
            throw new SSLibException(SSLibExceptionType.NoActiveSession);

        if (_localSecretShare.size() == 0)
            throw new SSLibException(SSLibExceptionType.LocalSharesMissing);

        _commitments = commitments;

        boolean localSharesValid = true;
        BigInteger modulo = _currentSessionInfo.getModulo();

        switch (_currentOperation) {
            case None:
                if (shareIsInvalid(commitments.get(_secretId), _localSecretShare.get(_currentMultipartyOperation.getInputKey1()), _localRndShare.get(_currentMultipartyOperation.getInputKey1()), _currentSessionInfo.getCommitmentTriple(), _currentSessionInfo.getCommitmentType()))
                    localSharesValid = false;
                break;
            case Sum:
                BigInteger secretSum = _localSecretShare.get(_currentMultipartyOperation.getInputKey1()).add(_localSecretShare.get(_currentMultipartyOperation.getInputKey2())).mod(modulo);

                if (_localRndShare.get(_currentMultipartyOperation.getInputKey1()) == null || _localRndShare.get(_currentMultipartyOperation.getInputKey2()) == null) {
                    _localSecretShare.put(_currentMultipartyOperation.getOutputKey(), secretSum);
                    localSharesValid = true;
                } else {
                    BigInteger rndSum = _localRndShare.get(_currentMultipartyOperation.getInputKey1()).add(_localRndShare.get(_currentMultipartyOperation.getInputKey2())).mod(modulo);

                    localSharesValid = replaceShares(commitments, secretSum, rndSum);
                }
                break;
        }

        if (!localSharesValid)
            throw new SSLibException(SSLibExceptionType.ShareOfTheDealerInvalid);
    }

    /**
     * With this method the participant receives shares from other participants.
     *
     * @param id          Id of the sender participant that gives the share to this participant
     * @param secretShare secret share of the sender participant
     * @param rndShare    random share of the sender participant
     * @throws SSLibException This method is only valid when a player participant currently joins a None or Sum session. Further, the received share must match the commitment value.
     */
    public void receiveShare(BigInteger id, BigInteger secretShare, BigInteger rndShare) throws SSLibException {

        if (!_isPlayer)
            throw new SSLibException(SSLibExceptionType.UnauthorizedPlayerAction);

        if (_currentSessionInfo == null)
            throw new SSLibException(SSLibExceptionType.NoActiveSession);

        if (_currentOperation == Operation.Product)
            throw new SSLibException(SSLibExceptionType.WrongSessionType);

        if (rndShare != null && shareIsInvalid(_commitments.get(id), secretShare, rndShare, _currentSessionInfo.getCommitmentTriple(), _currentSessionInfo.getCommitmentType()))
            throw new SSLibException(SSLibExceptionType.ShareOfParticipantInvalid);

        _othersSecretShares.put(id, secretShare);
        _othersRndShares.put(id, rndShare);
    }

    /**
     * This method adds a secretShare of another participant to the shares of this participant
     *
     * @param id          Id of the sender participant
     * @param secretShare Local secret share of the sender participant
     * @throws SSLibException This method call is only valid for a player participant that currently joins a Product session
     */
    public void receiveShare(BigInteger id, BigInteger secretShare) throws SSLibException {

        if (!_isPlayer)
            throw new SSLibException(SSLibExceptionType.UnauthorizedPlayerAction);

        if (_currentSessionInfo == null)
            throw new SSLibException(SSLibExceptionType.NoActiveSession);

        if (_currentOperation != Operation.Product)
            throw new SSLibException(SSLibExceptionType.WrongSessionType);

        _othersSecretShares.put(id, secretShare);
    }


    /**
     * This method enables a participant to reconstruct the shared secret
     *
     * @return The method returns the reconstructed secret
     * @throws FFaplAlgebraicException This method throws an exception of type FFaplAlgebraicException, when the secret reconstruction calculation fails
     * @throws SSLibException          The method call is only valid for combiners that currently join a session and received valid shares from all participants.
     */
    public BigInteger reconstructFinalResult() throws FFaplAlgebraicException, SSLibException {

        if (!_isCombiner)
            throw new SSLibException(SSLibExceptionType.UnauthorizedCombinerAction);

        if (_currentSessionInfo == null)
            throw new SSLibException(SSLibExceptionType.NoActiveSession);

        if (this.numberOfValidShares() < _currentSessionInfo.getNumberOfCombiners() - 1)
            throw new SSLibException(SSLibExceptionType.TooFewValidValueSets);

        if (_currentOperation == Operation.Product)
            return calculateFinalResultMul();

        return calculateFinalResult();
    }


    /**
     * This method is only relevant for a Product session. This method calculates the share of the final polynomial for a participant.
     * This method must be called after the participant received his support ordinates from all players
     *
     * @throws SSLibException This method throws an exception when it is called by a participant that is not a combiner or that currently does not join a Product session.
     *                        Further, an exception is thrown when the participant did not receive all shares of the other participants an his local shares beforehand.
     */
    public void calculateReducedPolynomial() throws SSLibException {

        if (_currentSessionInfo == null)
            throw new SSLibException(SSLibExceptionType.NoActiveSession);

        if (_currentOperation != Operation.Product)
            throw new SSLibException(SSLibExceptionType.WrongSessionType);

        if (_localSecretShare.size() == 0)
            throw new SSLibException(SSLibExceptionType.LocalSharesMissing);

        if (_othersSecretShares.size() < _currentSessionInfo.getNumberOfPlayers() - 1)
            throw new SSLibException(SSLibExceptionType.TooFewValidValueSets);

        HashMap<BigInteger, BigInteger> secretShares = new HashMap<>();
        CopyOnWriteArrayList<BigInteger> xValues = _currentSessionInfo.getPlayerIds();

        for (BigInteger xValue : xValues) {
            if (_othersSecretShares.containsKey(xValue))
                secretShares.put(xValue, _othersSecretShares.get(xValue));

            if (this.getSecretId().equals(xValue))
                secretShares.put(xValue, _localSecretShare.get(_currentMultipartyOperation.getOutputKey()));
        }

        BigInteger Hj;

        Integer k = _currentSessionInfo.getModulo().bitCount();

        if (_currentSessionInfo.getNumberOfPlayers() >= k) {
            Hj = getHjGennaroVersion(secretShares);
        } else {
            Hj = getHjLoryVersion(secretShares);
        }

        _localSecretShare.put(_currentMultipartyOperation.getOutputKey(), Hj);
        _othersSecretShares.clear();
        _supportOrdinates.clear();
    }

    private BigInteger getHjGennaroVersion(HashMap<BigInteger, BigInteger> secretShares) {
        BInteger lambda;
        BigInteger temp;
        BigInteger hj = BigInteger.ZERO;

        CopyOnWriteArrayList<BigInteger> xValues = _currentSessionInfo.getPlayerIds();
        for (BigInteger value : xValues) {
            lambda = getLambdaByLagrangeInterpolation(value);
            temp = secretShares.get(value);
            temp = lambda.multiply(temp);
            hj = hj.add(temp).mod(_currentSessionInfo.getModulo());
        }
        return hj;
    }

    private BigInteger getHjLoryVersion(HashMap<BigInteger, BigInteger> secretShares) {

        BigInteger temp;
        List<BigInteger> g = new ArrayList<>();
        List<BigInteger> x = new ArrayList<>();
        List<BigInteger> simpleList = new ArrayList<>();

        for (Map.Entry<BigInteger, BigInteger> share : secretShares.entrySet()) {
            simpleList.add(share.getValue());
        }


        for (int i = 0; i < _currentSessionInfo.getPlayerIds().size(); i++) {
            g.add(i, simpleList.get(i));

            if (i > 0) {
                for (int k = i - 1; k >= 0; k--) {
                    temp = g.get(k + 1).subtract(g.get(k)).mod(_currentSessionInfo.getModulo());
                    g.set(k, temp);
                }
            }
            x.add(i, g.get(0));
        }

        for (int k = secretShares.size() - 2; k >= 0; k--) {
            temp = x.get(k).subtract(x.get(k + 1)).mod(_currentSessionInfo.getModulo());
            x.set(k, temp);
        }

        return x.get(0).mod(_currentSessionInfo.getModulo());
    }

    /**
     * This method is only relevant for the sharing of secret shares with other participants.
     * With this method a participant gives the share of his local secret share to other participants.
     *
     * @return This method returns the share for a participant.
     * @throws SSLibException This method throws an exception when it is called by a participant that is no player or does currently not join a session.
     *                        Additionally, an exception is thrown, when the player has not his local share via addSimpleLocalShares, addLocalSharesOfSums or addLocalSharesOfProducts().
     */
    public BigInteger distributeSecretShare() throws SSLibException {
        if (!this._isPlayer)
            throw new SSLibException(SSLibExceptionType.UnauthorizedPlayerAction);

        if (this._currentSessionInfo == null)
            throw new SSLibException(SSLibExceptionType.NoActiveSession);

        if (this._localSecretShare.size() == 0)
            throw new SSLibException(SSLibExceptionType.LocalSharesMissing);

        if (_currentOperation == Operation.Product && this._supportOrdinates.size() != 0) {

            throw new SSLibException(SSLibExceptionType.WrongDistributionMethod);
        }

        return _localSecretShare.get(_currentMultipartyOperation.getOutputKey());
    }

    /**
     * This method is only relevant for the sharing of secret shares with other participants when a specific share for a
     * given participant Id should be returned.
     * With this method a participant gives the share of his local secret share to other participants.
     *
     * @param participantId Id of the participant that should receive his local share of this participants share.
     * @return This method returns the share for a participant with a given Id.
     * @throws SSLibException This method throws an exception when it is called by a participant that is no player or does currently not join a session.
     *                        Additionally, an exception is thrown, when the player has not his local share via addSimpleLocalShares, addLocalSharesOfSums or addLocalSharesOfProducts().
     */
    public BigInteger distributeSecretShareForSpecificParticipant(BigInteger participantId) throws SSLibException {

        if (!this._isPlayer)
            throw new SSLibException(SSLibExceptionType.UnauthorizedPlayerAction);

        if (this._currentSessionInfo == null)
            throw new SSLibException(SSLibExceptionType.NoActiveSession);

        if (this._localSecretShare.size() == 0)
            throw new SSLibException(SSLibExceptionType.LocalSharesMissing);

        if (_currentOperation != Operation.Product) {
            throw new SSLibException(SSLibExceptionType.WrongSessionType);
        }

        if (this._supportOrdinates.size() == 0) {

            throw new SSLibException(SSLibExceptionType.LocalSharesMissing);
        }

        return _supportOrdinates.get(participantId.intValue());
    }

    /**
     * This method returns the local random share of a participant. This is only needed for Sum or None sessions.
     *
     * @return Returns the local random secret share of the participant.
     * @throws SSLibException This method throws an exception when it is called by a participant that is not a player or is not part of a None or Sum session.
     *                        Additionally, the participant that should distribute his local random share must have received it before via addSimpleLocalShares() or addLocalSharesOfSums().
     */
    public BigInteger distributeRndShare() throws SSLibException {

        if (!this._isPlayer)
            throw new SSLibException(SSLibExceptionType.UnauthorizedPlayerAction);

        if (this._currentSessionInfo == null)
            throw new SSLibException(SSLibExceptionType.NoActiveSession);

        if (_currentOperation != Operation.None && _currentOperation != Operation.Sum)
            throw new SSLibException(SSLibExceptionType.WrongSessionType);

        /*if (this._localRndShare.size() == 0)
            throw new SSLibException(SSLibExceptionType.LocalSharesMissing);*/

        return _localRndShare.get(_currentMultipartyOperation.getOutputKey());
    }

    private void computeSupportOrdinates(BigInteger mulResult) throws FFaplAlgebraicException {

        RNG_Placebo randomNumber = new RNG_Placebo(BigInteger.ZERO, _currentSessionInfo.getModulo(), _currentSessionInfo.getThread());
        ArrayList<BigInteger> g = new ArrayList<>();
        _supportOrdinates = new ArrayList<>();
        int t = _currentSessionInfo.getNumberOfCombiners() - 1;
        int n = _currentSessionInfo.getNumberOfPlayers();

        _supportOrdinates.add(0, mulResult);

        for (int i = 1; i <= t; i++) {
            _supportOrdinates.add(i, randomNumber.next());
        }

        g.add(0, _supportOrdinates.get(0));

        for (int j = 1; j <= t; j++) {
            g.add(j, _supportOrdinates.get(j));

            for (int k = j - 1; k >= 0; k--) {
                BInteger value = new BInteger(g.get(k + 1).subtract(g.get(k)), _currentSessionInfo.getThread());
                g.set(k, value.mod(_currentSessionInfo.getModulo()));
            }
        }

        for (int j = t + 1; j <= n; j++) {
            for (int k = 0; k <= t - 1; k++) {
                BInteger value = new BInteger(g.get(k + 1).add(g.get(k)), _currentSessionInfo.getThread());
                g.add(k + 1, value.mod(_currentSessionInfo.getModulo()));
            }
            _supportOrdinates.add(j, g.get(t));
        }
    }

    private BInteger getLambdaByLagrangeInterpolation(BigInteger i) {
        BInteger lambda = new BInteger(BigInteger.ONE, _currentSessionInfo.getThread());
        BigInteger temp;
        BigInteger modulo = _currentSessionInfo.getModulo();
        CopyOnWriteArrayList<BigInteger> kValues = _currentSessionInfo.getPlayerIds();
        Collections.sort(kValues);

        for (BigInteger kValue : kValues) {

            if (kValue.intValue() != i.intValue()) {
                temp = kValue.multiply(kValue.subtract(i).modInverse(modulo)).mod(modulo);
                lambda = (BInteger) lambda.multiply(temp).mod(modulo);
            }
        }

        return lambda;
    }

    private HashMap<BigInteger, BigInteger> getKeyValuePairs(BigInteger localShare, HashMap<BigInteger, BigInteger> othersShares) {
        HashMap<BigInteger, BigInteger> values = new HashMap<>();
        values.put(_secretId, localShare);

        for (Map.Entry<BigInteger, BigInteger> share : othersShares.entrySet()) {
            values.put(share.getKey(), share.getValue());
        }

        return values;
    }

    private PolynomialRC reconstructWithLagrange() throws FFaplAlgebraicException {
        BInteger modulo = new BInteger(_currentSessionInfo.getModulo(), _currentSessionInfo.getThread());

        HashMap<BigInteger, BigInteger> secretShares = getKeyValuePairs(_localSecretShare.get(_currentMultipartyOperation.getOutputKey()), _othersSecretShares);
        HashMap<BigInteger, BigInteger> rndShares = getKeyValuePairs(_localRndShare.get(_currentMultipartyOperation.getOutputKey()), _othersRndShares);

        PolynomialRC secretPoly = AlgorithmExtended.getPolynomialByValues(modulo, secretShares);

        if (!rndShares.containsValue(null)) {
            PolynomialRC rndPoly = AlgorithmExtended.getPolynomialByValues(modulo, rndShares);

            List<BigInteger> xValues = new ArrayList<>(secretShares.keySet());

            HashMap<BigInteger, BigInteger> commitments_new = AlgorithmExtended.getPolynomialCommitmentValues(secretPoly, rndPoly, modulo, xValues, _currentSessionInfo.getCommitmentTriple(), _currentSessionInfo.getCommitmentType());

            boolean commitmentsValid = true;
            for (Map.Entry<BigInteger, BigInteger> commitment : commitments_new.entrySet()) {

                BigInteger x = commitment.getKey();

                BigInteger c_orig = _commitments.get(x);
                BigInteger c_new = commitment.getValue();
                if (!c_orig.equals(c_new))
                    commitmentsValid = false;
            }

            if (!commitmentsValid)
                return null;
        }

        return secretPoly;
    }

    private BigInteger calculateFinalResult() throws FFaplAlgebraicException, SSLibException {
        BInteger modulo = new BInteger(_currentSessionInfo.getModulo(), _currentSessionInfo.getThread());
        PolynomialRC secretPoly = reconstructWithLagrange();
        if (secretPoly == null)
            throw new SSLibException(SSLibExceptionType.TooFewValidValueSets);

        return secretPoly.calculate(new BInteger(BigInteger.ZERO, modulo.getThread()));
    }

    private PolynomialRC reconstructWithLagrangeMul() throws FFaplAlgebraicException {
        BInteger modulo = new BInteger(_currentSessionInfo.getModulo(), _currentSessionInfo.getThread());

        HashMap<BigInteger, BigInteger> secretShares = getKeyValuePairs(_localSecretShare.get(_currentMultipartyOperation.getOutputKey()), _othersSecretShares);

        return AlgorithmExtended.getPolynomialByValues(modulo, secretShares);
    }

    private BigInteger calculateFinalResultMul() throws FFaplAlgebraicException {
        PolynomialRC secretPoly = reconstructWithLagrangeMul();
        BInteger modulo = new BInteger(_currentSessionInfo.getModulo(), _currentSessionInfo.getThread());
        return secretPoly.calculate(new BInteger(BigInteger.ZERO, modulo.getThread()));
    }

    private int numberOfValidShares() {
        return _localSecretShare.size() + _othersSecretShares.size();
    }

    private boolean replaceShares(HashMap<BigInteger, BigInteger> commitments, BigInteger combinedSecret, BigInteger combinedRnd) {
        if (shareIsInvalid(commitments.get(_secretId), combinedSecret, combinedRnd, _currentSessionInfo.getCommitmentTriple(), _currentSessionInfo.getCommitmentType()))
            return false;

        else {
            _localSecretShare.put(_currentMultipartyOperation.getOutputKey(), combinedSecret);
            _localRndShare.put(_currentMultipartyOperation.getOutputKey(), combinedRnd);
        }
        return true;
    }

    public void manipulateIntermediateValue() throws FFaplAlgebraicException {

        List<BigInteger> xValues = new ArrayList<>();

        xValues.add(BigInteger.ZERO);
        xValues.addAll(_currentSessionInfo.getPlayerIds());
        Collections.sort(xValues);

        BInteger modulo = new BInteger(_currentSessionInfo.getModulo(), _currentSessionInfo.getThread());
        PolynomialRC h_diff = AlgorithmExtended.getManipulatedPolynomial(_supportOrdinates, xValues, modulo, this.getSecretId());
        HashMap<BigInteger, BigInteger> h_diffValues = getPolynomialValues(h_diff, modulo, xValues);

        _supportOrdinates.clear();

        for (Map.Entry<BigInteger, BigInteger> h_diffValue : h_diffValues.entrySet()) {
            _supportOrdinates.add(h_diffValue.getValue());
        }

        BigInteger hi_diff = _supportOrdinates.get(_secretId.intValue());
        _localSecretShare.put(_currentMultipartyOperation.getOutputKey(), hi_diff);
    }

}
