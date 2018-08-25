package ffapl.lib.interfaces;

/**
 * Interface to the <code>Token</code> class generated by JavaCC 5.0.
 * @author Alexander Ortner
 *
 */
public interface IToken {
	/**
	 * Return the token kind, as defined by the JavaCC API.
	 */
    int getKind();

	/**
	 * Return the string representation of the token, as defined
	 * by the JavaCC API.
	 */
    String toString();
	
	/**
	 * Return the source line number where the token begins.
	 */
    int line();

	/**
	 * Return the source code column number where the token begins.
	 */
    int column();
}