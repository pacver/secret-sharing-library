package ffapl.utils;

import java.util.Locale;
import java.util.ResourceBundle;


public class FFaplProperties {
	
	private static final String BASENAME = "ffapl.bundles.ffapl";

	
	private static FFaplProperties instance = new FFaplProperties();
	
	public static FFaplProperties getInstance(){
		return instance;
	}
	
	private FFaplProperties(){
		
	}
	
	public String getProperty(String key, Locale locale){
		return ResourceBundle.getBundle(BASENAME, locale).getString(key);
	}


	public String getProperty(String errorNr) {
		return getProperty(String.valueOf(errorNr));
	}
}
