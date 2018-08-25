/**
 * 
 */
package ffapl.java.interfaces;

import ffapl.types.FFaplTypeCrossTable;

/**
 * @author Alexander Ortner
 * @version 1.0
 *
 */
public interface IJavaType extends Cloneable{

	byte INTEGER			= 0;
	byte GALOISFIELD 		= 1;
	byte POLYNOMIAL 		= 2;
	byte POLYNOMIALRC 		= 3;
	byte POLYNOMIALRCPRIME 	= 4;
	byte PRIME 				= 5;
	byte RCPRIME 			= 6;
	byte RECORD				= 7;
	byte PSRANDOMGENERATOR	= 8;
	byte RANDOMGENERATOR	= 9;
	byte ARRAY				= 10;
	byte BOOLEAN			= 11;
	byte RESIDUECLASS		= 12;
	byte STRING				= 13;
	byte ELLIPTICCURVE		= 14;
	
	byte[] FFapl_Type_Compatibility = {
		FFaplTypeCrossTable.FFAPLINTEGER,
		FFaplTypeCrossTable.FFAPLGF,
		FFaplTypeCrossTable.FFAPLPOLYNOMIAL,
		FFaplTypeCrossTable.FFAPLPOLYNOMIALRESIDUE,
		-1,
		FFaplTypeCrossTable.FFAPLPRIME,
		-1,
		FFaplTypeCrossTable.FFAPLRECORD,
		FFaplTypeCrossTable.FFAPLPSRANDOMG,
		FFaplTypeCrossTable.FFAPLRANDOMG,
		FFaplTypeCrossTable.FFAPLARRAY,
		FFaplTypeCrossTable.FFAPLBOOLEAN,
		FFaplTypeCrossTable.FFAPLRESIDUECLASS,
		FFaplTypeCrossTable.FFAPLSTRING,
		FFaplTypeCrossTable.FFAPLEC		
	};
	
	
	/**
	 * returns the Type ID of the Type
	 * @return
	 */
    int typeID();
	
	/**
	 * Returns General Info about Type
	 * @return
	 */
    String classInfo();
	
	/**
	 * returns Clone of the Type
	 * @return
	 */
    IJavaType clone();
	
	/**
	 * returns if two types are equal
	 * e.g. Z(3) is equal to Z(3) but not to Z(5)
	 * @param type
	 * @return
	 */
    boolean equalType(Object type);
}
