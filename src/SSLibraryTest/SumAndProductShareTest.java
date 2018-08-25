package SSLibraryTest;

import SSLibrary.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class SumAndProductShareTest {

    Dealer dealer;
    Participant p1,p2,p3;
BigInteger idP1, idP2, idP3;
    HashMap<String, BigInteger> secrets;
    ArrayList<MultipartyOperation> definitions;
    SessionInfo sessionInfo;
    MultipartyOperationObjectList shares;

    @Before
    public void setUp() {

        dealer = new Dealer();
        p1 = new Participant(true);
        p2 = new Participant(false);
        p3 = new Participant(true);

        idP1 = BigInteger.valueOf(1);
        idP2 = BigInteger.valueOf(3);
        idP3 = BigInteger.valueOf(2);

        secrets = new HashMap<>();
        definitions = new ArrayList<>();
    }

    @After
    public void TearDown(){
        definitions.clear();
        secrets.clear();
    }

    @Test
    public void sumTwiceAndMulShareTest() throws Exception {

        //Dealer defines secrets to share
        secrets.put("a", BigInteger.valueOf(3));
        secrets.put("b", BigInteger.valueOf(4));
        secrets.put("c",BigInteger.valueOf(2));
        secrets.put("d",BigInteger.valueOf(1));

        //Dealer defines queries to calculate
        definitions.add(new MultipartyOperation("r1", "a", "b", Operation.Sum));
        definitions.add(new MultipartyOperation("r2", "c", "d", Operation.Sum));
        definitions.add(new MultipartyOperation("r3", "r1", "r2", Operation.Product));

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

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares of a and b to P1, P2 and P3
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());
        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());
        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares of a and b to P1, P2 and P3
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());
        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());
        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

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
        p3.calculateReducedPolynomial();

        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShare());
        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShare());

        //Reconstruct by P1 and P3
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s3_rec = p3.reconstructFinalResult();

        Assert.assertEquals(21, s1_rec.intValue());
        Assert.assertEquals(21, s3_rec.intValue());
    }

    @Test
    public void sumFourTimesAndMulShareTest() throws Exception {

        secrets.put("a", BigInteger.valueOf(2));
        secrets.put("b", BigInteger.valueOf(2));

        definitions.add(new MultipartyOperation("r1", "a", "a", Operation.Sum));
        definitions.add(new MultipartyOperation("r2", "r1", "r1", Operation.Sum));
        definitions.add(new MultipartyOperation("r3", "r2", "r1", Operation.Sum));
        definitions.add(new MultipartyOperation("r4", "r3", "r2", Operation.Sum));
        definitions.add(new MultipartyOperation("r5", "r4", "b", Operation.Product));

        //dealer chooses s and creates sessionInfo
        dealer.defineSecrets(secrets,definitions,CommitmentType.Pedersen);
        dealer.addParticipantToSession(idP1,p1);
        dealer.addParticipantToSession(idP2,p2);
        dealer.addParticipantToSession(idP3,p3);

        //The dealer creates shares
        sessionInfo =  dealer.prepareMultipartyOperationObjects();

        //The dealer creates shares
        p1.addSessionInfo(idP1, sessionInfo);
        p2.addSessionInfo(idP2, sessionInfo);
        p3.addSessionInfo(idP3, sessionInfo);

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1 and P2
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());

        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());

        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1 and P2
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());

        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());

        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1 and P2
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());

        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());

        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1 and P2
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());

        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());

        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

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
        p3.calculateReducedPolynomial();

        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShare());
        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShare());

        //Reconstruct by P1 and P3
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s3_rec = p3.reconstructFinalResult();

        Assert.assertEquals(40, s1_rec.intValue());
        Assert.assertEquals(40, s3_rec.intValue());
    }

    @Test
    public void MulSumTest() throws Exception {

        //Dealer defines secrets to share
        secrets.put("a", BigInteger.valueOf(9));
        secrets.put("b", BigInteger.valueOf(2));
        secrets.put("c", BigInteger.valueOf(3));

        //Dealer defines queries to calculate
        definitions.add(new MultipartyOperation("r1", "a", "b", Operation.Product));
        definitions.add(new MultipartyOperation("r2", "r1", "c", Operation.Sum));

        //dealer chooses s and creates sessionInfo
        dealer.defineSecrets(secrets,definitions,CommitmentType.Hash256);
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

        //Give right shares of a and b to P1, P2 and P3
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());
        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());
        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShare(), p3.distributeRndShare());
        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShare(), p1.distributeRndShare());

        //Reconstruct by P1 and P3
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s3_rec = p3.reconstructFinalResult();

        Assert.assertEquals(21, s1_rec.intValue());
        Assert.assertEquals(21, s3_rec.intValue());
    }

    @Test
    public void MulSumSumTest() throws Exception {

        //Dealer defines secrets to share
        secrets.put("a", BigInteger.valueOf(8));
        secrets.put("b", BigInteger.valueOf(2));
        secrets.put("c", BigInteger.valueOf(3));
        secrets.put("d", BigInteger.valueOf(2));

        //Dealer defines queries to calculate
        definitions.add(new MultipartyOperation("r1", "a", "b", Operation.Product));
        definitions.add(new MultipartyOperation("r2", "r1", "c", Operation.Sum));
        definitions.add(new MultipartyOperation("r3", "r2", "d", Operation.Sum));

        //dealer chooses s and creates sessionInfo
        dealer.defineSecrets(secrets,definitions,CommitmentType.Hash256);
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

        //Give right shares of a and b to P1, P2 and P3
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());
        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());
        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShare(), p3.distributeRndShare());
        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShare(), p1.distributeRndShare());

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares of a and b to P1, P2 and P3
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());
        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());
        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShare(), p3.distributeRndShare());
        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShare(), p1.distributeRndShare());

        //Reconstruct by P1 and P3
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s3_rec = p3.reconstructFinalResult();

        Assert.assertEquals(21, s1_rec.intValue());
        Assert.assertEquals(21, s3_rec.intValue());
    }

    @Test
    public void SumMulMulTest() throws Exception {

        secrets.put("a",BigInteger.valueOf(1));
        secrets.put("b",BigInteger.valueOf(2));
        secrets.put("c",BigInteger.valueOf(3));

        //Dealer defines queries to calculate
        definitions.add(new MultipartyOperation("r1","a","b", Operation.Sum));
        definitions.add(new MultipartyOperation("r2","r1","c", Operation.Product));
        definitions.add(new MultipartyOperation("r3","r1","r2", Operation.Product));

        // r3 = r1 * r2 = r1 * r1 * c = (a + b) * (a + b) * c =  (1 + 2) * (1 + 2) * 3 = 3 * 3 * 3 = 27

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

        //Give right shares of a and b to P1, P2 and P3
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());
        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());
        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShare(), p3.distributeRndShare());
        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShare(), p1.distributeRndShare());

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

        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShare());
        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShare());

        //Reconstruct by P1 and P2
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s3_rec = p3.reconstructFinalResult();

        Assert.assertEquals(27 ,s1_rec.intValue());
        Assert.assertEquals(27 ,s3_rec.intValue());
    }

    @Test
    public void sumMulSumComplexShare() throws Exception {

        secrets.put("a",BigInteger.valueOf(1));
        secrets.put("b",BigInteger.valueOf(2));
        secrets.put("c",BigInteger.valueOf(3));

        //Dealer defines queries to calculate
        definitions.add(new MultipartyOperation("r1","a","b", Operation.Sum));
        definitions.add(new MultipartyOperation("r2","r1","c", Operation.Product));
        definitions.add(new MultipartyOperation("r3","r1","r2", Operation.Sum));

        // r3 = r1 + r2 = r1 + (r1 * c) = (a + b) + ((a + b) * c) =  (1 + 2) + ((1 + 2) * 3) = 3 + (3*3) = 12

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

        //Give right shares of a and b to P1, P2 and P3
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());
        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());
        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShare(), p3.distributeRndShare());
        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShare(), p1.distributeRndShare());

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

        //Give right shares of a and b to P1, P2 and P3
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());
        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());
        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShare(), p3.distributeRndShare());
        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShare(), p1.distributeRndShare());

        //Reconstruct by P1 and P2
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s3_rec = p3.reconstructFinalResult();

        Assert.assertEquals(12 ,s1_rec.intValue());
        Assert.assertEquals(12 ,s3_rec.intValue());
    }

    @Test
    public void MulSumMulShare() throws Exception {

        secrets.put("a",BigInteger.valueOf(1));
        secrets.put("b",BigInteger.valueOf(2));
        secrets.put("c",BigInteger.valueOf(3));

        //Dealer defines queries to calculate
        definitions.add(new MultipartyOperation("r1","a","b", Operation.Product));
        definitions.add(new MultipartyOperation("r2","r1","c", Operation.Sum));
        definitions.add(new MultipartyOperation("r3","r1","r2", Operation.Product));

        // r3 = r1 * r2 = r1 * (r1 + c) = (a * b) * ((a * b) + c) =  (1 * 2) * ((1 * 2) + 3) = 3 * (2+3) = 2 * 5 = 10

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

        //Give right shares of a and b to P1, P2 and P3
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());
        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());
        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShare(), p3.distributeRndShare());
        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShare(), p1.distributeRndShare());

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

        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShare());
        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShare());

        //Reconstruct by P1 and P2
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s3_rec = p3.reconstructFinalResult();

        Assert.assertEquals(10 ,s1_rec.intValue());
        Assert.assertEquals(10 ,s3_rec.intValue());
    }

    @Test
    public void MulMulSumTest() throws Exception {

        secrets.put("a",BigInteger.valueOf(1));
        secrets.put("b",BigInteger.valueOf(2));
        secrets.put("c",BigInteger.valueOf(3));

        //Dealer defines queries to calculate
        definitions.add(new MultipartyOperation("r1","a","b", Operation.Product));
        definitions.add(new MultipartyOperation("r2","r1","c", Operation.Product));
        definitions.add(new MultipartyOperation("r3","r1","r2", Operation.Sum));

        // r3 = r1 + r2 = r1 + (r1 * c) = (a * b) + (a * b * c) =  (1 * 2) + (1 * 2 * 3) = 2 + 6 = 8

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

        //Give right shares of a and b to P1, P2 and P3
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());
        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());
        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

        p1.receiveShare(p3.getSecretId(), p3.distributeSecretShare(), p3.distributeRndShare());
        p3.receiveShare(p1.getSecretId(), p1.distributeSecretShare(), p1.distributeRndShare());

        //Reconstruct by P1 and P2
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s3_rec = p3.reconstructFinalResult();

        Assert.assertEquals(8 ,s1_rec.intValue());
        Assert.assertEquals(8 ,s3_rec.intValue());
    }


}
