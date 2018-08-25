/* Generated By:JavaCC: Do not edit this line. FFaplInterpreterConstants.java */
package ffapl;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface FFaplInterpreterConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int EQUAL_SIGN = 5;
  /** RegularExpression Id. */
  int LOWER = 6;
  /** RegularExpression Id. */
  int GREATER = 7;
  /** RegularExpression Id. */
  int ECLEFT = 8;
  /** RegularExpression Id. */
  int ECRIGHT = 9;
  /** RegularExpression Id. */
  int ECPAI = 10;
  /** RegularExpression Id. */
  int ECRANDOM = 11;
  /** RegularExpression Id. */
  int ECRANDOMSUB = 12;
  /** RegularExpression Id. */
  int SAMEAS = 13;
  /** RegularExpression Id. */
  int ECBASEGF = 14;
  /** RegularExpression Id. */
  int ECBASEZ = 15;
  /** RegularExpression Id. */
  int LOWER_EQUAL = 16;
  /** RegularExpression Id. */
  int GREATER_EQUAL = 17;
  /** RegularExpression Id. */
  int EQUAL = 18;
  /** RegularExpression Id. */
  int NOT_EQUAL = 19;
  /** RegularExpression Id. */
  int NOT = 20;
  /** RegularExpression Id. */
  int PLUS = 21;
  /** RegularExpression Id. */
  int MINUS = 22;
  /** RegularExpression Id. */
  int MULTIPLY = 23;
  /** RegularExpression Id. */
  int DIVIDE = 24;
  /** RegularExpression Id. */
  int MODULO = 25;
  /** RegularExpression Id. */
  int HASH_KEY = 26;
  /** RegularExpression Id. */
  int SQUARED_BRACE_LEFT = 27;
  /** RegularExpression Id. */
  int SQUARED_BRACE_RIGHT = 28;
  /** RegularExpression Id. */
  int CURLY_BRACE_LEFT = 29;
  /** RegularExpression Id. */
  int CURLY_BRACE_RIGHT = 30;
  /** RegularExpression Id. */
  int BRACE_LEFT = 31;
  /** RegularExpression Id. */
  int BRACE_RIGHT = 32;
  /** RegularExpression Id. */
  int DOT = 33;
  /** RegularExpression Id. */
  int ASSIGN = 34;
  /** RegularExpression Id. */
  int SEMICOLON = 35;
  /** RegularExpression Id. */
  int COMMA = 36;
  /** RegularExpression Id. */
  int COLON = 37;
  /** RegularExpression Id. */
  int POWER = 38;
  /** RegularExpression Id. */
  int AND = 39;
  /** RegularExpression Id. */
  int OR = 40;
  /** RegularExpression Id. */
  int XOR = 41;
  /** RegularExpression Id. */
  int RESIDUE = 42;
  /** RegularExpression Id. */
  int INTEGER = 43;
  /** RegularExpression Id. */
  int BOOLEAN = 44;
  /** RegularExpression Id. */
  int PRIME = 45;
  /** RegularExpression Id. */
  int POLYNOMIAL = 46;
  /** RegularExpression Id. */
  int TSTRING = 47;
  /** RegularExpression Id. */
  int WHILE = 48;
  /** RegularExpression Id. */
  int NEW = 49;
  /** RegularExpression Id. */
  int IF = 50;
  /** RegularExpression Id. */
  int FOR = 51;
  /** RegularExpression Id. */
  int TRUE = 52;
  /** RegularExpression Id. */
  int FALSE = 53;
  /** RegularExpression Id. */
  int ELSE = 54;
  /** RegularExpression Id. */
  int RANDOM = 55;
  /** RegularExpression Id. */
  int RANDOMGENERATOR = 56;
  /** RegularExpression Id. */
  int TO = 57;
  /** RegularExpression Id. */
  int STEP = 58;
  /** RegularExpression Id. */
  int EC = 59;
  /** RegularExpression Id. */
  int GF = 60;
  /** RegularExpression Id. */
  int RECORD = 61;
  /** RegularExpression Id. */
  int RECORD_END = 62;
  /** RegularExpression Id. */
  int PSEUDORANDOMGENERATOR = 63;
  /** RegularExpression Id. */
  int PROGRAM = 64;
  /** RegularExpression Id. */
  int PROCEDURE = 65;
  /** RegularExpression Id. */
  int FUNCTION = 66;
  /** RegularExpression Id. */
  int RETURN = 67;
  /** RegularExpression Id. */
  int CONST = 68;
  /** RegularExpression Id. */
  int BREAK = 69;
  /** RegularExpression Id. */
  int HEX = 70;
  /** RegularExpression Id. */
  int LETTER = 71;
  /** RegularExpression Id. */
  int HEXLETTER = 72;
  /** RegularExpression Id. */
  int DIGIT_EX_ZERO = 73;
  /** RegularExpression Id. */
  int ZERO = 74;
  /** RegularExpression Id. */
  int DIGIT = 75;
  /** RegularExpression Id. */
  int IDENT = 76;
  /** RegularExpression Id. */
  int NUMBER = 77;
  /** RegularExpression Id. */
  int HEXNUMBER = 78;
  /** RegularExpression Id. */
  int STRING = 79;
  /** RegularExpression Id. */
  int SINGLE_LINE_COMMENT = 83;
  /** RegularExpression Id. */
  int FORMAL_COMMENT = 84;
  /** RegularExpression Id. */
  int MULTI_LINE_COMMENT = 85;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int IN_SINGLE_LINE_COMMENT = 1;
  /** Lexical state. */
  int IN_FORMAL_COMMENT = 2;
  /** Lexical state. */
  int IN_MULTI_LINE_COMMENT = 3;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\r\"",
    "\"\\t\"",
    "\"\\n\"",
    "\"=\"",
    "\"<\"",
    "\">\"",
    "\"<<\"",
    "\">>\"",
    "\"PAI\"",
    "\"RandomPoint\"",
    "\"RandomPointSubfield\"",
    "\"SameAs\"",
    "\"BaseGF\"",
    "\"BaseZ\"",
    "\"<=\"",
    "\">=\"",
    "\"==\"",
    "\"!=\"",
    "\"!\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"MOD\"",
    "\"#\"",
    "\"[\"",
    "\"]\"",
    "\"{\"",
    "\"}\"",
    "\"(\"",
    "\")\"",
    "\".\"",
    "\":=\"",
    "\";\"",
    "\",\"",
    "\":\"",
    "\"^\"",
    "\"AND\"",
    "\"OR\"",
    "\"XOR\"",
    "\"Z\"",
    "\"Integer\"",
    "\"Boolean\"",
    "\"Prime\"",
    "\"Polynomial\"",
    "\"String\"",
    "\"while\"",
    "\"new\"",
    "\"if\"",
    "\"for\"",
    "\"true\"",
    "\"false\"",
    "\"else\"",
    "\"Random\"",
    "\"RandomGenerator\"",
    "\"to\"",
    "\"step\"",
    "\"EC\"",
    "\"GF\"",
    "\"Record\"",
    "\"EndRecord\"",
    "\"PseudoRandomGenerator\"",
    "\"program\"",
    "\"procedure\"",
    "\"function\"",
    "\"return\"",
    "\"const\"",
    "\"break\"",
    "\"0x\"",
    "<LETTER>",
    "<HEXLETTER>",
    "<DIGIT_EX_ZERO>",
    "\"0\"",
    "<DIGIT>",
    "<IDENT>",
    "<NUMBER>",
    "<HEXNUMBER>",
    "<STRING>",
    "\"//\"",
    "<token of kind 81>",
    "\"/*\"",
    "<SINGLE_LINE_COMMENT>",
    "\"*/\"",
    "\"*/\"",
    "<token of kind 86>",
  };

}
