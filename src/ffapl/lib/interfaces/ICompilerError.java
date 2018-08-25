package ffapl.lib.interfaces;

/** Generic FFAPL compiler Error Interface
 * It defines error numbers and common methods of exception and error types
 * thrown by the FFAPL compiler.
 * 
 * @author Alexander Ortner
 */
public interface ICompilerError {

	/* ##### ERROR Numbers ##### */
	
	/* ---- Global Error Numbers ---- */
	
	/** Internal error. */
    int INTERNAL             = 100;
	/** Lexical error. */
    int LEXICAL              = 101;
	/** Syntax error. */
    int SYNTAX               = 102;
	/** Expected one*/
    int SYNTAXSINGLE         = 103;
	/** Expected one*/
    int INTERRUPT            = 104;
	
	/* ---- Errors Symbol checking  ----*/
	
	/** Symbol already declared. */
    int SYMBOL_EXISTS          = 110;
	/** Identifier not declared. */
    int IDENT_NOT_DECL         = 111;
	/** Symbol not initialized. */
    int SYMBOL_NOT_INIT        = 112;
	
	/* ---- Type error checking ----*/
	
	/** Illegal Use of Type */
    int TYPE_ILLEGAL_USE       			= 200;
	/** Readonly variable in Assignment */
    int READONLY_ASSIGN        			= 201;
	/** Expression before '[' is not an array type. */
    int SELECTOR_NOT_ARRAY     			= 202;
	/** Expression before '.' is not an record type. */
    int SELECTOR_NOT_RECORD    			= 203;
	/** Expression after '#' is not an array type. */
    int ARRAY_LEN_NOT_ARRAY    			= 204;
	/** Illegal operand type for unary operator. */
    int ILLEGAL_OP1_TYPE     			= 205;
	/** Illegal operand type for binary operator. */
    int ILLEGAL_OP2_TYPE      			= 206;
	/** Using procedure (not a function) in expression. */
    int PROC_NOT_FUNC_EXPR    			= 207;
	/** Type mismatch in assignment. */
    int TYPE_MISMATCH_ASSIGN  			= 208;
	/** Condition is not a boolean expression. */
    int COND_NOT_BOOL          			= 209;
	/** Returning none or invalid type from function. */
    int INVALID_RETURN_TYPE    			= 210;
	/** Type mismatch in assignment. */
    int TYPE_MISMATCH_FORSTATEMENT   	= 211;
	/**  Illegal use if type in an argument*/
    int ILLEGAL_TYPE_ARGUMENT			= 212;
	/** Implicit cast error **/
    int IMPLICIT_CAST_ERROR 			= 213;
	/** invalid subarray length **/
    int INVALID_SUBARRAY_LENGTH         = 214;
	/** argumentlist not allowed in function or procedure call **/
    int ARGUMENTLIST_NOT_ALLOWED 		= 215;
	/** invalid indeterminate variable**/
    int INVALID_INDETERMINATE			= 216;
	/** invalid position of break statement **/
    int BREAK_WRONG_POSITION			= 217;
	/** invalid ec-parameter type **/
    int WRONG_EC_PARAMETER_TYPE			= 218;
	/** invalid ec-parameter, only a1, a2, a3, a4, a6 **/
    int WRONG_EC_PARAMETER 				= 219;
	/**  **/
    int WRONG_EC_COORDINATE 			= 220;

	
	/* ##### End of error numbers ##### */
	
	
	
	/** 
	 * Return the compiler error number.
	 * @return error number
	 */
    int errorNumber();
	
	/**
	 * Return the source code line number where the error occurred.
	 * @return line number 
	 * */
    int errorLine();
	
	/** 
	 * Return the source code column number where the error occurred.
	 * @return column number 
	 * */
    int errorColumn();
	
	/** 
	 * Return the detailed error message. 
	 * @return error message
	 * */
    String getErrorMessage();
	
	/**
	 * Returns the error type
	 * @return
	 */
    String errorType();
}

