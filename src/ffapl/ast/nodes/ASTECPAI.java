package ffapl.ast.nodes;

import ffapl.exception.FFaplException;
import ffapl.visitor.interfaces.IRetArgVisitor;
import ffapl.visitor.interfaces.IRetVisitor;
import ffapl.visitor.interfaces.IVoidArgVisitor;
import ffapl.visitor.interfaces.IVoidVisitor;

public class ASTECPAI extends FFaplNode {
	 

	  public ASTECPAI(int id)
	  {
		  super(id);
	  }
	  



	@Override
	public void accept(IVoidVisitor visitor) throws FFaplException {
		visitor.visit(this);
		
	}


	@Override
	public <A> void accept(IVoidArgVisitor<A> visitor, A argument) throws FFaplException {
		visitor.visit(this, argument);
	}


	@Override
	public <R> R accept(IRetVisitor<R> visitor) throws FFaplException {
		return visitor.visit(this);
		//return null;
	}


	@Override
	public <A, R> R accept(IRetArgVisitor<R, A> visitor, A argument) throws FFaplException {
		return visitor.visit(this, argument);
		//return null;
	}

}