/**
 * 
 */
package ffapl.lib.interfaces;

import ffapl.types.Type;

/**
 * Represents a Attribute 
 * @author Alexander Ortner
 * @version 1.0
 *
 */
public interface IAttribute {

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
    void setType(Type type);
	
	/**
	 * Sets the kind of the Attribute
	 * @param kind
	 */
    void setKind(int kind);
	
	/**
	 * Returns the Type of the Attribute
	 * @return
	 */
    Type getType();
	
	/**
	 * Returns the Kind of the Attribute
	 * @return
	 */
    int getKind();
	
	/**
	 * Sets the register of the Attribute
	 * @param register
	 */
    void setRegister(byte register);
	
	/**
	 * Return the Register of the Attribute
	 * @return
	 */
    byte getRegister();
	
	/**
	 * Returns true if the operand can be evaluated at compile time
	 * @return
	 */
    boolean isConstant();
	
}
