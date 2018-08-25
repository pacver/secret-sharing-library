package SSLibrary;

import ffapl.java.exception.FFaplAlgebraicException;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;

public class ExtendedSessionInfo extends SessionInfo implements Serializable{

    private final ArrayList<MultipartyOperation> _multipartyOperations;
    private Integer _currentMpcOperationIndices;

    /**
     * Constructor to create a new session
     *
     * @param multipartyOperations Queries to calculate within the session
     * @param moduloThreshold      minimum size of modulo to prevent unintended reduction of calculation
     */
    public ExtendedSessionInfo(ArrayList<MultipartyOperation> multipartyOperations, BigInteger moduloThreshold, CommitmentType commitmentType) throws FFaplAlgebraicException {
        super(moduloThreshold, commitmentType);
        _multipartyOperations = multipartyOperations;
        _currentMpcOperationIndices = 0;
    }

    /**
     * This method is necessary to decide which mpc operation must be calculated next
     *
     * @return Returns indices of mpc operation to calculate nect
     */
    public int currentMpcOperationIndices() {
        return _currentMpcOperationIndices;
    }

    public void resetCurrentMpcOperationIndices() {
        _currentMpcOperationIndices = 0;
    }

    public MultipartyOperation getNextMultipartyOperation() {

        if (_currentMpcOperationIndices >= _multipartyOperations.size())
            return null;

        return _multipartyOperations.get(_currentMpcOperationIndices);
    }

    public void increaseMpcOperationIndices() {
        _currentMpcOperationIndices++;
    }

    /**
     * With this method a participant is added to the session
     *
     * @param secretId Id of participant that should be added to the session.
     */
    public void addParticipant(BigInteger secretId, Participant participant) {

        if (!_players.contains(secretId))
            _players.add(secretId);

        if (participant.IsCombiner())
            _combiners.add(secretId);
    }
}
