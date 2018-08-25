package SSLibrary;

import ffapl.java.exception.FFaplAlgebraicException;
import ffaplExtended.java.math.AlgorithmExtended;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class SessionInfo implements Serializable{

    final ArrayList<BigInteger> _players;
    final ArrayList<BigInteger> _combiners;
    private final ThreadSerializable _thread;
    private BigInteger _modulo;
    private CommitmentType _commitmentType;
    private PedersenCommitmentTriple _commitmentTriple;

    /**
     * Constructor to create a new session
     *
     * @param moduloThreshold minimum size of modulo to prevent unintended reduction of calculation
     */
    public SessionInfo(BigInteger moduloThreshold, CommitmentType commitmentType) throws FFaplAlgebraicException {
        _thread = new ThreadSerializable();
        _players = new ArrayList<>();
        _combiners = new ArrayList<>();
        _modulo = AlgorithmExtended.getRandomPrime(moduloThreshold.nextProbablePrime(), _thread);
        _commitmentType = commitmentType;
        _commitmentTriple = _commitmentType == CommitmentType.Hash256 ? null : AlgorithmExtended.getCommitmentFactors(_modulo, moduloThreshold, _thread);

    }

    public PedersenCommitmentTriple getCommitmentTriple() {
        return _commitmentTriple;
    }

    public CommitmentType getCommitmentType() {
        return _commitmentType;
    }

    /**
     * This method is necessary to establish, that during the whole session the same modulo is used by all participants.
     *
     * @return Returns the modulo of the current session.
     */
    public BigInteger getModulo() {
        return _modulo;
    }

    /**
     * This method is necessary to establish that during the whole session the same thread is used for all calculations
     *
     * @return Returns the session thread for ffapl/sunset calculations.
     */
    public Thread getThread() {
        return _thread;
    }

    /**
     * @return Number of players that join the current session
     */
    public Integer getNumberOfPlayers() {
        return _players.size();
    }

    /**
     * @return Number of combiners that join the current session
     */
    public Integer getNumberOfCombiners() {
        return _combiners.size();
    }

    /**
     * @return Id's of players that join the current session
     */
    public CopyOnWriteArrayList<BigInteger> getPlayerIds() {

        CopyOnWriteArrayList<BigInteger> copy = new CopyOnWriteArrayList<>();
        copy.addAll(_players);
        return copy;
    }

    public String toString()
    {
      return
    }

}
