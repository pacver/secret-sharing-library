/**
 * 
 */
package ffapl.java.interfaces;

/**
 * Level of FFapl Logging
 * @author Alexander Ortner
 * @version 1.0
 *
 */
public interface ILevel {

	int RESULT 	= 0;
	
	int ERROR = 1;
	
	int WARNING	= 2;

	
	String[] Level_name = {
		"result",
		"error",
		"warning"
	};
}
