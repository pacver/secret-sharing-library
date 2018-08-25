package ffapl.lib.interfaces;

import ffapl.java.classes.Array;
import ffapl.java.exception.FFaplAlgebraicException;
import ffapl.types.Type;

public interface ICodeInterpreter {
	
	/**
	 * Allocate space on the new procedure stack
	 * @param type
	 * @param symbol
	 * @return
	 * @throws FFaplAlgebraicException
	 */
    int allocStack(ISymbol symbol) throws FFaplAlgebraicException;
	
	/**
	 * Allocate space for an array an loads the base address on the stack
	 * @param type
	 * @param initArray
	 * @throws FFaplAlgebraicException
	 */
    void allocArray(Type type, Array initArray) throws FFaplAlgebraicException;
	
	/**
	 * Allocate space on the new procedure stack for FormalParam
	 * @param symbol
	 * @return
	 * @throws FFaplAlgebraicException
	 */
    int allocFormalParam(ISymbol symbol)  throws FFaplAlgebraicException;
	
	
	/**
	 * Allocate space on the new procedure stack, the place will be 
	 * a copy of the specified offset
	 * @param symbol
	 * @param offset
	 * @return
	 * @throws FFaplAlgebraicException
	 */
    int allocStack(ISymbol symbol, int offset)  throws FFaplAlgebraicException;
    
	/**
	 * Allocate space on the global memory 
	 * @param type
	 * @return
	 * @throws FFaplAlgebraicException
	 */
    int allocGlobal(Type type) throws FFaplAlgebraicException;
	
		
	/**
	 * loads value from procedure Stack or from the global memory
	 * @param offset
	 * @param global specifies if value is from global memory or procedure stack
	 * @throws FFaplAlgebraicException
	 */
    void loadValue(int offset, boolean global) throws FFaplAlgebraicException;
	
	/**
	 * runtime effect: b = pop(); a = pop(); push(a[b]);
	 * @throws FFaplAlgebraicException
	 */
    void loadArrayElement() throws FFaplAlgebraicException;
	
	/**
	 * loads a copy of the value from procedure Stack or from the global memory
	 * @param offset
	 * @param global specifies if value is from global memory or procedure stack
	 * @throws FFaplAlgebraicException
	 */
    void loadCopy(int offset, boolean global) throws FFaplAlgebraicException;
	
	/**
	 * stores value on procedure stack or in the global memory
	 * @param offset
	 * @param global
	 * @throws FFaplAlgebraicException
	 */
    void storeValue(int offset, boolean global) throws FFaplAlgebraicException;
	
	/**
	 * stores formal parameter value on procedure stack
	 * @param offset
	 * @param global
	 * @throws FFaplAlgebraicException
	 */
    void storeFormalParamValue(int elementAt) throws FFaplAlgebraicException;
	
	/**
	 * Emit code for add operation on expression stack
	 * <pre>
     * Runtime effect: b = pop(), a = pop(), push(a + b)
     * </pre>
	 * @throws FFaplAlgebraicException
	 */
    void add() throws FFaplAlgebraicException;
	
	/**
	 * Emit code for sub operation on expression stack
	 * <pre>
     * Runtime effect: b = pop(), a = pop(), push(a - b)
     * </pre>
	 * @throws FFaplAlgebraicException
	 */
    void sub() throws FFaplAlgebraicException;
	
	
	/**
	 * Emit code for mul operation on expression stack
	 * <pre>
     * Runtime effect: b = pop(), a = pop(), push(a * b)
     * </pre>
	 * @throws FFaplAlgebraicException
	 */
    void mul() throws FFaplAlgebraicException;
	
	/**
	 * Emit code for div operation on expression stack
	 * <pre>
     * Runtime effect: b = pop(), a = pop(), push(a / b)
     * </pre>
	 * @throws FFaplAlgebraicException 
	 */
    void div() throws FFaplAlgebraicException;
	
	/**
	 * Emit code for neg operation on expression stack
	 * <pre>
     * Runtime effect: a = pop(), push( - a)
     * </pre>
	 * @throws FFaplAlgebraicException
	 */
    void neg() throws FFaplAlgebraicException;
	
	/**
	 * Emit code for mod operation on expression stack
	 * <pre>
     * Runtime effect: b = pop(), a = pop(), push(a Mod b)
     * </pre>
	 * @throws FFaplAlgebraicException
	 */
    void mod() throws FFaplAlgebraicException;
	
	/**
	 * Emit code for pow operation on expression stack
	 * <pre>
     * Runtime effect: b = pop(), a = pop(), push(a ^ b)
     * </pre>
	 * @throws FFaplAlgebraicException
	 */
    void pow() throws FFaplAlgebraicException;
	
	/**
	 * <pre>
     * Runtime effect: b = pop(), a = pop(), push(a && b)
     * </pre>
	 * @throws FFaplAlgebraicException
	 */
    void and() throws FFaplAlgebraicException;
	
	/**
	 * <pre>
     * Runtime effect: b = pop(), a = pop(), push(a || b)
     * </pre>
	 * @throws FFaplAlgebraicException
	 */
    void or() throws FFaplAlgebraicException;
	
	/**
	 * <pre>
     * Runtime effect: a = pop(), push(!a)
     * </pre>
	 * @throws FFaplAlgebraicException
	 */
    void not() throws FFaplAlgebraicException;
	
	/**
	 * <pre>
     * Runtime effect: b = pop(), a = pop(), push(a > b)
     * </pre>
	 * @throws FFaplAlgebraicException
	 */
    void isGreater() throws FFaplAlgebraicException;
	
	/**
	 * <pre>
     * Runtime effect: b = pop(), a = pop(), push(a >= b)
     * </pre>
	 * @throws FFaplAlgebraicException
	 */
    void isGreaterEqual() throws FFaplAlgebraicException;
	
	/**
	 * <pre>
     * Runtime effect: b = pop(), a = pop(), push(a == b)
     * </pre>
	 * @throws FFaplAlgebraicException
	 */
    void isEqual() throws FFaplAlgebraicException;
	
	/**
	 * <pre>
     * Runtime effect: b = pop(), a = pop(), push(a < b)
     * </pre>
	 * @throws FFaplAlgebraicException
	 */
    void isLess() throws FFaplAlgebraicException;
	
	/**
	 * <pre>
     * Runtime effect: b = pop(), a = pop(), push(a <= b)
     * </pre>
	 * @throws FFaplAlgebraicException
	 */
    void isLessEqual() throws FFaplAlgebraicException;
	
	/**
	 * push on expression Stack
	 * @param exp
	 */
    void pushStack(Object exp) ;
	
	/**
	 * pop from expression Stack
	 * @return top element of the stack
	 */
    Object popStack();
	
	/**
	 * peeks from expression Stack
	 * @return top element of the stack
	 */
    Object peekStack();
	
	/**
	 * Cast the second top Element of the Stack to the top element
	 * of the Stack
	 * @param typeID
	 * @throws FFaplAlgebraicException
	 */
    void castElementOnStackTo(int typeID) throws FFaplAlgebraicException;

	/**
	 * simulates an enter of a procedure or function
	 * @throws FFaplAlgebraicException
	 */
    void enterProcFunc() throws FFaplAlgebraicException;
	
	/**
	 * simulates an exit of a procedure or function
	 * @throws FFaplAlgebraicException
	 */
    void exitProcFunc() throws FFaplAlgebraicException;
	
	/**
	 * Simulates return of function.
	 * Runtime effect: rt = pop()
	 */
    void funcReturn();
	
	/**
	 * Runtime effect: push(rt);
	 */
    void loadReturn();

	/**
	 * runtime effect: c = pop(), b = pop(), a = pop(), a[b] = c
	 * @throws FFaplAlgebraicException
	 */
    void storeArrayElement() throws FFaplAlgebraicException;

	/**
	 * load array length
	 * @throws FFaplAlgebraicException
	 */
    void arrayLen() throws FFaplAlgebraicException;

	/**
	 * @param offset the offset to check
	 * @return true if offset is allocated, false otherwise
	 */
    boolean isStackOffsetAllocated(int offset);

	
	
}
