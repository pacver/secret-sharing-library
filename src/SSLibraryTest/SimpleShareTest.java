package SSLibraryTest;

import SSLibrary.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class SimpleShareTest {

    Dealer dealer;
    Participant p1;
    Participant p2;
    BigInteger idP1;
    BigInteger idP2;
    HashMap<String, BigInteger> secrets;
    ArrayList<MultipartyOperation> definitions;
    SessionInfo sessionInfo;
    MultipartyOperationObjectList shares;

    @Before
    public void setUp() {

        dealer = new Dealer();

        p1 = new Participant(true);
        p2 = new Participant(true);
        idP1 = BigInteger.valueOf(1);
        idP2 = BigInteger.valueOf(2);

        secrets = new HashMap<>();
        definitions = new ArrayList<>();
    }

    @Test
    public void simpleShare21PedersenCommitment() throws Exception {
        secrets.put("s1",BigInteger.valueOf(21));
        definitions.add(new MultipartyOperation("s1","s1",null, Operation.None));

        //The dealer defines the secrets and adds participants to the sessionInfo
       dealer.defineSecrets(secrets, definitions, CommitmentType.Pedersen);
       dealer.addParticipantToSession(idP1,p1);
       dealer.addParticipantToSession(idP2,p2);

        //The dealer creates shares
        SessionInfo sessionInfo =  dealer.prepareMultipartyOperationObjects();
        p1.addSessionInfo(idP1, sessionInfo);
        p2.addSessionInfo(idP2, sessionInfo);

        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1 and P2
        p1.addSimpleLocalShare(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());

        p2.addSimpleLocalShare(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());

        //Share secrets between P1 and P2
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShare(), p2.distributeRndShare());
        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShare(), p1.distributeRndShare());

        //Reconstruct by P2 and P3
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s2_rec = p2.reconstructFinalResult();

        Assert.assertEquals(21,s1_rec.intValue());
        Assert.assertEquals(21,s2_rec.intValue());
    }

    @Test
    public void simpleShare0HashCommitment() throws Exception {
        secrets.put("s1", BigInteger.valueOf(0));
        definitions.add(new MultipartyOperation("s1", "s1", null, Operation.None));

        dealer.defineSecrets(secrets,definitions,CommitmentType.Hash256);
        dealer.addParticipantToSession(idP1, p1);
        dealer.addParticipantToSession(idP2, p2);

        //The dealer creates shares
        SessionInfo sessionInfo = dealer.prepareMultipartyOperationObjects();
        p1.addSessionInfo(idP1, sessionInfo);
        p2.addSessionInfo(idP2, sessionInfo);

        //The dealer creates shares
        dealer.prepareMultipartyOperationObjects();
        shares = dealer.getNextMultipartyOperationObjects();

        //Give right shares to P1 and P2
        p1.addSimpleLocalShare(shares.getMultipartyOperationObject(p1.getSecretId()));
        p1.addCommitmentShares(shares.getCommitmentValues());

        p2.addSimpleLocalShare(shares.getMultipartyOperationObject(p2.getSecretId()));
        p2.addCommitmentShares(shares.getCommitmentValues());

        //Share secrets between P1 and P2
        p1.receiveShare(p2.getSecretId(), p2.distributeSecretShare(), p2.distributeRndShare());
        p2.receiveShare(p1.getSecretId(), p1.distributeSecretShare(), p1.distributeRndShare());

        //Reconstruct by P2 and P3
        BigInteger s1_rec = p1.reconstructFinalResult();
        BigInteger s2_rec = p2.reconstructFinalResult();

        Assert.assertEquals(0, s1_rec.intValue());
        Assert.assertEquals(0, s2_rec.intValue());
    }
}