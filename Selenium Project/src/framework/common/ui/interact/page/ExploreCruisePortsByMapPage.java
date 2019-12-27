package framework.common.ui.interact.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import framework.commons.util.Time;

public class ExploreCruisePortsByMapPage extends Page {

	private By searchBarLocator = By.xpath( "//input[@id='searchbar']" );
	private By searchBarSuggestedResultsLocator = By.xpath( "//li[@class='ng-scope']" );
	
	/**
	 * Constructs a ExploreCruisePortsByMap Page object to search by Map.
	 * 
	 * @param driver
	 */
	public ExploreCruisePortsByMapPage( WebDriver driver ) {
	
		super( driver );
	}
	
	/**
	 * In the search bar, searches for the given port entry and returns the WebElement if found.
	 * @param portEntry port entry to search for
	 * @param refreshMapTimeout if suggested port entry is selected, wait for a given amount for time for the map to refresh.
	 * @return the selected port entry WebElement if found.  Otherwise, it returns null.
	 */
	public WebElement searchDestination( String portEntry, long refreshMapTimeout ) {
		
		/**
		 * At times a survey dialog may appear on any page.
		 * This methods waits a period amount of time for the dialog to appear before closing it.
		 * Note: If the Survey dialog was already closed, it will not check again.  This is only a 1 time check.
		 */
		closeWelcomeSurveyDialog( 3 );
		
		/* --- Let's find the search bar. --- */
		WebElement portSearchBar = driver.findElement( searchBarLocator );
		if( null == portSearchBar ) {
			
			/* --- Let's set the last error message. --- */
			setLastErrorMsg( new Object( ) { }.getClass( ).getEnclosingMethod( ).getName( ) + "Unable to find the port search bar element." );
			return null;
		}
	
		/* --- Let's send some text into the search bar. --- */
		portSearchBar.sendKeys( portEntry );
		
		/* Let's get the list that contains the suggestions.
		 * We may get many suggestions and it is possible that this code could be resuable
		 * to test the suggestions coming from the server side.  To see if they are valid.
		 * But for this scenario we are interested in "HONOLULU".
		*/
		List< WebElement > suggestedPortOptions = driver.findElements( searchBarSuggestedResultsLocator );
		
		WebElement suggestedPortEntry = null;
		for( WebElement option : suggestedPortOptions ) {
			
			/* --- Let's display the available ports. --- */
			if( option.getText().toLowerCase( ).contains( portEntry.toLowerCase( ) ) ) {
				
				/* --- Let's select the suggested item from the list. --- */
				option.click( );
				
				/* --- Let's wait for 5 seconds for the map to refresh. --- */
				Time.waitFor( 5000 );
				
				suggestedPortEntry = option;
				break;
			}
		}
	
		/* --- If we find the port return the WebElement. --- */
		if( null != suggestedPortEntry )
			return suggestedPortEntry;
		
		/* --- Let's set the last error message. --- */
		setLastErrorMsg( new Object( ) { }.getClass( ).getEnclosingMethod( ).getName( ) + "Unable to find the suggested " + portEntry + " entry element through the search bar." );
		
		return null;
	}
}
