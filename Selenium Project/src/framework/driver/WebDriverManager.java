package framework.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/*
 * This is an abstract class defining the implementations for the
 * FirefoxDriverManager, ChromeDriverManager, and any additional managers.
 * 
 * @author : Sergio A. Hernandez
 * @version : 1.0
 * @since 2019-12-25
 * 
 */
public abstract class WebDriverManager {

	protected WebDriver driver = null;
	
	protected abstract void createWebDriver( );
	
	/**
	 * Quits the WebDriver.
	 */
	public void quitWebDriver( ) {
		
		if( null != driver ) {
			
			driver.quit( );
			driver = null;
		}
	}
	
	/**
	 * Returns the WebDriver.
	 * @return the WebDriver.  Otherwise, it returns null.
	 */
	public WebDriver getWebDriver( ) {
		
		if( null == driver )
			createWebDriver( );
		
		return driver;
	}
	
	/**
	 * Returns the JavascriptExecutor.
	 * @return the JavascriptExecutor.  Otherwise, it returns null.
	 */
	public JavascriptExecutor getJavascriptExecutor( ) {
		
		WebDriver wd = getWebDriver( );
		if( null != wd )
			return ( JavascriptExecutor )wd;
		
		return null;
	}
}
