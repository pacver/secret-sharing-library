package SSLibraryTest;

import SSLibrary.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class SumShareTest {

    Dealer dealer;
    Participant p1;
    Participant p2;
    Participant p3;
    BigInteger idP1, idP2,idP3;
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

        idP1 = BigInteger.valueOf(7);
        idP2 = BigInteger.valueOf(8);
        idP3 = BigInteger.valueOf(13);

        secrets = new HashMap<>();
        definitions = new ArrayList<>();
    }

    @Test
    public void sumShare21PedersenCommitment() throws Exception {

        secrets.put("s1", BigInteger.valueOf(12));
        secrets.put("s2", BigInteger.valueOf(9));

        MultipartyOperation multipartyOperation = new MultipartyOperation("r1", "s1", "s2", Operation.Sum);
        definitions.add(multipartyOperation);

        //dealer chooses s and creates sessionInfo
        dealer.defineSecrets(secrets,definitions,CommitmentType.Pedersen);
        dealer.addParticipantToSession(idP1,p1);
        dealer.addParticipantToSession(idP2,p2);

        //The dealer creates shares
        sessionInfo =  dealer.prepareMultipartyOperationObjects();
        p1.addSessionInfo(idP1, sessionInfo);
        p2.addSessionInfo(idP2, sessionInfo);

        //The dealer creates shares
        dealer.prepareMultipartyOperationObjects();
        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1 and P2
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());

        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());

        //Share secrets between P1 and P2
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShare(), p2.distributeRndShare());
        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShare(), p1.distributeRndShare());

        //Reconstruct by P1 and P2
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s2_rec = p2.reconstructFinalResult();

        Assert.assertEquals(21, s1_rec.intValue());
        Assert.assertEquals(21, s2_rec.intValue());
    }

    @Test
    public void sumSameValuePedersenCommitment() throws Exception {

        secrets.put("a", BigInteger.valueOf(2));
        secrets.put("b", BigInteger.valueOf(1));

        definitions.add(new MultipartyOperation("r1", "a", "a", Operation.Sum));
        definitions.add(new MultipartyOperation("r2", "r1", "r1", Operation.Sum));
        definitions.add(new MultipartyOperation("r3", "r2", "r1", Operation.Sum));
        definitions.add(new MultipartyOperation("r4", "r3", "r2", Operation.Sum));
        definitions.add(new MultipartyOperation("r5", "r4", "b", Operation.Sum));

        //r1 = 4
        //r2 = 4+4 = 8
        //r3 = 8+4 = 12
        //r4 = 12+8 = 20
        //r5 = 20+1 = 21 :)

        //dealer chooses s and creates sessionInfo
        dealer.defineSecrets(secrets,definitions,CommitmentType.Pedersen);
        dealer.addParticipantToSession(idP1,p1);
        dealer.addParticipantToSession(idP2,p2);

        //The dealer creates shares
        sessionInfo =  dealer.prepareMultipartyOperationObjects();

        //The dealer creates shares
        p1.addSessionInfo(idP1, sessionInfo);
        p2.addSessionInfo(idP2, sessionInfo);

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1 and P2
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());

        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1 and P2
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());

        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1 and P2
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());

        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1 and P2
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());

        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1 and P2
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());

        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());

        //Share secrets between P1 and P2
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShare(), p2.distributeRndShare());
        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShare(), p1.distributeRndShare());

        //Reconstruct by P1 and P2
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s2_rec = p2.reconstructFinalResult();

        Assert.assertEquals(21, s1_rec.intValue());
        Assert.assertEquals(21, s2_rec.intValue());
    }

    @Test
    public void sumShare0HashCommitment() throws Exception {

        secrets.put("s1", BigInteger.valueOf(0));
        secrets.put("s2", BigInteger.valueOf(0));

        MultipartyOperation multipartyOperation = new MultipartyOperation("r1", "s1", "s2", Operation.Sum);
        definitions.add(multipartyOperation);

        //dealer chooses s and creates sessionInfo
        dealer.defineSecrets(secrets,definitions,CommitmentType.Hash256);
        dealer.addParticipantToSession(idP1,p1);
        dealer.addParticipantToSession(idP2,p2);

        //The dealer creates shares
        sessionInfo =  dealer.prepareMultipartyOperationObjects();
        p1.addSessionInfo(idP1, sessionInfo);
        p2.addSessionInfo(idP2, sessionInfo);

        //The dealer creates shares
        dealer.prepareMultipartyOperationObjects();
        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1 and P2
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());

        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());

        //Share secrets between P1 and P2
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShare(), p2.distributeRndShare());
        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShare(), p1.distributeRndShare());

        //Reconstruct by P1 and P2
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s2_rec = p2.reconstructFinalResult();

        Assert.assertEquals(0, s1_rec.intValue());
        Assert.assertEquals(0, s2_rec.intValue());
    }

    @Test
    public void sumTwiceTest() throws Exception{

        secrets.put("a",BigInteger.valueOf(8));
        secrets.put("b",BigInteger.valueOf(12));
        secrets.put("c",BigInteger.valueOf(1));

        definitions.add(new MultipartyOperation("r1","a","b", Operation.Sum));
        definitions.add(new MultipartyOperation("r2","r1","c", Operation.Sum));

        dealer.defineSecrets(secrets,definitions,CommitmentType.Pedersen);
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

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares of a and b to P1, P2 and P3
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());
        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());
        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

        //Reconstruct by P1 and P2
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShare(), p2.distributeRndShare());
        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShare(), p1.distributeRndShare());

        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s2_rec = p2.reconstructFinalResult();

        Assert.assertEquals(21, s1_rec.intValue());
        Assert.assertEquals(21, s2_rec.intValue());
    }

    @Test
    public void sumThreeTimesTest() throws Exception{

        secrets.put("a",BigInteger.valueOf(7));
        secrets.put("b",BigInteger.valueOf(3));
        secrets.put("c",BigInteger.valueOf(2));

        //Dealer defines queries to calculate
        definitions.add(new MultipartyOperation("r1","a","b", Operation.Sum));
        definitions.add(new MultipartyOperation("r2","r1","c", Operation.Sum));
        definitions.add(new MultipartyOperation("r3","r1","r2", Operation.Sum));


        //r3 = r1 * r2 = r1 * r1 * c = a + b + a + b + c = 7 + 3 + 7 + 3 + 2 = 22

        dealer.defineSecrets(secrets,definitions,CommitmentType.Pedersen);
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

        //Reconstruct by P1 and P2
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShare(), p2.distributeRndShare());
        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShare(), p1.distributeRndShare());

        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s2_rec = p2.reconstructFinalResult();

       Assert.assertEquals(22,s1_rec.intValue());
       Assert.assertEquals(22,s2_rec.intValue());
    }

    @Test
    public void complexSumTest() throws Exception{

        //Dealer defines secrets to share
        secrets.put("a",BigInteger.valueOf(6));
        secrets.put("b",BigInteger.valueOf(7));
        secrets.put("c",BigInteger.valueOf(5));
        secrets.put("d",BigInteger.valueOf(3));

        //Dealer defines queries to calculate
        //Dealer defines queries to calculate
        definitions.add(new MultipartyOperation("r1","a","b", Operation.Sum));
        definitions.add(new MultipartyOperation("r2","r1","c", Operation.Sum));
        definitions.add(new MultipartyOperation("r3","r1","r2", Operation.Sum));
        definitions.add(new MultipartyOperation("s","r3","d", Operation.Sum));

        //s = r3 +d = r1 + r2 + d = a + b + c + d + d = 6 + 7 + 5 + 3 + 3 = 24

        dealer.defineSecrets(secrets,definitions,CommitmentType.Pedersen);
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

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares of a and b to P1, P2 and P3
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());
        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());
        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares of c and d to P1, P2 and P3
        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());
        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());
        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

        shares = dealer.getNextMultipartyOperationObjects();

        p1.addLocalSharesOfSums(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());
        p2.addLocalSharesOfSums(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());
        p3.addLocalSharesOfSums(shares.getMultipartyOperationObject(p3.getSecretId()));
        p3.addCommitmentShares(shares.getCommitmentValues());

        //Reconstruct by P1 and P2
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShare(), p2.distributeRndShare());
        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShare(), p1.distributeRndShare());

        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s2_rec = p2.reconstructFinalResult();

        Assert.assertEquals(34,s1_rec.intValue());
        Assert.assertEquals(34,s2_rec.intValue());
    }
}
