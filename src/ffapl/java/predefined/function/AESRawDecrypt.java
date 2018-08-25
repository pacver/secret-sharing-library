package ffapl.java.predefined.function;

import java.math.BigInteger;

import ffapl.exception.FFaplException;
import ffapl.java.classes.BInteger;
import ffapl.java.exception.FFaplAlgebraicException;
import ffapl.java.interfaces.IJavaType;
import ffapl.java.interfaces.IPredefinedProcFunc;
import ffapl.java.math.Algorithm;
import ffapl.lib.FFaplPreProcFuncSymbol;
import ffapl.lib.FFaplSymbol;
import ffapl.lib.interfaces.ISymbol;
import ffapl.lib.interfaces.ISymbolTable;
import ffapl.lib.interfaces.IVm;
import ffapl.types.FFaplInteger;

public class AESRawDecrypt implements IPredefinedProcFunc {

	@Override
	public void execute(IVm interpreter) throws FFaplAlgebraicException {
		// TODO Auto-generated method stub
		IJavaType k = (IJavaType) interpreter.popStack();
		IJavaType c = (IJavaType) interpreter.popStack();
		BigInteger input = BigInteger.ZERO;
		BInteger result;

		switch(c.typeID()){
		//				case IJavaType.RESIDUECLASS:
		//					valueRC = Algorithm.sqrtMod(((ResidueClass)a).value(), ((ResidueClass)a).modulus(), true);
		//					resultRC = new ResidueClass(valueRC,((ResidueClass)a).modulus());
		//					interpreter.pushStack(resultRC);
		//					break;
		case IJavaType.INTEGER:
			input = Algorithm.AESDecrypt((BInteger)c, (BInteger)k, false, null);
			result = new BInteger(input, null);
			interpreter.pushStack(result);
			break;

		default:
			break;
		}

		interpreter.funcReturn();
	}

	/**
	 * Registers predefined Function in Symbol table
	 * @param symbolTable
	 * @param thread
	 * @throws FFaplException
	 */
	public static void registerProcFunc(ISymbolTable symbolTable)
			throws FFaplException {
		FFaplPreProcFuncSymbol s;
		s = new FFaplPreProcFuncSymbol("AESRawDecrypt", 
                null,
                new FFaplInteger(),
                ISymbol.FUNCTION);
		s.setProcFunc(new AESRawDecrypt());
		symbolTable.addSymbol(s);
	    symbolTable.openScope(false);
	    //for Parameter
	    symbolTable.addSymbol(
	    		new FFaplSymbol("_t1", 
                null,
                new FFaplInteger(),
                ISymbol.PARAMETER));
	    symbolTable.addSymbol(
	    		new FFaplSymbol("_t2", 
	            null,
	            new FFaplInteger(),
	            ISymbol.PARAMETER));
	    symbolTable.closeScope();	   
	}
}
