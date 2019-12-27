package framework.common.ui.interact.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import framework.commons.ui.util.Wait;

/*
 * This class handles the interaction with the https://www.ncl.com main website page."
 * 
 * @author : Sergio A. Hernandez
 * @version : 1.0
 * @since 2019-12-25
 * 
 */
public class MainPage extends Page {

	private By excursionLinkLocator = By.xpath( "//a[@href='/shore-excursions']" );
	private By portsLinkLocator = By.xpath( "//a[@class='simpleText' and @href='/port-of-call']" );

	/**
	 * Constructs a Main Web Page object that provides interactions with the https://www.ncl.com main website page.
	 * 
	 * @param driver
	 */
	public MainPage( WebDriver driver ) {
	
		super( driver );
		
		driver.get( "https://www.ncl.com" );
	}
	
	/**
	 * This methods to find the excursion link and clicks on it.
	 * @return returns clicked WebElement.  Otherwise, it returns null if not found.
	 */
	public WebElement clickExcursion( ) {
		
		/**
		 * At times a survey dialog may appear on any page.
		 * This methods waits a period amount of time for the dialog to appear before closing it.
		 * Note: If the Survey dialog was already closed, it will not check again.  This is only a 1 time check.
		 */
		closeWelcomeSurveyDialog( 3 );
			
		/* --- Let's find the shore excursion link. --- */
		WebElement excursionLink = driver.findElement( this.excursionLinkLocator );
		if( null != excursionLink ) {
			
			excursionLink.click( );
			return excursionLink;
		}
		
		/* --- Let's set the last error message. --- */
		setLastErrorMsg( new Object( ) { }.getClass( ).getEnclosingMethod( ).getName( ) + "Unable to find the 'Excursion Link' element." );
		
		return null;
	}
	
	/**
	 * This methods to find the ports link and clicks on it.
	 * @return returns clicked WebElement.  Otherwise, it returns null if not found.
	 */
	public WebElement clickPorts( ) {
		
		/**
		 * At times a survey dialog may appear on any page.
		 * This methods waits a period amount of time for the dialog to appear before closing it.
		 * Note: If the Survey dialog was already closed, it will not check again.  This is only a 1 time check.
		 */
		closeWelcomeSurveyDialog( 3 );
			
		/* --- Let's find the ports link. --- */
		WebElement portsLink = Wait.elementToBeClickable( driver, portsLinkLocator, 3 );
		//WebElement portsLink = driver.findElement( this.portsLinkLocator );
		if( null != portsLink ) {
			
			portsLink.click( );
			return portsLink;
		}
				
		/* --- Let's set the last error message. --- */
		setLastErrorMsg( new Object( ) { }.getClass( ).getEnclosingMethod( ).getName( ) + "Unable to find the 'Ports Link' element." );
		
		return null;
	}
}
