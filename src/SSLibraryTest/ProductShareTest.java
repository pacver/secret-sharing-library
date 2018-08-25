package SSLibraryTest;

import SSLibrary.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductShareTest {

    Dealer dealer;
    Participant p1;
    Participant p2;
    Participant p3;
    BigInteger idP1, idP2, idP3;
    HashMap<String, BigInteger> secrets;
    ArrayList<MultipartyOperation> definitions;
    SessionInfo sessionInfo;
    MultipartyOperationObjectList shares;

    @Before
    public void setUp() {

        dealer = new Dealer();
        p1 = new Participant(true);
        p2 = new Participant(true);
        p3 = new Participant(false);

        idP1 = BigInteger.valueOf(1);
        idP2 = BigInteger.valueOf(2);
        idP3 = BigInteger.valueOf(3);

        secrets = new HashMap<>();
        definitions = new ArrayList<>();
    }

    @After
    public void TearDown(){
        definitions.clear();
        secrets.clear();
    }

    @Test
    public void productShare21() throws Exception {

        secrets.put("a",BigInteger.valueOf(7));
        secrets.put("b",BigInteger.valueOf(3));

        //Dealer defines queries to calculate
        definitions.add(new MultipartyOperation("r1","a","b", Operation.Product));

        //dealer chooses s and creates sessionInfo
        dealer.defineSecrets(secrets,definitions);
        dealer.addParticipantToSession(idP1,p1);
        dealer.addParticipantToSession(idP2,p2);
        dealer.addParticipantToSession(idP3,p3);

        //The dealer creates shares
        sessionInfo =  dealer.prepareMultipartyOperationObjects();
        p1.addSessionInfo(idP1, sessionInfo);
        p2.addSessionInfo(idP2, sessionInfo);
        p3.addSessionInfo(idP3, sessionInfo);

        //The dealer creates shares
        dealer.prepareMultipartyOperationObjects();

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1, P2 and P3
        p1.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p1.getSecretId()));
        p2.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p2.getSecretId()));
        p3.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p3.getSecretId()));

        //Share secrets
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p1.getSecretId()));
        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p1.getSecretId()));

        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p2.getSecretId()));
        p2.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p2.getSecretId()));

        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p3.getSecretId()));
        p3.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p3.getSecretId()));

        p1.calculateReducedPolynomial();
        p2.calculateReducedPolynomial();

        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShare());
        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShare());

        //Reconstruct by P1 and P3
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s3_rec = p2.reconstructFinalResult();

        Assert.assertEquals(21,s1_rec.intValue());
        Assert.assertEquals(21,s3_rec.intValue());
    }

    @Test
    public void productTwiceShare42() throws Exception {

        secrets.put("a",BigInteger.valueOf(7));
        secrets.put("b",BigInteger.valueOf(3));
        secrets.put("c",BigInteger.valueOf(2));

        //Dealer defines queries to calculate
        definitions.add(new MultipartyOperation("r1","a","b", Operation.Product));
        definitions.add(new MultipartyOperation("r2","r1","c", Operation.Product));

        //dealer chooses s and creates sessionInfo
        dealer.defineSecrets(secrets,definitions);
        dealer.addParticipantToSession(idP1,p1);
        dealer.addParticipantToSession(idP2,p2);
        dealer.addParticipantToSession(idP3,p3);

        //The dealer creates shares
        sessionInfo =  dealer.prepareMultipartyOperationObjects();
        p1.addSessionInfo(idP1, sessionInfo);
        p2.addSessionInfo(idP2, sessionInfo);
        p3.addSessionInfo(idP3, sessionInfo);

        //The dealer creates shares
        dealer.prepareMultipartyOperationObjects();

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1, P2 and P3
        p1.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p1.getSecretId()));
        p2.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p2.getSecretId()));
        p3.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p3.getSecretId()));

        //Share secrets
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p1.getSecretId()));
        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p1.getSecretId()));

        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p2.getSecretId()));
        p2.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p2.getSecretId()));

        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p3.getSecretId()));
        p3.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p3.getSecretId()));

        p1.calculateReducedPolynomial();
        p2.calculateReducedPolynomial();
        p3.calculateReducedPolynomial();

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1, P2 and P3
        p1.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p1.getSecretId()));
        p2.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p2.getSecretId()));
        p3.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p3.getSecretId()));

        //Share secrets
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p1.getSecretId()));
        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p1.getSecretId()));

        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p2.getSecretId()));
        p2.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p2.getSecretId()));

        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p3.getSecretId()));
        p3.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p3.getSecretId()));

        p1.calculateReducedPolynomial();
        p2.calculateReducedPolynomial();
        p3.calculateReducedPolynomial();

        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShare());
        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShare());

        //Reconstruct by P1 and P2
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s2_rec = p2.reconstructFinalResult();

        Assert.assertEquals(42,s1_rec.intValue());
        Assert.assertEquals(42,s2_rec.intValue());
    }

    @Test
    public void productShare1() throws Exception {

        secrets.put("a",BigInteger.valueOf(1));
        secrets.put("b",BigInteger.valueOf(1));

        //Dealer defines queries to calculate
        definitions.add(new MultipartyOperation("r1","a","b", Operation.Product));

        dealer.defineSecrets(secrets,definitions);
        dealer.addParticipantToSession(idP1,p1);
        dealer.addParticipantToSession(idP2,p2);
        dealer.addParticipantToSession(idP3,p3);

        //The dealer creates shares
        sessionInfo =  dealer.prepareMultipartyOperationObjects();
        p1.addSessionInfo(idP1, sessionInfo);
        p2.addSessionInfo(idP2, sessionInfo);
        p3.addSessionInfo(idP3, sessionInfo);

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1, P2 and P3
        p1.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p1.getSecretId()));
        p2.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p2.getSecretId()));
        p3.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p3.getSecretId()));

        //Share secrets
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p1.getSecretId()));
        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p1.getSecretId()));

        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p2.getSecretId()));
        p2.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p2.getSecretId()));

        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p3.getSecretId()));
        p3.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p3.getSecretId()));

        p1.calculateReducedPolynomial();
        p2.calculateReducedPolynomial();

        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShare());
        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShare());

        //Reconstruct by P1 and P2
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s3_rec = p2.reconstructFinalResult();

        Assert.assertEquals(1,s1_rec.intValue());
        Assert.assertEquals(1,s3_rec.intValue());
    }

    @Test
    public void productThreeTimesShare() throws Exception {

        secrets.put("a",BigInteger.valueOf(7));
        secrets.put("b",BigInteger.valueOf(3));
        secrets.put("c",BigInteger.valueOf(2));
        secrets.put("d",BigInteger.valueOf(1));

        //Dealer defines queries to calculate
        definitions.add(new MultipartyOperation("r1","a","b", Operation.Product));
        definitions.add(new MultipartyOperation("r2","r1","c", Operation.Product));
        definitions.add(new MultipartyOperation("r3","r2","d", Operation.Product));

        //r3 = r2 * d = r1 * c * d = a * b * c * d = 7 * 3 * 2 * 1 = 42

        //dealer chooses s and creates sessionInfo
        dealer.defineSecrets(secrets,definitions);
        dealer.addParticipantToSession(idP1,p1);
        dealer.addParticipantToSession(idP2,p2);
        dealer.addParticipantToSession(idP3,p3);

        //The dealer creates shares
        sessionInfo =  dealer.prepareMultipartyOperationObjects();
        p1.addSessionInfo(idP1, sessionInfo);
        p2.addSessionInfo(idP2, sessionInfo);
        p3.addSessionInfo(idP3, sessionInfo);

        //The dealer creates shares
        dealer.prepareMultipartyOperationObjects();

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1, P2 and P3
        p1.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p1.getSecretId()));
        p2.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p2.getSecretId()));
        p3.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p3.getSecretId()));

        //Share secrets
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p1.getSecretId()));
        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p1.getSecretId()));

        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p2.getSecretId()));
        p2.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p2.getSecretId()));

        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p3.getSecretId()));
        p3.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p3.getSecretId()));

        p1.calculateReducedPolynomial();
        p2.calculateReducedPolynomial();
        p3.calculateReducedPolynomial();

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1, P2 and P3
        p1.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p1.getSecretId()));
        p2.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p2.getSecretId()));
        p3.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p3.getSecretId()));

        //Share secrets
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p1.getSecretId()));
        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p1.getSecretId()));

        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p2.getSecretId()));
        p2.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p2.getSecretId()));

        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p3.getSecretId()));
        p3.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p3.getSecretId()));

        p1.calculateReducedPolynomial();
        p2.calculateReducedPolynomial();
        p3.calculateReducedPolynomial();

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1, P2 and P3
        p1.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p1.getSecretId()));
        p2.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p2.getSecretId()));
        p3.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p3.getSecretId()));

        //Share secrets
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p1.getSecretId()));
        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p1.getSecretId()));

        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p2.getSecretId()));
        p2.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p2.getSecretId()));

        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p3.getSecretId()));
        p3.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p3.getSecretId()));

        p1.calculateReducedPolynomial();
        p2.calculateReducedPolynomial();
        p3.calculateReducedPolynomial();

        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShare());
        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShare());

        //Reconstruct by P1 and P2
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s2_rec = p2.reconstructFinalResult();

        Assert.assertEquals(42 ,s1_rec.intValue());
        Assert.assertEquals(42 ,s2_rec.intValue());
    }

    @Test
    public void productComplexShare() throws Exception {

        secrets.put("a",BigInteger.valueOf(1));
        secrets.put("b",BigInteger.valueOf(2));
        secrets.put("c",BigInteger.valueOf(3));

        //Dealer defines queries to calculate
        definitions.add(new MultipartyOperation("r1","a","b", Operation.Product));
        definitions.add(new MultipartyOperation("r2","r1","c", Operation.Product));
        definitions.add(new MultipartyOperation("r3","r1","r2", Operation.Product));

        // r3 = r1 * r2 = r1 * r1 * c = a * b * a * b * c =  1 * 2 * 1 * 2 * 3 = 12

        //dealer chooses s and creates sessionInfo
        dealer.defineSecrets(secrets,definitions);
        dealer.addParticipantToSession(idP1,p1);
        dealer.addParticipantToSession(idP2,p2);
        dealer.addParticipantToSession(idP3,p3);

        //The dealer creates shares
        sessionInfo =  dealer.prepareMultipartyOperationObjects();
        p1.addSessionInfo(idP1, sessionInfo);
        p2.addSessionInfo(idP2, sessionInfo);
        p3.addSessionInfo(idP3, sessionInfo);

        //The dealer creates shares
        dealer.prepareMultipartyOperationObjects();

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1, P2 and P3
        p1.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p1.getSecretId()));
        p2.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p2.getSecretId()));
        p3.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p3.getSecretId()));

        //Share secrets
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p1.getSecretId()));
        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p1.getSecretId()));

        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p2.getSecretId()));
        p2.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p2.getSecretId()));

        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p3.getSecretId()));
        p3.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p3.getSecretId()));

        p1.calculateReducedPolynomial();
        p2.calculateReducedPolynomial();
        p3.calculateReducedPolynomial();

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1, P2 and P3
        p1.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p1.getSecretId()));
        p2.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p2.getSecretId()));
        p3.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p3.getSecretId()));

        //Share secrets
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p1.getSecretId()));
        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p1.getSecretId()));

        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p2.getSecretId()));
        p2.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p2.getSecretId()));

        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p3.getSecretId()));
        p3.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p3.getSecretId()));

        p1.calculateReducedPolynomial();
        p2.calculateReducedPolynomial();
        p3.calculateReducedPolynomial();

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1, P2 and P3
        p1.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p1.getSecretId()));
        p2.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p2.getSecretId()));
        p3.addLocalSharesOfProducts(shares.getMultipartyOperationObject(p3.getSecretId()));

        //Share secrets
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p1.getSecretId()));
        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p1.getSecretId()));

        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p2.getSecretId()));
        p2.receiveShare(p3.getSecretId(), p3.distributeSecretShareForSpecificParticipant(p2.getSecretId()));

        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShareForSpecificParticipant(p3.getSecretId()));
        p3.receiveShare(p2.getSecretId(), p2.distributeSecretShareForSpecificParticipant(p3.getSecretId()));

        p1.calculateReducedPolynomial();
        p2.calculateReducedPolynomial();
        p3.calculateReducedPolynomial();

        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShare());
        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShare());

        //Reconstruct by P1 and P2
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s2_rec = p2.reconstructFinalResult();

        Assert.assertEquals(12 ,s1_rec.intValue());
        Assert.assertEquals(12 ,s2_rec.intValue());
    }

}
