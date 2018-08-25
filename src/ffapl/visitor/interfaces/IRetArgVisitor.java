package ffapl.visitor.interfaces;

import java.util.Vector;

import ffapl.ast.nodes.*;
import ffapl.exception.FFaplException;
import ffapl.lib.interfaces.IAttribute;

public interface IRetArgVisitor <R,A> {

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTAddExpr node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R><
	 * @throws FFaplException
	 */
    R visit(ASTAddOp node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTArgumentList node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTArrayLen node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTAssignment node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTBlock node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTComplexAlgebraicType node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTRandomGeneratorType node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTCondAndExpr node, A argument) throws FFaplException;
	
	R visit(ASTCondOrExpr node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTCondition node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTCreationExpr node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTExprComplexAType node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTExprRandomGType node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTDecl node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTConstDecl node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTConstType node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTContainerType node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTParamType node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTArrayType node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTAlgebraicType node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTElseBlock node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTEqualExpr node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTEqualOp node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTExpr node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 */
    R visit(ASTFormalParam node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTFormalParamList node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTForStatement node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTFunc node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTFuncBlock node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTGF node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTEC node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTECPoint node, A argument) throws FFaplException;
	R visit(ASTECBaseField node, A argument) throws FFaplException;
	R visit(ASTECAssignment node, A argument) throws FFaplException;
	
	R visit(ASTECPAI node, A argument) throws FFaplException;
	R visit(ASTECRandom node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTIdTerm node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTIfStatement node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTLiteral node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTMulExpr node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTMulOp node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTPolynomial node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTPowExpr node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTPrimaryExpr node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTPrimitiveType node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTProc node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTProcFuncCall node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTProgram node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTPsRandomGenerator node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 */
    R visit(ASTRandom node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTRandomGenerator node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTRecord node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTRelExpr node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTRelOp node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTReturnStatement node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTBreak node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTSameAs node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTSelector node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTStatement node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTBlockStatement node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTStatementList node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTTerm node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTDeclType node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTUnaryExpr node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTWhileStatement node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(FFaplNodeChoice node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(FFaplNodeList node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(FFaplNodeListOpt node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(FFaplNodeOpt node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(FFaplNodeSequence node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(FFaplNodeToken node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @return Values of type <R>
	 * @throws FFaplException
	 */
    R visit(FFaplNodeType node, A argument) throws FFaplException;


	
	
}
