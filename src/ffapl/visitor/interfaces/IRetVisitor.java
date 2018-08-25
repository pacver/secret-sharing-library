package ffapl.visitor.interfaces;

import ffapl.ast.nodes.*;
import ffapl.exception.FFaplException;

public interface IRetVisitor <R>{

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTAddExpr node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTAddOp node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTArgumentList node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTArrayLen node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTAssignment node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTBlock node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTComplexAlgebraicType node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTRandomGeneratorType node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTCondAndExpr node) throws FFaplException;
	
	R visit(ASTCondOrExpr node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTCondition node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTCreationExpr node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTExprComplexAType node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTExprRandomGType node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTDecl node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTConstDecl node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTConstType node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTContainerType node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTParamType node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTArrayType node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTAlgebraicType node) throws FFaplException;
	
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTElseBlock node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTEqualExpr node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTEqualOp node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTExpr node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTFormalParam node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTFormalParamList node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTForStatement node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTFunc node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTFuncBlock node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTGF node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTEC node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTECPoint node) throws FFaplException;
	
	R visit(ASTECPAI node) throws FFaplException;


	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTIdTerm node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTIfStatement node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTLiteral node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTMulExpr node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTMulOp node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTPolynomial node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTPowExpr node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTPrimaryExpr node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTPrimitiveType node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTProc node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTProcFuncCall node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTProgram node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTPsRandomGenerator node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTRandom node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTRandomGenerator node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTRecord node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTRelExpr node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTRelOp node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTReturnStatement node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTBreak node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	* @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTSameAs node) throws FFaplException;

	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	* @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTECBaseField node) throws FFaplException;

	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	* @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTECAssignment node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	* @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTSelector node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTStatement node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTBlockStatement node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTStatementList node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTTerm node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTDeclType node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTUnaryExpr node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(ASTWhileStatement node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(FFaplNodeChoice node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(FFaplNodeList node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(FFaplNodeListOpt node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(FFaplNodeOpt node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(FFaplNodeSequence node) throws FFaplException;

	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @return value of type <R>
	 * @throws FFaplException */
    R visit(FFaplNodeToken node) throws FFaplException;
	
	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argumen
	 * @return value of type <R>
	 * @throws FFaplException
	 */
    R visit(FFaplNodeType node) throws FFaplException;


	/**
	 * Visit Method for the specified input node type
	 * @param node
	 * @param argumen
	 * @return value of type <R>
	 * @throws FFaplException
	 */
    R visit(ASTECRandom astecRandom);


	
}
