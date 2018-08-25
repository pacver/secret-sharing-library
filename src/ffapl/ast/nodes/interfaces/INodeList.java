/**
 * 
 */
package ffapl.ast.nodes.interfaces;

import java.util.Iterator;


/**
 * @author Alexander Ortner
 * @version 1.0
 *
 */
public interface INodeList extends INode {

	/**
	 * adds a Node to the list
	 * @param node
	 */
    void addNode(INode node);
	
	/**
	 * Returns the size of the Node list
	 * @return
	 */
    int size();
	
	/**
	 * returns a Iterator for the Node list
	 * @return
	 */
    Iterator<INode> iterator();
	
	/**
	 * Returns the Node at position <Code> i </Code>
	 * @param i
	 * @return <null> if no element exists
	 */
    INode elementAt(int i);
	
}
