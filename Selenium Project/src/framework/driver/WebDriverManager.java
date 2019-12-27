package framework.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/*
 * This is an abstract class defining the implementations for the
 * FirefoxDriverManager, ChromeDriverManager, and any additional managers.
 */
public abstract class WebDriverManager {

	protected WebDriver driver = null;
	
	protected abstract void createWebDriver( );
	
	public void quitWebDriver( ) {
		
		if( null != driver ) {
			
			driver.quit( );
			driver = null;
		}
	}
	
	public WebDriver getWebDriver( ) {
		
		if( null == driver )
			createWebDriver( );
		
		return driver;
	}
	
	public JavascriptExecutor getJavascriptExecutor( ) {
		
		WebDriver wd = getWebDriver( );
		if( null != wd )
			return ( JavascriptExecutor )wd;
		
		return null;
	}
}
