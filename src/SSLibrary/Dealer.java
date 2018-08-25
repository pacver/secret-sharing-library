package SSLibrary;

import ffapl.java.classes.BInteger;
import ffapl.java.classes.PolynomialRC;
import ffapl.java.exception.FFaplAlgebraicException;
import ffaplExtended.java.math.AlgorithmExtended;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static ffaplExtended.java.math.AlgorithmExtended.getCommitment;

public class Dealer implements Serializable {

    private List<MultipartyOperationObjectList> multipartyOperationObjectLists;
    private ExtendedSessionInfo _currentSession;
    private HashMap<String, BigInteger> _secretDefinitions;

    /**
     * Constructor for a new dealer
     */
    public Dealer() {
        multipartyOperationObjectLists = new ArrayList<>();
    }

    /**
     * Add secret definitions and create a new session
     * @param secretDefinitions
     * @param multipartyOperations
     * @param commitmentType
     * @throws FFaplAlgebraicException
     * @throws SSLibException
     */
    public void defineSecrets(HashMap<String, BigInteger> secretDefinitions, ArrayList<MultipartyOperation> multipartyOperations, CommitmentType commitmentType) throws FFaplAlgebraicException, SSLibException {

        if (_currentSession != null)
            throw new SSLibException(SSLibExceptionType.OngoingSession);

        this._secretDefinitions = secretDefinitions;

        BigInteger moduloThreshold = calculateModuloThreshold(multipartyOperations);
        this._currentSession = new ExtendedSessionInfo(multipartyOperations, moduloThreshold, commitmentType);
    }

    /**
     *  * Add secret definitions and create a new session without commitments
     * @param secretDefinitions
     * @param multipartyOperations
     * @throws FFaplAlgebraicException
     * @throws SSLibException
     */
    public void defineSecrets(HashMap<String, BigInteger> secretDefinitions, ArrayList<MultipartyOperation> multipartyOperations) throws FFaplAlgebraicException, SSLibException {

       defineSecrets(secretDefinitions,multipartyOperations,null);
    }

    /**
     * Calculate modulo to use based on secret definitions and mpc program so that result is not reduced by accident
     *
     * @return smallest modulo value to use within session
     */
    private BigInteger calculateModuloThreshold(ArrayList<MultipartyOperation> multipartyOperations) {

        BigInteger value = BigInteger.ZERO;
        BigInteger secret1, secret2;

        for (MultipartyOperation multipartyOperation : multipartyOperations) {
            switch (multipartyOperation.getOperationType()) {

                case None:
                    value = value.add(_secretDefinitions.get(multipartyOperation.getInputKey1()));
                    break;
                case Sum:
                    secret1 = _secretDefinitions.containsKey(multipartyOperation.getInputKey1()) ? value.add(_secretDefinitions.get(multipartyOperation.getInputKey1())) : value;
                    secret2 = _secretDefinitions.getOrDefault(multipartyOperation.getInputKey2(), value);
                    value = secret1.add(secret2);
                    break;
                case Product:
                    secret1 = _secretDefinitions.getOrDefault(multipartyOperation.getInputKey1(), value);
                    secret2 = _secretDefinitions.getOrDefault(multipartyOperation.getInputKey2(), value);
                    value = secret1.multiply(secret2);
                    break;
            }
        }
        return value;
    }

    public void addParticipantToSession(BigInteger secretId, Participant participant) {
        _currentSession.addParticipant(secretId, participant);
    }

    /**
     * This method allows a dealer to create a share after he defined the secrets and created a new session.
     *
     * @return For the Operation.None the method returns a list with two hashmap items. The first list item contains the secret shares and the second item contains the random shares.
     * For the Operation.Sum the method returns a list with four hashmap items. The list item contains of the shares of the first secret, the shares of the second secret, the random shares for the first secret and the random shares for the second secret.
     * For the Operation.Product the method returns a list with two hashmap items. The first list item contains the shares of the first secret and the secret list item contains the shares of the second secret.
     * @throws SSLibException          The method throws a SSLibException when it is called by a participant that is not the dealer. Further, exceptions are thrown when there is no active session or the number of players and combiners is too low.
     * @throws FFaplAlgebraicException The method throws a FFaplAlgebraicException when the calculation of the shares fails.
     */
    public SessionInfo prepareMultipartyOperationObjects() throws SSLibException, FFaplAlgebraicException {
        multipartyOperationObjectLists = new ArrayList<>();

        if (this._currentSession == null)
            throw new SSLibException(SSLibExceptionType.NoActiveSession);

        if (_currentSession.getNumberOfCombiners() < 2)
            throw new SSLibException(SSLibExceptionType.TooFewCombiners);


        while (_currentSession.getNextMultipartyOperation() != null) {
            MultipartyOperation currentMpcOperation = _currentSession.getNextMultipartyOperation();
            MultipartyOperationObjectList operation = null;

            if (currentMpcOperation.getOperationType() == Operation.Product && _currentSession.getNumberOfPlayers() < _currentSession.getNumberOfCombiners() + 1)
                throw new SSLibException(SSLibExceptionType.TooFewPlayers);

            BigInteger secret1 = _secretDefinitions.get(currentMpcOperation.getInputKey1());
            BigInteger secret2 = _secretDefinitions.get(currentMpcOperation.getInputKey2());

            switch (currentMpcOperation.getOperationType()) {
                case None:
                    if (secret1 == null)
                        throw new SSLibException(SSLibExceptionType.MissingSecretDefinition);
                    else
                        operation = createSimpleShare(secret1);
                    break;

                case Sum:
                    if (secret1 != null && secret2 != null)
                        operation = createSumShares(secret1, secret2);
                    else
                        operation = createSimpleShareForCombinedCalculation(currentMpcOperation, secret1, secret2);
                    break;

                case Product:
                    if (secret1 != null && secret2 != null)
                        operation = createProductShares(secret1, secret2);
                    else
                        operation = createSimpleShareForCombinedCalculation(currentMpcOperation, secret1, secret2);
                    break;
            }

            multipartyOperationObjectLists.add(operation);
            _currentSession.increaseMpcOperationIndices();
        }
        _currentSession.resetCurrentMpcOperationIndices();

        return _currentSession;
    }

    /***
     * This method creates the objects to share based on the whole mpc program to calculate.
     * Depending if s1 or s2 is an intermediate result, e.g. s1 = a + b and secret values a and b are defined,
     * different objects must be created.
     * @param currentMpcOperation current operation for which the object values to share are calculated
     * @param secret1 first operand of the operation
     * @param secret2 second operand of the operation
     * @return Returns the corresponding multiparty operation object (secret values to share) for the current operation
     * @throws SSLibException
     * @throws FFaplAlgebraicException
     */
    private MultipartyOperationObjectList createSimpleShareForCombinedCalculation(MultipartyOperation currentMpcOperation, BigInteger secret1, BigInteger secret2) throws SSLibException, FFaplAlgebraicException {

        boolean secret1IsIntermediateResult = secretIsIntermediateResult(currentMpcOperation.getInputKey1());
        boolean secret2IsIntermediateResult = secretIsIntermediateResult(currentMpcOperation.getInputKey2());

        //value can only be null, if it is an intermediate result
        if (secret1 == null && !secret1IsIntermediateResult)
            throw new SSLibException(SSLibExceptionType.MissingSecretDefinition);

        if (secret2 == null && !secret2IsIntermediateResult)
            throw new SSLibException(SSLibExceptionType.MissingSecretDefinition);

        MultipartyOperationObjectList multipartyOperationObjectListForSecret2;
        MultipartyOperationObjectList multipartyOperationObjectListForSecret1;

        if (secret1 == null && secret2 != null) {

            //Values for input key 1 should already been calculated, since it is an intermediate result
            multipartyOperationObjectListForSecret1 = getMPObjectToKey(currentMpcOperation.getInputKey1());

            //Create new shares of the secret to share with inputKey2 (second operand)
            multipartyOperationObjectListForSecret2 = createSimpleShare(secret2);

            //For multiplication only new values of second operand are returned
            if (multipartyOperationObjectListForSecret1.getOperationType() == Operation.Product) {
                return new MultipartyOperationObjectList(null, multipartyOperationObjectListForSecret2.getSecretShares1(), null, null, null, currentMpcOperation.getOperationType(), _currentSession.getNextMultipartyOperation());
            }

            //For additions, the sum of the random values and the commitments of the intermediate result (first operand) and the secret definition (second operand) must be calculated
            multipartyOperationObjectListForSecret1 = getConsolidatedMultipartyOperationObject(multipartyOperationObjectListForSecret1);
        } else if (secret1 != null && secret2 == null) {

            //Create new shares of the secret definition used as inputKey1 (first operand)
            multipartyOperationObjectListForSecret1 = createSimpleShare(secret1);

            //Values for input key 2 should already been calculated, since it is an intermediate result
            multipartyOperationObjectListForSecret2 = getMPObjectToKey(currentMpcOperation.getInputKey2());

            //For multiplication only new values of first operand are returned
            if (multipartyOperationObjectListForSecret2.getOperationType() == Operation.Product) {
                return new MultipartyOperationObjectList(multipartyOperationObjectListForSecret1.getSecretShares1(), null, null, null, null, currentMpcOperation.getOperationType(), _currentSession.getNextMultipartyOperation());
            }

            //For additions, the sum of the random values and the commitments of the intermediate result (first operand) and the secret definition (second operand) must be calculated
            multipartyOperationObjectListForSecret2 = getConsolidatedMultipartyOperationObject(multipartyOperationObjectListForSecret2);
        }

        //In this case both operands are intermediate results and shares for them have already been calculated
        else {

            multipartyOperationObjectListForSecret1 = getMPObjectToKey(currentMpcOperation.getInputKey1());
            multipartyOperationObjectListForSecret2 = getMPObjectToKey(currentMpcOperation.getInputKey2());

            if (multipartyOperationObjectListForSecret1.getOperationType() == Operation.Product || multipartyOperationObjectListForSecret2.getOperationType() == Operation.Product) {
                return new MultipartyOperationObjectList(null, null, null, null, null, currentMpcOperation.getOperationType(), _currentSession.getNextMultipartyOperation());
            }

            multipartyOperationObjectListForSecret1 = getConsolidatedMultipartyOperationObject(multipartyOperationObjectListForSecret1);
            multipartyOperationObjectListForSecret2 = getConsolidatedMultipartyOperationObject(multipartyOperationObjectListForSecret2);
        }

        //For additions the total summed up values are returned
        return getCombinedMpcObjectList(multipartyOperationObjectListForSecret1, multipartyOperationObjectListForSecret2, currentMpcOperation);
    }

    /**
     * Returns one multiparty operation object list for one operation to calculate based on a given key
     *
     * @param key name of the shared value
     * @return
     */
    private MultipartyOperationObjectList getMPObjectToKey(String key) {
        return multipartyOperationObjectLists.stream().filter(o -> o.getMpcOperationOutputKey().equals(key)).findFirst().orElse(null);
    }

    /***
     * Create multipartyOperationObjectList with result shares of two shared secrets for multi-party computation
     * @param multipartyOperationObjectList MultipartyOperationObjectList with two secrets to combine to one secret share, one rnd share and one commitment
     * @return
     */
    private MultipartyOperationObjectList getConsolidatedMultipartyOperationObject(MultipartyOperationObjectList multipartyOperationObjectList) {

        HashMap<BigInteger, BigInteger> _secretShares1;
        HashMap<BigInteger, BigInteger> _randomShares1;
        Operation _operation = multipartyOperationObjectList.getOperationType();
        HashMap<BigInteger, BigInteger> _commitmentShares;

        _secretShares1 = SumUpHashMaps(multipartyOperationObjectList.getSecretShares1(), multipartyOperationObjectList.getSecretShares2());
        _randomShares1 = SumUpHashMaps(multipartyOperationObjectList.getRandomShares1(), multipartyOperationObjectList.getRandomShares2());

        _commitmentShares = new HashMap<>();
        if (_secretShares1 != null && _randomShares1 != null) {
            for (Map.Entry<BigInteger, BigInteger> share : _secretShares1.entrySet()) {
                _commitmentShares.put(share.getKey(), getCommitment(share.getValue(), _randomShares1.get(share.getKey()), _currentSession.getCommitmentTriple(), _currentSession.getCommitmentType()));
            }
        }
        return new MultipartyOperationObjectList(_secretShares1, null, _randomShares1, null, _commitmentShares, _operation, _currentSession.getNextMultipartyOperation());
    }

    /**
     * Sums up values of two hash maps with each other
     *
     * @param map1 first hash map
     * @param map2 second hash map
     * @return Returns a hash map with the summed up values
     */
    private HashMap<BigInteger, BigInteger> SumUpHashMaps(HashMap<BigInteger, BigInteger> map1, HashMap<BigInteger, BigInteger> map2) {
        HashMap<BigInteger, BigInteger> mapOut = new HashMap<>();

        if (map1 == null || map2 == null) {
            return null;
        }

        for (Map.Entry<BigInteger, BigInteger> share : map1.entrySet()) {

            if (map2.get(share.getKey()) == null) {
                continue;
            }
            mapOut.put(share.getKey(), share.getValue().add(map2.get(share.getKey())).mod(_currentSession.getModulo()));
        }

        return mapOut;
    }


    /**
     * Calculate mpc operation objects out of at least one intermediate Result e.g. in one mpc object list a+b is saved and in one c is saved --> create share object for (a+b)+c
     *
     * @param multipartyOperationObjectListForSecret1
     * @param multipartyOperationObjectListForSecret2
     * @param currentMpcOperation
     * @return
     */
    private MultipartyOperationObjectList getCombinedMpcObjectList(MultipartyOperationObjectList multipartyOperationObjectListForSecret1, MultipartyOperationObjectList multipartyOperationObjectListForSecret2, MultipartyOperation currentMpcOperation) {

        HashMap<BigInteger, BigInteger> _secretShares1 = multipartyOperationObjectListForSecret1.getSecretShares1();
        HashMap<BigInteger, BigInteger> _randomShares1 = multipartyOperationObjectListForSecret1.getRandomShares1();
        HashMap<BigInteger, BigInteger> _secretShares2 = multipartyOperationObjectListForSecret2.getSecretShares1();
        HashMap<BigInteger, BigInteger> _randomShares2 = multipartyOperationObjectListForSecret2.getRandomShares1();

        return getSumOfTwoMpcObjectLists(_secretShares1, _secretShares2, _randomShares1, _randomShares2, currentMpcOperation);

    }

    private MultipartyOperationObjectList getSumOfTwoMpcObjectLists(HashMap<BigInteger, BigInteger> secretShares1, HashMap<BigInteger, BigInteger> secretShares2, HashMap<BigInteger, BigInteger> rndShares1, HashMap<BigInteger, BigInteger> rndShares2, MultipartyOperation currentMpcOperation) {
        HashMap<BigInteger, BigInteger> _commitmentShares = null;

        HashMap<BigInteger, BigInteger> _sumShares = SumUpHashMaps(secretShares1, secretShares2);
        HashMap<BigInteger, BigInteger> _rndShares = SumUpHashMaps(rndShares1, rndShares2);

        if (_rndShares != null) {
            _commitmentShares = new HashMap<>();
            for (Map.Entry<BigInteger, BigInteger> share : _sumShares.entrySet()) {
                _commitmentShares.put(share.getKey(), getCommitment(_sumShares.get(share.getKey()), _rndShares.get(share.getKey()), _currentSession.getCommitmentTriple(), _currentSession.getCommitmentType()));
            }
        }
        return new MultipartyOperationObjectList(secretShares1, secretShares2, rndShares1, rndShares2, _commitmentShares, currentMpcOperation.getOperationType(), currentMpcOperation);
    }

    private boolean secretIsIntermediateResult(String intermediateResultKey) {
        for (MultipartyOperationObjectList multipartyOperationObjectList : multipartyOperationObjectLists) {
            if (multipartyOperationObjectList.getMpcOperationOutputKey().equals(intermediateResultKey))
                return true;
        }

        return false;
    }

    public MultipartyOperationObjectList getNextMultipartyOperationObjects() {

        MultipartyOperationObjectList operationObjectList = multipartyOperationObjectLists.get(_currentSession.currentMpcOperationIndices());
        _currentSession.increaseMpcOperationIndices();
        return operationObjectList;
    }

    public boolean isNextShareAvailable() {


        return _currentSession.getNextMultipartyOperation() != null;
    }

    /***
     * Creates a multiparty operation object for one secret definition (all values necessary to share one value)
     * @param secret Defined secret value
     * @return Multiparty operation object list with secret values, random values and commitment values to share
     * @throws FFaplAlgebraicException
     */
    private MultipartyOperationObjectList createSimpleShare(BigInteger secret) throws FFaplAlgebraicException {
        HashMap<BigInteger, BigInteger> secrets;
        HashMap<BigInteger, BigInteger> randoms;
        HashMap<BigInteger, BigInteger> commitments;

        int t = _currentSession.getNumberOfCombiners() - 1;
        Thread thread = _currentSession.getThread();
        BInteger modulo = new BInteger(_currentSession.getModulo(), thread);
        CopyOnWriteArrayList<BigInteger> xValues = _currentSession.getPlayerIds();
        Collections.sort(xValues);

        BInteger degree = new BInteger(BigInteger.valueOf(t), thread);
        BInteger constantTerm = new BInteger(secret, thread);

        PolynomialRC secretPoly = AlgorithmExtended.getRandomPolynomial(degree, modulo, constantTerm);
        PolynomialRC rndPoly = AlgorithmExtended.getRandomPolynomial(degree, modulo);

        secrets = AlgorithmExtended.getPolynomialValues(secretPoly, modulo, xValues);
        randoms = AlgorithmExtended.getPolynomialValues(rndPoly, modulo, xValues);
        commitments = AlgorithmExtended.getPolynomialCommitmentValues(secretPoly, rndPoly, modulo, xValues, _currentSession.getCommitmentTriple(), _currentSession.getCommitmentType());

        return new MultipartyOperationObjectList(secrets, randoms, commitments, Operation.None, _currentSession.getNextMultipartyOperation());
    }

    /***
     * Creates a multiparty operation object for the sum of two secret definition (all values necessary to share the sum of two values)
     * @param secret1 Defined secret value of first operand
     * @param secret2 Defined secret value of second operand
     * @return Multiparty operation object list with secret values, random values and commitment values to share for one expression
     * @throws FFaplAlgebraicException
     */
    private MultipartyOperationObjectList createSumShares(BigInteger secret1, BigInteger secret2) throws FFaplAlgebraicException {

        HashMap<BigInteger, BigInteger> secrets1;
        HashMap<BigInteger, BigInteger> randoms1;
        HashMap<BigInteger, BigInteger> secrets2;
        HashMap<BigInteger, BigInteger> randoms2;
        HashMap<BigInteger, BigInteger> commitments;

        int t = _currentSession.getNumberOfCombiners() - 1;
        Thread thread = _currentSession.getThread();
        BInteger modulo = new BInteger(_currentSession.getModulo(), thread);
        CopyOnWriteArrayList<BigInteger> xValues = _currentSession.getPlayerIds();
        BInteger degree = new BInteger(BigInteger.valueOf(t), thread);

        BInteger constantTerm = new BInteger(secret1, modulo.getThread());
        PolynomialRC secretPoly1 = AlgorithmExtended.getRandomPolynomial(degree, modulo, constantTerm);
        PolynomialRC rndPoly1 = AlgorithmExtended.getRandomPolynomial(degree, modulo);

        secrets1 = AlgorithmExtended.getPolynomialValues(secretPoly1, modulo, xValues);
        randoms1 = AlgorithmExtended.getPolynomialValues(rndPoly1, modulo, xValues);

        PolynomialRC secretPoly2, rndPoly2;

        if (secret1.equals(secret2)) {
            secrets2 = secrets1;
            randoms2 = randoms1;
            secretPoly2 = secretPoly1;
            rndPoly2 = rndPoly1;
        } else {
            constantTerm = new BInteger(secret2, modulo.getThread());
            secretPoly2 = AlgorithmExtended.getRandomPolynomial(degree, modulo, constantTerm);
            rndPoly2 = AlgorithmExtended.getRandomPolynomial(degree, modulo);

            secrets2 = AlgorithmExtended.getPolynomialValues(secretPoly2, modulo, xValues);
            randoms2 = AlgorithmExtended.getPolynomialValues(rndPoly2, modulo, xValues);
        }
        secretPoly1.add(secretPoly2);
        rndPoly1.add(rndPoly2);
        commitments = AlgorithmExtended.getPolynomialCommitmentValues(secretPoly1, rndPoly1, modulo, xValues, _currentSession.getCommitmentTriple(), _currentSession.getCommitmentType());


        return new MultipartyOperationObjectList(secrets1, secrets2, randoms1, randoms2, commitments, Operation.Sum, _currentSession.getNextMultipartyOperation());
    }

    /***
     * Creates a multiparty operation object for the product of two secret definition.
     * Object contains all values necessary to share the product of two secret definitions.
     * @param secret1 Defined secret value of first operand
     * @param secret2 Defined secret value of second operand
     * @return Multiparty operation object list with secret values, random values and commitment values to share for one product expression
     * @throws FFaplAlgebraicException
     */
    private MultipartyOperationObjectList createProductShares(BigInteger secret1, BigInteger secret2) throws FFaplAlgebraicException {

        HashMap<BigInteger, BigInteger> secrets1;
        HashMap<BigInteger, BigInteger> secrets2;

        int t = _currentSession.getNumberOfCombiners() - 1;
        Thread thread = _currentSession.getThread();
        BInteger modulo = new BInteger(_currentSession.getModulo(), thread);
        CopyOnWriteArrayList<BigInteger> xValues = _currentSession.getPlayerIds();
        Collections.sort(xValues);
        BInteger degree = new BInteger(BigInteger.valueOf(t), thread);

        BInteger constantTerm = new BInteger(secret1, modulo.getThread());
        PolynomialRC secretPoly1 = AlgorithmExtended.getRandomPolynomial(degree, modulo, constantTerm);
        secrets1 = AlgorithmExtended.getPolynomialValues(secretPoly1, modulo, xValues);

        constantTerm = new BInteger(secret2, modulo.getThread());
        PolynomialRC secretPoly2 = AlgorithmExtended.getRandomPolynomial(degree, modulo, constantTerm);
        secrets2 = AlgorithmExtended.getPolynomialValues(secretPoly2, modulo, xValues);

        return new MultipartyOperationObjectList(secrets1, secrets2, null, null, null, Operation.Product, _currentSession.getNextMultipartyOperation());
    }
}
