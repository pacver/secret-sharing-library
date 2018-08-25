package SSLibrary;

public class SSLibException extends Exception {

    private static final String SECRET_NOT_DEFINED = "Missing definition of secret(s) by dealer.";
    private static final String UNAUTHORIZED_DEALER_ACTION = "Only the dealer is allowed to define secrets, to create a session and to create shares of a secret.";
    private static final String TOO_FEW_COMBINERS = "At least two combiners must join the session. This session has been cancelled, because of too few combiners.";
    //private static final String TOO_FEW_PLAYERS = "At least t players must join a session with t combiners. This session has been cancelled, because of few few players";
    private static final String TOO_FEW_PLAYERS = "At least t+1 players must join a session with t combiners, when a product is shared. This session has been cancelled, because of too few players";
    private static final String TOO_FEW_VALID_VALUE_SETS = "The participant must hold at least t-1 valid shares for the secret reconstruction, when t combiners joined the session.";
    private static final String SHARE_OF_DEALER_INVALID = "A share distributed by the dealer does not match the commitment value.";
    private static final String UNAUTHORIZED_COMBINER_ACTION = "Only a combiner is allowed to reconstruct the secret.";
    private static final String LOCAL_SHARES_MISSING = "Players/Combiners must first receive their local share from the dealer via addSimpleLocalShare(), addLocalSharesOfSums() or addLocalSharesOfProducts().";
    private static final String NO_ACTIVE_SESSION = "Participant is not part of an active session. Participant must join a session first.";
    private static final String UNAUTHORIZED_PLAYER_ACTION = "Only a player is allowed to receive local shares.";
    private static final String WRONG_SESSION_TYPE = "This method is not valid for the type of the current session.";
    private static final String SHARE_OF_PARTICIPANT_INVALID = "A share distributed by a participant does not match the commitment value shared by the dealer.";
    private static final String WRONG_DISTRIBUTION_METHOD = "Participant holds specific shares for each participant. Please call the method distributeSecretShareForSpecificParticipant() instead.";
    private static final String ONGOING_SESSION = "The participant is already part of an active session.";

    public SSLibException(SSLibExceptionType type) {
        super(String.format("%s.%s() has thrown an exception of type %s:%s",
                Thread.currentThread().getStackTrace()[2].getClassName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                type.toString(),
                getExceptionMessage(type)));
    }

    private static String getExceptionMessage(SSLibExceptionType SSLibExceptionType) {
        String message;
        switch (SSLibExceptionType) {
            case MissingSecretDefinition:
                message = SECRET_NOT_DEFINED;
                break;
            case UnauthorizedDealerAction:
                message = UNAUTHORIZED_DEALER_ACTION;
                break;
            case TooFewCombiners:
                message = TOO_FEW_COMBINERS;
                break;
            case TooFewValidValueSets:
                message = TOO_FEW_VALID_VALUE_SETS;
                break;
            case ShareOfTheDealerInvalid:
                message = SHARE_OF_DEALER_INVALID;
                break;
            case UnauthorizedCombinerAction:
                message = UNAUTHORIZED_COMBINER_ACTION;
                break;
            case TooFewPlayers:
                message = TOO_FEW_PLAYERS;
                break;
            case LocalSharesMissing:
                message = LOCAL_SHARES_MISSING;
                break;
            case NoActiveSession:
                message = NO_ACTIVE_SESSION;
                break;
            case UnauthorizedPlayerAction:
                message = UNAUTHORIZED_PLAYER_ACTION;
                break;
            case WrongSessionType:
                message = WRONG_SESSION_TYPE;
                break;
            case ShareOfParticipantInvalid:
                message = SHARE_OF_PARTICIPANT_INVALID;
                break;
            case WrongDistributionMethod:
                message = WRONG_DISTRIBUTION_METHOD;
                break;
            case OngoingSession:
                message = ONGOING_SESSION;
                break;
            default:
                message = "Unknown exception occured!";
                break;
        }
        return message;
    }
}
