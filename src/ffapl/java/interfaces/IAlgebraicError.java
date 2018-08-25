package ffapl.java.interfaces;

/** 
 * Generic FFAPL Java Error Interface
 * It defines error numbers and common methods of exception and error types
 * thrown by the FFAPL execution.
 * 
 * @author Alexander Ortner
 */
public interface IAlgebraicError {

/* ---- Algebraic Error Numbers ---- */
	
	/** execution interrupted */
    int INTERRUPT 				 = 1099;
	
	/** internal Error */
    int INTERNAL                 = 1100;
	
	/** Divide by zero. */
    int DIVZERO                  = 1101;
	
	/** Number not Prime. */
    int NOTPRIME                 = 1102;
	
	/** Polynom not irreducible. */
    int NOTIRREDUCIBLE           = 1103;
	
	/** Unequal characteristics. */
    int CHARACTERISTIC_UNEQUAL   = 1104;
	
	/** Types are not compatible **/
    int TYPES_INCOMPATIBLE       = 1105;
	
	/** no multiplicative inverse available **/
    int NO_MULTINVERSE           = 1106;
	
	/** Negative not allowed **/
    int NEGATIVE_EXPONENT        = 1107;
	
	/** Division not reasonable **/
    int DIVISION_NOT_REASONABLE   = 1108;
	
	/** Division not reasonable **/
    int MOD_NOT_REASONABLE        = 1109;
	
	/** Illegal Cast **/
    int CAST_NOT_POSSIBLE        = 1110;

	/** to high exponent for calculation **/
    int TO_HIGH_EXPONENT         = 1111;
	
	/** minimum is greater than maximum */
    int MIN_GREATER_EQUAL_MAX    = 1112;
	
	/** value must be > 0 */
    int VAL_LESS_EQUAL_ZERO      = 1113;
	
	/** value must be >= 0 */
    int VAL_LESS_ZERO      		= 1114;
	
	/** length too high for array */
    int ARR_LENGTH_TOOHIGH      = 1115;
	
	/** Array index out of bound	 */
    int ARR_INDEX_OUT_OF_BOUNDS = 1116;
	
	/** Value is null	 */
    int VALUE_IS_NULL			= 1117;

	/** Some coeffiecients has no inverse **/
    int NO_MULTINVERSE_COEFF    = 1118;

	/** PrimeFactors are unequal to **/
    int PRIMITIVE 				= 1119;

	/** Exponent < 0 **/
    int EXPONENT_LESS_ZERO 		= 1120;
	
	/** underfull AES block  **/
    int ILLEGAL_AES_INPUT		= 1132;

	/** WARNING **/
    int WARNING_IMPLICIT_CAST	= 2000;
	
	/** WARNING **/
    int WARNING_OVERSIZE_AES_KEY	= 2002;
	

	int WRONG_EC_FIELD		 	= 1121;

	int EC_POINT_ERROR 			= 1122;
	
	int INVALID_EC_RESIDUE_CLASS_CHARACTERISTIC 			= 1123;

	int WEIERSTRASS_SINGULAR		= 1124;
        
    int RESIDUE_CLASS_CHARACTERISTIC_POSITIVE = 1125;

    int NEGATIVE_MODUL = 1126;

    int SQUARE_ROOT_DOES_NOT_EXIST = 1127;

    int EC_FIELD_ERROR = 1128;
    
    int EC_PAIRING_PARAMETER_NOT_VALID = 1129;
    
    int EC_BASEFIELD_ERROR = 1130;

    int EC_GET_PARAMETER_ERROR = 1131;
	
	/** 
	 * Return the compiler error number.
	 * @return error number
	 */
    int errorNumber();
	
	/** 
	 * Return the detailed error message. 
	 * @return error message
	 * */
    String getErrorMessage();
	
}
