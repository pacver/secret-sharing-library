package ffapl.lib.interfaces;

import ffapl.types.*;

/**
 * Specifies an interface for an Symbol in FFapl Symboltable
 * @author Alexander Ortner
 * @version 1.0
 *
 */
public interface ISymbol {
	
	/*Symbol Kinds*/
    int PROGRAM       = 0;
	int PROCEDURE     = 1;
	int FUNCTION      = 2;
	int VARIABLE      = 3;
	int CONSTANT      = 4;
	int PARAMETER     = 5; // formal parameter
	int BLOCK         = 6;

	
	/**
	 * String representation of symbol kind
	 */
    String[] kindArray ={
		"program",
		"procedure",
		"function",
		"variable",
		"constant",
		"paramenter",
		"block",
	};
	
	/**
	 * Returns kind of a Symbol
	 * @return
	 */
    int getKind();
	
	/**
	 * Returns String representation of the Symbol kind
	 * @return
	 */
    String getKindStr();
	
	/**
	 * Returns next Symbol linked to this Symbol,
	 * @return
	 */
    ISymbol next();
    
    /**
     * points to the father
     * @return
     */
    ISymbol scope();
    
    
    /**
     * Returns Type of the Symbol
     * @return
     */
    Type getType();
    
    
    /**
     * Returns the Token of the Symbol
     * @return
     */
    IToken getToken();
    
    /**
     * Returns the pointer to the Child
     * @return
     */
    ISymbol local();
    
    /**
     * Returns the level of the Scope
     * @return
     */
    int level();
    
    /**
     * Returns the Symbol name
     * @return
     */
    String getName();
    
    /**
     * Returns the id of the Symbol
     * @return
     */
    String getID();
    
    
    /**
     * Returns true if Symbol is Global, false otherwise;
     * @return
     */
    boolean isGlobal();
    
    /**
     * Returns true if Symbol is Shielded, means do not look above the Symbol
     * in the scope case
     * @return
     */
    boolean isShielded();
    
    /**
     * Returns true if Symbol is an Reference in case of
     * Array, Record, etc.
     * @return
     */
    boolean isReference();
    
   /**
    * Returns true if the symbol is initialized
    * @return
    */
   boolean isInitialized();
    
    /**
     * Returns if the Symbol is readonly
     * @return
     */
    boolean readonly();
    
    /**
     * Sets the isGlobal value for the Symbol, true if the Symbol is Global
     * false otherwise
     * @param isGlobal
     */
    void setGlobal(boolean isGlobal);
    
    /**
     * Sets the isShielded value for the Symbol, true if the Symbol is shielded,
     * which means, do not look above the Symbol in case of scoping.
     * @param isShielded
     */
    void setShielded(boolean isShielded);
    
    /**
     * specifies if the symbol is readonly
     * @param readonly
     */
    void setReadonly(boolean readonly);
    
    /**
     * Sets the scope of the Symbol
     * @param symbol
     */
    void setScope(ISymbol symbol);
    
    /**
     * Sets the kind of the Symbol
     * @param kind
     */
    void setKind(int kind);
    
    /**
     * Sets the pointer to the child
     * @param symbol
     */
    void setLocal(ISymbol symbol);
    
    /**
     * links a Symbol to this Symbol, used especially for parameter
     * list for functions and procedures.
     * @param nSymbol
     */
    void setNext(ISymbol symbol);
    
    /** 
     * Set true if the Symbol is a Reference
     * @param isReference
     */
    void setReference(boolean isReference);
    
    /**
     * Set true if the Symbol is initialized;
     * @param isInitialized
     */
    void setInitialized(boolean isInitialized);
    
    /**
     * Set the Symbol Type
     * @param sType
     */
    void setType(Type type);
    
    /**
     * Set scope level
     * @param i
     */
    void setLevel(int i);
    
    /**
     * Checks if the Symbol is equal to the input. Name and structure are controlled.
     * @param symbol
     * @return
     */
    boolean equals(ISymbol symbol);
    
    /**
     * Checks if the Symbol is similar to the input. Only name is controlled
     * @param symbol
     * @return
     */
    boolean similarTo(ISymbol symbol);
    
    /**
     * Returns String representation of the Symbol
     * only for debug issues
     * @return
     */
    String toString();
    
    /**
     * Clones the Symbol
     * @return
     */
    ISymbol clone();
    
    /**
     * Sets the offset of the symbol
     * @param offset
     */
    void setOffset(int offset);
    
    /**
     * Returns the offset of the symbol
     * @return
     */
    int getOffset();
    
    /**
     * Returns true if the offset is set, false otherwise
     * @return
     */
    boolean setOffset();

    /**
     * resets the offset to default value
     */
    void resetOffset();
    
}
