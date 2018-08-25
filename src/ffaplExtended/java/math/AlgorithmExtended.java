package ffaplExtended.java.math;

import SSLibrary.CommitmentType;
import SSLibrary.PedersenCommitmentTriple;
import ffapl.java.classes.*;
import ffapl.java.exception.FFaplAlgebraicException;
import ffapl.java.interfaces.IAlgebraicError;
import ffapl.java.math.Algorithm;

import java.math.BigInteger;
import java.util.*;

public class AlgorithmExtended extends Algorithm {

    public static PolynomialRC getRandomPolynomial(BInteger n, BInteger p, BInteger c) throws FFaplAlgebraicException {
        PolynomialRC ply;
        Thread thread = p.getThread();
        RNG_Placebo rnd1 = new RNG_Placebo(BigInteger.ONE, p, thread);
        RNG_Placebo rnd2 = new RNG_Placebo(p, thread);

        if (n.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0){

            ply = new PolynomialRC(rnd1.next(), n, p, thread);
            while(ply.isZero()){
                ply = new PolynomialRC(rnd1.next(), n, p, thread);
            }

            ply.add(c,BigInteger.valueOf(0));

            for(int i = 1; i < n.intValue(); i++){
                ply.add(rnd2.next(), BigInteger.valueOf(i));
            }
        }
        else{
            Object[] messages = {n};
            throw new FFaplAlgebraicException(messages,
                    IAlgebraicError.TO_HIGH_EXPONENT);
        }

        return ply;
    }


    public static PolynomialRC getPolynomialByValues(BInteger p, HashMap<BigInteger,BigInteger> values) throws  FFaplAlgebraicException
    {
        Thread t = p.getThread();
        PolynomialRC g = new PolynomialRC(p,t);
        PolynomialRC polyTemp;
        PolynomialRC L;
        BigInteger x_temp;
        Integer n = values.size();
        Object[] x_values = values.keySet().toArray();

        for(int k = 0; k < n; k++)
        {
            L = new PolynomialRC(p,t);
            L.add(new Polynomial("1",t));

            for(int i = 0; i < n; i++)
            {
                if(i != k)
                {
                    x_temp = new BigInteger(x_values[k].toString()).subtract(new BigInteger(x_values[i].toString()));
                    polyTemp = new PolynomialRC(p,t);
                    polyTemp.add(new Polynomial("x-"+x_values[i],t)); //i
                    polyTemp.divide(new Polynomial(x_temp.toString(),t));
                    L.multiply(polyTemp);
                }
            }
            L.multiply(new Polynomial(values.get(x_values[k]).toString(),t));
            g.add(L);
        }

        return g;
    }

    public static HashMap<BigInteger,BigInteger> getPolynomialValues(PolynomialRC ply, BInteger p, List<BigInteger> xValues) throws FFaplAlgebraicException {
        HashMap<BigInteger,BigInteger> values = new HashMap<>();
        Thread thread = p.getThread();

        for(Iterator<BigInteger> i = xValues.iterator(); i.hasNext();)
        {
            BigInteger xValue = i.next();
            values.put(xValue,ply.calculate(new BInteger(xValue,thread)));
        }
        return values;
    }

    public static HashMap<BigInteger,BigInteger> getPolynomialCommitmentValues(PolynomialRC secretPly, PolynomialRC randomPly, BInteger p, List<BigInteger> xValues, PedersenCommitmentTriple commitmentTriple, CommitmentType commitmentType) throws FFaplAlgebraicException {
        HashMap<BigInteger,BigInteger> values = new HashMap<>();
        Thread thread = p.getThread();
        BigInteger s_temp;
        BigInteger r_temp;

        for(Iterator<BigInteger> i = xValues.iterator(); i.hasNext();)
        {
            BigInteger xValue = i.next();
            s_temp = secretPly.calculate(new BInteger(xValue,thread));
            r_temp = randomPly.calculate(new BInteger(xValue,thread));

            values.put(xValue,getCommitment(s_temp,r_temp,commitmentTriple,commitmentType));
        }
        return values;
    }

    public static BigInteger getRandomPrime(BigInteger threshold, Thread thread) throws FFaplAlgebraicException {
        RNG_Placebo random = new RNG_Placebo(BigInteger.ONE, threshold, thread);

        threshold = threshold.add(random.next());
        return threshold.nextProbablePrime();
    }

    public static Integer getRandomNumber(BigInteger threshold, Thread thread) throws FFaplAlgebraicException {
        RNG_Placebo random = new RNG_Placebo(BigInteger.ONE, threshold.add(BigInteger.ONE), thread);
        return random.next().intValue();
    }

    public static boolean shareIsInvalid(BigInteger commitmentValue, BigInteger s, BigInteger r, PedersenCommitmentTriple commitmentTriple, CommitmentType commitmentType) {
        return !getCommitment(s,r,commitmentTriple,commitmentType).equals(commitmentValue);
    }

    public static BigInteger getCommitment(BigInteger messageValue, BigInteger randomValue, PedersenCommitmentTriple commitmentTriple, CommitmentType commitmentType)
    {
        if(commitmentType == CommitmentType.Hash256)
            return hashSHA256(new JString(messageValue.toString() + randomValue.toString()));

        else
            return getPedersenCommitment(messageValue,randomValue, commitmentTriple);
    }

    private static BigInteger getPedersenCommitment(BigInteger messageValue, BigInteger randomValue, PedersenCommitmentTriple commitmentTriple )
    {
        return commitmentTriple.getgFactor().pow(messageValue.intValue()).multiply(commitmentTriple.gethFactor().pow(randomValue.intValue())).mod(commitmentTriple.getModulo());
    }

    public static PedersenCommitmentTriple getCommitmentFactors(BigInteger sessionPrime, BigInteger threshold, Thread thread) throws FFaplAlgebraicException {
        BigInteger q = sessionPrime;
        BigInteger mu = getRandomPrime(BigInteger.TEN,thread);
        BigInteger p = mu.multiply(q).add(BigInteger.ONE);

        BigInteger g = findSubgroupGenerator(p,thread);
        Integer z = getRandomNumber(threshold,thread);
        BigInteger h = g.pow(z).mod(p);

        return new PedersenCommitmentTriple(p,g,h);
    }

    /**
     * Find element with order q of Zp
     * @param p
     * @param thread
     * @return
     * @throws FFaplAlgebraicException
     */
    private static BigInteger findSubgroupGenerator(BigInteger p, Thread thread) throws FFaplAlgebraicException {
        BigInteger a,pow;
        TreeMap<BigInteger, BigInteger> primeFact;
        primeFact = Algorithm.FactorInteger(new BInteger(p,thread));

        a = BigInteger.ONE;

        boolean generatorFound = false;

        while(!generatorFound && a.intValue() < p.subtract(BigInteger.ONE).intValue())
        {
            a = a.add(BigInteger.ONE);
            generatorFound = true;

            for(int i=0; i < primeFact.size(); i++)
            {
                pow = primeFact.get(primeFact.keySet().toArray()[i]);

                if(a.pow(pow.intValue())==BigInteger.ONE)
                {
                    generatorFound = false;
                }
            }
        }
        return a.pow(2);
    }

    public static PolynomialRC getManipulatedPolynomial(List<BigInteger> supportOrdinates, List<BigInteger> xValues, BInteger p, BigInteger manipulatingXValue) throws FFaplAlgebraicException {
        HashMap<BigInteger, BigInteger> hValues = new HashMap<>();

        PolynomialRC delta = getDeltaPolynomial(xValues, p, manipulatingXValue);

        for(int i=0; i < supportOrdinates.size();i++)
        {
            hValues.put(BigInteger.valueOf(i),supportOrdinates.get(i));
        }

        PolynomialRC h = getPolynomialByValues(p,hValues);

       h.add(delta);

        return h;
    }

    public static PolynomialRC getDeltaPolynomial(List<BigInteger> xValues, BInteger p, BigInteger manipulatingXValue) throws FFaplAlgebraicException {
        HashMap<BigInteger, BigInteger> deltaValues = new HashMap<>();

        deltaValues.put(BigInteger.ZERO, BigInteger.valueOf(-1));
        for (BigInteger xValue : xValues) {

            if(xValue != BigInteger.ZERO && xValue != manipulatingXValue)
                 deltaValues.put(xValue,BigInteger.ZERO);
        }

        return getPolynomialByValues(p,deltaValues);
    }


}
