package ffapl.java.matcher;

import java.awt.Font;

public class FuncProcMatcher extends RegexMatcher{

	public FuncProcMatcher(String regex){
		super(regex);
		setFontWeight(Font.ITALIC);
	}
	
	public boolean find() {
		boolean result = super.find();
		if(result){
			setEnd(end()-1);
		}
		return result;
	}
}
