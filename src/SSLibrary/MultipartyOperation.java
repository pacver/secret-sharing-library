package SSLibrary;

import java.io.Serializable;

public class MultipartyOperation implements Serializable{

    private final String _inputKey1;
    private final String _inputKey2;
    private final String _outputKey;
    private final Operation _operation;

    public MultipartyOperation(String outputKey, String inputKey1, String inputKey2, Operation operation) {
        this._inputKey1 = inputKey1;
        this._inputKey2 = inputKey2;
        this._outputKey = outputKey;
        this._operation = operation;
    }

    public String getInputKey1() {
        return _inputKey1;
    }

    public Operation getOperationType() {
        return _operation;
    }

    public String getInputKey2() {
        return _inputKey2;
    }

    public String getOutputKey() {
        return _outputKey;
    }


}
