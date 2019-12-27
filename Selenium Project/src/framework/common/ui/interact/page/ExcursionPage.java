package framework.common.ui.interact.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import framework.commons.ui.util.Wait;

/*
 * This class handles the interaction with the https://www.ncl.com/shore-excursions webpage."
 * 
 * @author : Sergio A. Hernandez
 * @version : 1.0
 * @since 2019-12-25
 * 
 */
public class ExcursionPage extends Page {
	
	private By overlayDialogRefresher1Locator = By.xpath( "//div[@id='modal-dialog-loading']" );
	private By overlayDialogRefresher2Locator = By.xpath( "//div[@class='modal-backdrop fade']" );
	
	private By dropdownDestinationLocator = By.xpath( "//div[@id='search_destinations_chosen']/a/div/b" );
	private By dropdownDestinationSuggestedResultsLocator = By.cssSelector( "li[class=active-result]" );
	
	private By excursionbuttonLocator  = By.xpath( "//button[text()='FIND EXCURSIONS']" );

	/**
	 * Constructs a Excursion Web Page object that provides interactions with the https://www.ncl.com/shore-excursions page.
	 * 
	 * @param driver
	 */
	public ExcursionPage( WebDriver driver ) {
	
		super( driver );
	}
	
	/**
	 * As we are interacting with the dropdowns, there are two dialog element refreshers that appears.
	 * This method waits until backdrop fade.  Call this method after interacting with the dropdowns.
	 *  
	 */
	private void waitForDialogLoadingBackdropToFade( ) {
		
		/* --- Let's wait for 10 seconds, until the temporary overlay dialog disappears. - */
		Wait.invisibilityOfElementLocated (driver, this.overlayDialogRefresher1Locator, 15 );
		Wait.invisibilityOfElementLocated (driver, this.overlayDialogRefresher2Locator, 15 );
	}
	
	/**
	 * This methods find the excursion link and clicks on it.
	 * @return returns the WebElement of the excursion link if found.  Otherwise, it returns null.
	 */
	public WebElement clickFindExcursions( ) {
		
		/**
		 * At times a survey dialog may appear on any page.
		 * This methods waits a period amount of time for the dialog to appear before closing it.
		 * Note: If the Survey dialog was already closed, it will not check again.  This is only a 1 time check.
		 */
		closeWelcomeSurveyDialog( 10 );
		
		/* --- We then go find the Button "FIND EXCURSIONS" to click it. ---- */
		WebElement button = driver.findElement( this.excursionbuttonLocator );
		if( null != button ) {
			
			/* --- Let's click the button. --- */
			button.click( );
			
			/* --- Let's return the WebElement. --- */
			return button;
		}
		
		/* --- Let's set the last error message. --- */
		setLastErrorMsg( new Object( ) { }.getClass( ).getEnclosingMethod( ).getName( ) + "Unable to find the 'Find Excursion Button' element." );
		
		return null;
	}
	
	/**
	 * In the destination dropdown it selects a given destination and returns the WebElement if found.
	 * @param destination destination to select for.
	 * @param click if destination found, select it.  Otherwise, do not selected it. 
	 * @return the selected destination WebElement if found.  Otherwise, it returns null.
	 */
	public WebElement selectDestination( String destination ) {
		
		/**
		 * At times a survey dialog may appear on any page.
		 * This methods waits a period amount of time for the dialog to appear before closing it.
		 * Note: If the Survey dialog was already closed, it will not check again.  This is only a 1 time check.
		 */
		closeWelcomeSurveyDialog( 3 );
		
		/* --- Let's go to the dropdown - Destination. --- */
		WebElement destinationDropdown = this.driver.findElement( dropdownDestinationLocator );
		if( null == destinationDropdown ) {
			
			/* --- Let's set the last error message. --- */
			setLastErrorMsg( new Object( ) { }.getClass( ).getEnclosingMethod( ).getName( ) + "Unable to find the destination dropdown box element." );
			return null;
		}
	
		/* --- Let's click the drop down. --- */
		destinationDropdown.click( );
		
		/* --- Let's get the whole list. --- */
		List< WebElement > destinationOptions = driver.findElements( dropdownDestinationSuggestedResultsLocator );
		
		WebElement suggestedDestinationEntry = null;
		for( WebElement option : destinationOptions ) {
			
			/* --- Let's display the available cruises. --- */
			if( option.getText().toLowerCase( ).contentEquals( destination.toLowerCase( ) ) ) {
				
				/* --- Let's select the item and then let's wait for the dialog loading to fade. --- */
				option.click( );
				waitForDialogLoadingBackdropToFade( );
				
				suggestedDestinationEntry = option;
				break;
			}
		}

		/* --- If we find the destination, let's return the WebElement. --- */
		if( null != suggestedDestinationEntry )
			return suggestedDestinationEntry;
		
		/* --- Let's set the last error message. --- */
		setLastErrorMsg( new Object( ) { }.getClass( ).getEnclosingMethod( ).getName( ) + "Unable to find the suggested " + destination + " entry element." );
		
		return null;
	}
}