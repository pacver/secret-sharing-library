package SSLibrary;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;

public class MultipartyOperationObjectList implements Serializable{

    private final HashMap<BigInteger, BigInteger> _secretShares1;
    private final HashMap<BigInteger, BigInteger> _randomShares1;
    private final HashMap<BigInteger, BigInteger> _commitmentShares;
    private final Operation _operation;
    private final MultipartyOperation _multipartyOperation;
    private HashMap<BigInteger, BigInteger> _secretShares2;
    private HashMap<BigInteger, BigInteger> _randomShares2;

    /**
     * MultipartyOperationObjectList object includes all secret shares, all randomShares, all Commitments, the share type and the query Definition for one subquery
     *
     * @param secretShares
     * @param randomShares
     * @param commitmentShares
     * @param operation
     * @param multipartyOperation
     */
    public MultipartyOperationObjectList(HashMap<BigInteger, BigInteger> secretShares, HashMap<BigInteger, BigInteger> randomShares, HashMap<BigInteger, BigInteger> commitmentShares, Operation operation, MultipartyOperation multipartyOperation) {
        _secretShares1 = secretShares;
        _randomShares1 = randomShares;
        _commitmentShares = commitmentShares;
        _operation = operation;
        _multipartyOperation = multipartyOperation;
    }

    /**
     * MultipartyOperationObjectList object includes all secret shares, all randomShares, all Commitments, the share type and the query Definition for one subquery
     *
     * @param secretShares1
     * @param secretShares2
     * @param randomShares1
     * @param randomShares2
     * @param commitmentShares
     * @param operation
     * @param multipartyOperation
     */
    public MultipartyOperationObjectList(HashMap<BigInteger, BigInteger> secretShares1, HashMap<BigInteger, BigInteger> secretShares2, HashMap<BigInteger, BigInteger> randomShares1, HashMap<BigInteger, BigInteger> randomShares2, HashMap<BigInteger, BigInteger> commitmentShares, Operation operation, MultipartyOperation multipartyOperation) {
        _secretShares1 = secretShares1;
        _randomShares1 = randomShares1;
        _secretShares2 = secretShares2;
        _randomShares2 = randomShares2;
        _commitmentShares = commitmentShares;
        _operation = operation;
        _multipartyOperation = multipartyOperation;
    }

    public MultipartyOperation getMultiPartyOperation() {
        return _multipartyOperation;
    }

    public Operation getOperationType() {
        return _operation;
    }

    public HashMap<BigInteger, BigInteger> getRandomShares1() {
        return _randomShares1;
    }

    public HashMap<BigInteger, BigInteger> getRandomShares2() {
        return _randomShares2;
    }

    public HashMap<BigInteger, BigInteger> getSecretShares1() {
        return _secretShares1;
    }

    public HashMap<BigInteger, BigInteger> getSecretShares2() {
        return _secretShares2;
    }

    public String getMpcOperationOutputKey() {
        return _multipartyOperation.getOutputKey();
    }

    public MultipartyOperationObject getMultipartyOperationObject(BigInteger secretId) {
        BigInteger secret1 = _secretShares1 != null ? _secretShares1.get(secretId) : null;
        BigInteger secret2 = _secretShares2 != null ? _secretShares2.get(secretId) : null;
        BigInteger random1 = _randomShares1 != null ? _randomShares1.get(secretId) : null;
        BigInteger random2 = _randomShares2 != null ? _randomShares2.get(secretId) : null;
        return new MultipartyOperationObject(secret1, secret2, random1, random2, _operation, _multipartyOperation);
    }

    public HashMap<BigInteger, BigInteger> getCommitmentValues() {
        return _commitmentShares;
    }

}
