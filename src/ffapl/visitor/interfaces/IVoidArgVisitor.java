package ffapl.visitor.interfaces;

import ffapl.ast.nodes.*;
import ffapl.exception.FFaplException;


public interface IVoidArgVisitor <A>{

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTAddExpr node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTAddOp node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTArgumentList node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTArrayLen node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTAssignment node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTBlock node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTComplexAlgebraicType node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTRandomGeneratorType node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTCondAndExpr node, A argument) throws FFaplException;

	void visit(ASTCondOrExpr node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTCondition node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException
	 */
    void visit(ASTCreationExpr node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException
	 */
    void visit(ASTExprComplexAType node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException
	 */
    void visit(ASTExprRandomGType node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTDecl node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTConstDecl node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException
	 */
    void visit(ASTConstType node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException
	 */
    void visit(ASTContainerType node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException
	 */
    void visit(ASTParamType node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException
	 */
    void visit(ASTArrayType node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException
	 */
    void visit(ASTAlgebraicType node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTElseBlock node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTEqualExpr node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTEqualOp node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTExpr node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTFormalParam node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTFormalParamList node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTForStatement node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTFunc node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTFuncBlock node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTGF node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTEC node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTECPoint node, A argument) throws FFaplException;
	void visit(ASTECBaseField node, A argument) throws FFaplException;
	void visit(ASTECAssignment node, A argument) throws FFaplException;

	void visit(ASTECPAI node, A argument) throws FFaplException;
	void visit(ASTECRandom node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTIdTerm node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTIfStatement node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTLiteral node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTMulExpr node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTMulOp node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTPolynomial node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTPowExpr node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTPrimaryExpr node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTPrimitiveType node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTProc node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTProcFuncCall node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTProgram node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTPsRandomGenerator node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTRandom node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTRandomGenerator node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTRecord node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTRelExpr node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTRelOp node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTReturnStatement node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTBreak node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	* @param argument
	 * @throws FFaplException */
    void visit(ASTSelector node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	* @param argument
	 * @throws FFaplException */
    void visit(ASTSameAs node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTStatement node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTBlockStatement node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTStatementList node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTTerm node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTDeclType node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTUnaryExpr node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(ASTWhileStatement node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(FFaplNodeChoice node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(FFaplNodeList node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(FFaplNodeListOpt node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(FFaplNodeOpt node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(FFaplNodeSequence node, A argument) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException */
    void visit(FFaplNodeToken node, A argument) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argument
	 * @throws FFaplException
	 */
    void visit(FFaplNodeType node, A argument) throws FFaplException;

	
}
