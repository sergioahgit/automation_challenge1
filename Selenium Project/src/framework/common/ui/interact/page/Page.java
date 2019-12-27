package framework.common.ui.interact.page;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import framework.commons.ui.condition.page.ScrollToBottomPage;
import framework.commons.ui.util.Wait;

/*
 * This abstract class must be extended by every page object."
 * 
 * @author : Sergio A. Hernandez
 * @version : 1.0
 * @since 2019-12-25
 * 
 */
public abstract class Page {

	protected WebDriver driver = null;
	protected JavascriptExecutor javaScriptExecutor = null;
	
	/* --- This is the welcome survey locator. --- */
	By welcomePopupSurveyWindowLocator = By.xpath( "//div[@id='IPEinvL850']/map[@name='IPEMap']/area[@alt='close']" );
	By overlayDialog = By.xpath( "//div[@id='IPEbgCover850']/map[@name='IPEMap']/area[@alt='close']" );
	
	/* --- Set to true when the survey dialog pops and it is closed. --- */
	boolean welcomePopupSurveyWindowClosed = false;
	
	/* --- Stores the last error message. --- */
	private String lastErrorMsg = "";
	
	public Page( WebDriver driver ) {
		
		this.driver = driver;
		this.javaScriptExecutor = ( JavascriptExecutor ) driver;
	}
	
	/**
	 * Maximizes the browser.
	 */
	public void maximizeBrowser( ) {
	
		/* --- Let's Maximize the browser. --- */
		driver.manage( ).window( ).maximize( );
	}
	
	/**
	 * Scrolls to bottom of page, scrolling between intervals of given seconds
	 * @param interval wait number of seconds between scrolling.
	 * @param timeout the number of seconds
	 */
	public void scrollToBottom( long interval, int timeout ) {
		
		/* --- Let's scroll to the end of the page, just to make sure the element that we are trying to click is viewable. --- */
		if( !Wait.until( driver, new ScrollToBottomPage( interval, false ), timeout ) )
			throw new RuntimeException( "Homepage : https://www.nc.com was not fully scrolled on the given time.");
	}
	
	/**
	 * Returns the WebDriver.
	 * 
	 * Returns the WebDriver
	 * @return the WebDriver
	 */
	public WebDriver getWebDriver( ) {
		
		return driver;
	}
	
	/**
	 * Returns the JavascriptExcutor.
	 * 
	 * @return the JavascriptExecutor
	 */
	public JavascriptExecutor getJavascriptExecutor( ) {
		
		return javaScriptExecutor;
	}
	
	/**
	 * At times a survey dialog may appear on any page.
	 * This methods waits a period amount of time for the dialog to appear before closing it.
	 * @param timeout the timeout to wait for.
	 */
	protected void closeWelcomeSurveyDialog( int timeout ) {
		
		/* --- If we already close the 'Welcome' survey dialog, then it shouldn't pop again.  Let's ignore it. --- */
		if( this.welcomePopupSurveyWindowClosed )
			return;
		
		/* --- Let's wait for 10 seconds, until the temporary overlay dialog disappears. - */
		Wait.invisibilityOfElementLocated (driver, this.overlayDialog, 2 );
		
		/* So, at this point the 'Welcome' survey dialog may show up if it hasn't.  So, we'll try to close it if does. --- */
		WebElement welcomePopupSurveyWindow = Wait.elementToBeClickable( driver, welcomePopupSurveyWindowLocator, timeout );
		
		try {
			
			/* --- If the 'Welcome' was detected, let's close it. --- */
			if( null != welcomePopupSurveyWindow ) {
			
				welcomePopupSurveyWindow.click( );
				this.welcomePopupSurveyWindowClosed = true;
			}
			
		} catch( ElementNotInteractableException enie ) {
			
			JavascriptExecutor je = ( JavascriptExecutor )driver;
			je.executeScript( "arguments[0].click();", welcomePopupSurveyWindow );
		}
		
		this.welcomePopupSurveyWindowClosed = true;
	}
	
	/**
	 * Sets the last error message.
	 * @param msg message to be set.
	 */
	protected void setLastErrorMsg( String msg ) {
		
		this.lastErrorMsg = msg;
	}
	
	/**
	 * Returns the last error encountered
	 * @return
	 */
	public String getLastErrorMsg( ) {
		
		return this.lastErrorMsg;
	}
}
