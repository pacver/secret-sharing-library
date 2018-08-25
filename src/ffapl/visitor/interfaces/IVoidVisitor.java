package ffapl.visitor.interfaces;

import ffapl.ast.nodes.*;
import ffapl.exception.FFaplException;

public interface IVoidVisitor {

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTAddExpr node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTAddOp node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTArgumentList node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTArrayLen node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTAssignment node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTBlock node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTComplexAlgebraicType node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTRandomGeneratorType node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTCondAndExpr node) throws FFaplException;
	
	
	void visit(ASTCondOrExpr node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTCondition node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException
	 */
    void visit(ASTCreationExpr node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException
	 */
    void visit(ASTExprComplexAType node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException
	 */
    void visit(ASTExprRandomGType node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTDecl node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTConstDecl node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException
	 */
    void visit(ASTConstType node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException
	 */
    void visit(ASTContainerType node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException
	 */
    void visit(ASTParamType node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException
	 */
    void visit(ASTArrayType node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException
	 */
    void visit(ASTAlgebraicType node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTElseBlock node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTEqualExpr node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTEqualOp node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTExpr node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTFormalParam node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTFormalParamList node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTForStatement node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTFunc node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTFuncBlock node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTGF node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTEC node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTECPoint node) throws FFaplException;
	
	void visit(ASTECPAI node) throws FFaplException;


	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTIdTerm node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTIfStatement node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTLiteral node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTMulExpr node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTMulOp node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTPolynomial node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTPowExpr node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTPrimaryExpr node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTPrimitiveType node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTProc node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTProcFuncCall node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTProgram node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTPsRandomGenerator node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTRandom node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTRandomGenerator node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTRecord node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTRelExpr node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTRelOp node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTReturnStatement node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTBreak node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTSameAs node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTSelector node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTStatement node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTBlockStatement node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTStatementList node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTTerm node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTDeclType node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTUnaryExpr node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(ASTWhileStatement node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(FFaplNodeChoice node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(FFaplNodeList node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(FFaplNodeListOpt node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(FFaplNodeOpt node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(FFaplNodeSequence node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException */
    void visit(FFaplNodeToken node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException
	 */
    void visit(FFaplNodeType node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @throws FFaplException
	 */
    void visit(ASTECRandom astecRandom);

	void visit(ASTECBaseField node);

	void visit(ASTECAssignment node);

	
}
