/**
 * 
 */
package ffapl.ast.nodes;

import ffapl.exception.FFaplException;
import ffapl.visitor.interfaces.IRetArgVisitor;
import ffapl.visitor.interfaces.IRetVisitor;
import ffapl.visitor.interfaces.IVoidArgVisitor;
import ffapl.visitor.interfaces.IVoidVisitor;

/**
 * Represents a node for <Code>Decl</Code>
 * @author Alexander Ortner
 * @version 1.0
 *
 */
public class ASTDecl extends FFaplNode {

	
	//public FFaplNodeToken _node1;
	public FFaplNodeToken _node1;
	public FFaplNodeListOpt _node2;
	public FFaplNodeToken _node3;
	public ASTDeclType _node4;
	public FFaplNodeToken _node5;
	
	/**
	 * Constructor
	 * @param id
	 * @param node1
	 * @param node2
	 * @param node3
	 * @param node4
	 * @param node5
	 */
	public ASTDecl(int id,  FFaplNodeToken node1,
			FFaplNodeListOpt node2, FFaplNodeToken node3, ASTDeclType node4,
			FFaplNodeToken node5) {
		super(id);
		_node1 = node1;
		_node2 = node2;
		_node3 = node3;
		_node4 = node4;
		_node5 = node5;
		//_node6 = node6;
		_node1.setParent(this);
		_node2.setParent(this);
		_node3.setParent(this);
		_node4.setParent(this);
		_node5.setParent(this);
		//_node6.setParent(this);
	}

	/* (non-Javadoc)
	 * @see ffapl.ast.nodes.interfaces.INode#accept(ffapl.visitor.interfaces.IVoidVisitor)
	 */
	@Override
	public void accept(IVoidVisitor visitor) throws FFaplException{
		visitor.visit(this);
	}

	/* (non-Javadoc)
	 * @see ffapl.ast.nodes.interfaces.INode#accept(ffapl.visitor.interfaces.IVoidArgVisitor, java.lang.Object)
	 */
	@Override
	public <A> void accept(IVoidArgVisitor<A> visitor, A argument) throws FFaplException{
		visitor.visit(this, argument);
	}

	/* (non-Javadoc)
	 * @see ffapl.ast.nodes.interfaces.INode#accept(ffapl.visitor.interfaces.IRetVisitor)
	 */
	@Override
	public <R> R accept(IRetVisitor<R> visitor) throws FFaplException{
		return visitor.visit(this);
	}

	/* (non-Javadoc)
	 * @see ffapl.ast.nodes.interfaces.INode#accept(ffapl.visitor.interfaces.IRetArgVisitor, java.lang.Object)
	 */
	@Override
	public <A, R> R accept(IRetArgVisitor<R, A> visitor, A argument) throws FFaplException{
		return visitor.visit(this, argument);
	}


}
