package SSLibrary;

import java.io.Serializable;
import java.math.BigInteger;

public class MultipartyOperationObject implements Serializable {

    private final BigInteger _secret1;
    private final BigInteger _secret2;
    private final BigInteger _random1;
    private final BigInteger _random2;
    private final Operation _operation;
    private final MultipartyOperation _multipartyOperation;

    public MultipartyOperationObject(BigInteger secret1, BigInteger secret2, BigInteger random1, BigInteger random2, Operation operation, MultipartyOperation multipartyOperation) {
        _secret1 = secret1;
        _secret2 = secret2;
        _random1 = random1;
        _random2 = random2;
        _operation = operation;
        _multipartyOperation = multipartyOperation;

    }

    public BigInteger getRandom1() {
        return _random1;
    }

    public BigInteger getRandom2() {
        return _random2;
    }

    public BigInteger getSecret1() {
        return _secret1;
    }

    public BigInteger getSecret2() {
        return _secret2;
    }

    public Operation getShareType() {
        return _operation;
    }

    public MultipartyOperation getMultipartyOperation() {
        return _multipartyOperation;
    }
}
