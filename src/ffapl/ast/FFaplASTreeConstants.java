package ffapl.ast;

/**
 * Tree constants
 * @author Alexander Ortner
 * @version 1.0
 *
 */
public interface FFaplASTreeConstants
{
  int FFT_RELOP = 0;
  int FFT_EQUALOP = 1;
  int FFT_ADDOP = 2;
  int FFT_MULOP = 3;
  int FFT_RANDOM = 4;
  int FFT_RANDOMGENERATOR = 5;
  int FFT_LITERAL = 6;
  int FFT_SELECTOR = 7;
  int FFT_ARRAYLEN = 8;
  int FFT_PRIMARYEXPR = 9;
  int FFT_UNARYEXPR = 10;
  int FFT_POWEXPR = 11;
  int FFT_MULEXPR = 12;
  int FFT_ADDEXPR = 13;
  int FFT_RELEXPR = 14;
  int FFT_EQUALEXPR = 15;
  int FFT_CONDANDEXPR = 16;
  int FFT_CREATIONEXPR = 17;
  int FFT_EXPR = 18;
  int FFT_VOID = 19;
  int FFT_PROCFUNCCALL = 20;
  int FFT_ASSIGNMENT = 21;
  int FFT_CONDITION = 22;
  int FFT_IFSTATEMENT = 23;
  int FFT_WHILESTATEMENT = 24;
  int FFT_FORSTATEMENT = 25;
  int FFT_RETURNSTATEMENT = 26;
  int FFT_PRIMITIVETYPE = 27;
  int FFT_COMPLEXALGEBRAICTYPE = 28;
  int FFT_TYPE = 29;
  int FFT_DECL = 30;
  int FFT_IDTERM = 31;
  int FFT_TERM = 32;
  int FFT_POLYNOMIAL = 33;
  int FFT_GF = 34;
  int FFT_RECORD = 35;
  int FFT_PSRANDOMGENERATOR = 36;
  int FFT_FORMALPARAM = 37;
  int FFT_PROGRAM = 38;
  int FFT_PROC = 39;
  int FFT_FUNC = 40;
  int FFT_ARGUMENTLIST = 41;
  int FFT_BLOCK = 42;
  int FFT_FUNCBLOCK = 43;
  int FFT_ELSEBLOCK = 44;
  int FFT_STATEMENTLIST = 45;
  int FFT_STATEMENT = 46;
  int FFT_DECLTYPE = 47;
  int FFT_FORMALPARAMLIST = 48;
  int FFT_CONSTDECL = 49;
  int FFT_EXPRCOMPLEXATYPE = 50;
  int FFT_CONTAINERTYPE = 51;
  int FFT_ALGEBRAICTYPE = 52;
  int FFT_PARAMTYPE = 53;
  int FFT_CONSTTYPE = 54;
  int FFT_ARRAYTYPE = 55;
  int FFT_RANDOMGENERATORTYPE = 56;
  int FFT_EXPRRANDOMGTYPE = 57;
  int FFT_BLOCKSTATEMENT = 58;
  int FFT_BREAK = 59;
  int FFT_EC = 60;
  int FFT_ECPOINT = 61;
  int FFT_SAMEAS = 62;
  int FFT_ECBASEFIELD = 63;
  int FFT_ECASSIGNMENT = 64;
  int FFT_CONDOREXPR = 65;


  String[] FFT_NodeName = {
    "RelOp",
    "EqualOp",
    "AddOp",
    "MulOp",
    "Random",
    "RandomGenerator",
    "Literal",
    "Selector",
    "ArrayLen",
    "PrimaryExpr",
    "UnaryExpr",
    "PowExpr",
    "MulExpr",
    "AddExpr",
    "RelExpr",
    "EqualExpr",
    "CondAndExpr",
    "CreationExpr",
    "Expr",
    "void",
    "ProcFuncCall",
    "Assignment",
    "Condition",
    "IfStatement",
    "WhileStatement",
    "ForStatement",
    "ReturnStatement",
    "PrimitiveType",
    "ComplexAlgebraicType",
    "Type",
    "Decl",
    "IdTerm",
    "Term",
    "Polynomial",
    "GF",
    "Record",
    "PsRandomGenerator",
    "FormalParam",
    "Program",
    "Proc",
    "Func",
    "ArgumentList",
    "Block",
    "FuncBlock",
    "ElseBlock",
    "StatementList",
    "Statement",
    "DeclType",
    "FormalParamList",
    "ConstDecl",
    "ExprComplexAType",
    "ContainerType",
    "AlgebraicType",
    "ParamType",
    "ConstType",
    "ArrayType",
    "RandomGeneratorType",
    "ExprRandomGType",
    "BlockStatement",
    "Break",
    "EllipticCurve",
    "EllipticCurvePoint"
  };
}

