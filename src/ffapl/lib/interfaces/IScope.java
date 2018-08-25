package ffapl.lib.interfaces;
import ffapl.exception.FFaplException;
/**
 * The Interface for a Scope in the Symbol Table
 * @author Alexander Ortner
 * @version 1.0
 *
 */
public interface IScope {
	
	/**
	 * The upscope of this scope
	 * @return null if scope is on top
	 */
    IScope upScope();
	
	/**
	 * return the child scope of this scope
	 * @return
	 */
    IScope downScope();
	
	/**
	 * returns if the scope is Global
	 * @return
	 */
    boolean isGlobal();
	
	/**
	 * Adds Symbol to the current Scope. If the scope is global the symbol
	 * will be also set to global.
	 * @param symbol
	 * @throws FFaplException 
	 * 			(SymbolExists) if Symbol exists in current scope.
	 * @throws FFaplException
	 *  		(internal) if internal error occurs
	 */
    void addSymbol(ISymbol symbol) throws FFaplException;
	
	/**
	 * lookup for a Symbol with the specified <Code>name</Code>
	 * @param name
	 * @return null if no Symbol exists with the specified <Code>name</Code>
	 * @throws FFaplException
	 *  		(internal) if internal error occurs
	 */
    ISymbol lookup(String name) throws FFaplException;
	
	/**
	 * lookup for a Symbol with the specified <Code>name</Code>
	 * @param symbol
	 * @return null if no Symbol exists with the same structure and name
	 * @throws FFaplException
	 *  		(internal) if internal error occurs
	 */
    ISymbol lookup(ISymbol symbol) throws FFaplException;
	
	/**
	 * Returns the parent Symbol of the Scope
	 * @return
	 */
    ISymbol parentSymbol();
	
	/**
	 * sets the parent scope of this scope
	 * @param upscope
	 */
    void setUpScope(IScope upscope);
	
	/**
	 * sets the child scope of this scope
	 * @param downscope
	 */
    void setDownScope(IScope downscope);
	
	
}
