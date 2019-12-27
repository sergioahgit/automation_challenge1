package framework.factory;

import framework.driver.WebDriverManager;
import framework.driver.FirefoxDriverManager;

public class WebDriverManagerFactory {

	public static enum DriverType {
	
		FIREFOX, CHROME, SAFARI, IE, OPERA;
	}
	
	public static WebDriverManager createDriverManager( DriverType type ) {
		
		WebDriverManager webDriverManager;
		switch( type ) {
		
			case FIREFOX: {
			
				webDriverManager = new FirefoxDriverManager( );
				break;
			}
			
			default: {
				
				webDriverManager = new FirefoxDriverManager( );
				break;
			}
		}
		
		return webDriverManager;
	}
}
