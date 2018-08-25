package SSLibrary;

import java.io.Serializable;
import java.math.BigInteger;

public class PedersenCommitmentTriple implements Serializable {

    private BigInteger modulo;
    private BigInteger gFactor;
    private BigInteger hFactor;

    public PedersenCommitmentTriple(BigInteger p, BigInteger g, BigInteger h) {
        modulo = p;
        gFactor = g;
        hFactor = h;
    }

    public BigInteger getgFactor() {
        return gFactor;
    }

    public BigInteger gethFactor() {
        return hFactor;
    }

    public BigInteger getModulo() {
        return modulo;
    }
}
