package ffapl.java.interfaces;


/**
 * Interface for Java Attribute
 * @author Alexander Ortner
 * @version 1.0
 *
 */
public interface IJAttribute {
	/**
	 * Invalid Attribute
	 */
    int NONE = 0;
	
	/**
	 * Constant Operand
	 */
    int CONSTANT = 1;
	
	/**
	 * register or stack operand
	 */
    int REGISTER = 2;
	
	/**
	 * memory operand
	 */
    int ADDRESS = 3;
	
	/**
	 * Attribute Object holds the Array Base
	 */
    int ARRAYELEMENT = 4;
		
	/**
	 * Attribute Object holds the Record Base
	 */
    int RECORDELEMENT = 5;
	
	/**
	 * Only specifies a Type
	 */
    int TYPE = 6;
	
	/**
	 * Sets the Type of the Attribute
	 * @param type
	 */
    void setType(Object type);
	
	/**
	 * Sets the kind of the Attribute
	 * @param kind
	 */
    void setKind(int kind);
	
	/**
	 * Returns the Type of the Attribute
	 * @return
	 */
    Object getType();
	
	/**
	 * Returns the Kind of the Attribute
	 * @return
	 */
    int getKind();
	
	/**
	 * Sets the offset of the Attribute
	 * @param register
	 */
    void setOffset(int offset);
	
	/**
	 * returns the offset of the Attribute
	 * @return
	 */
    int getOffset();
	
	/**
	 * returns true if the Attribute is Global
	 * @return
	 */
    boolean isGlobal();
	
	/**
	 * sets the state of the Attribute
	 * @param global
	 */
    void setGlobal(boolean global);
	
	
	/**
	 * Returns true if the operand can be evaluated at compile time
	 * @return
	 */
    boolean isConstant();
}
