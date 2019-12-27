package framework.common.ui.interact.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * This class handles the interaction with the https://www.ncl.com/shore-excursions/search webpage."
 * 
 * @author : Sergio A. Hernandez
 * @version : 1.0
 * @since 2019-12-25
 * 
 */
public class ExcursionResultsPage extends Page {

	private By filterByPortsWidgeLocator = By.xpath( "//div[@class='filter-options']/ul[@class='filter-widget widget-right']/li/a[@class='widget-link' and @title='Port']" );
	
	/**
	 * Constructs a Excursion Page Result object that provides interactions with the result page https://www.ncl.com/shore-excursions/search.
	 * 
	 * @param driver
	 */
	public ExcursionResultsPage( WebDriver driver ) {
	
		super( driver );
	}
	
	/**
	 * This methods find the excursion link and clicks on it.
	 * @return returns the WebElement of the excursion link if found.  Otherwise, it returns null.
	 */
	public WebElement clickFilterByPortsWidget( ) {
	
		/**
		 * At times a survey dialog may appear on any page.
		 * This methods waits a period amount of time for the dialog to appear before closing it.
		 * Note: If the Survey dialog was already closed, it will not check again.  This is only a 1 time check.
		 */
		closeWelcomeSurveyDialog( 3 );
		
		/* --- Let's go to the Filter 'Widget - Ports' and expand it. --- */
		WebElement filterWidgetPorts = driver.findElement( filterByPortsWidgeLocator );
		if( null != filterWidgetPorts ) {
		
			/* --- Click the filter widget ports. --- */
			filterWidgetPorts.click( );
			
			/* --- Let's return the WebElement. --- */
			return filterWidgetPorts;
		}
		
		
		/* --- Let's set the last error message. --- */
		setLastErrorMsg( new Object( ) { }.getClass( ).getEnclosingMethod( ).getName( ) + "Unable to find the 'Filter by Ports Widget' element." );
		
		return null;
	}
	
	/**
	 * Sets the left and right slider to a given value.
	 * @param minValue the minimum range value.
	 * @param maxValue the maximum range value
	 */
	public void setWidgetPriceSliderValues( int minValue, int maxValue ) {
		
		String javascriptToExecute = "jQuery(\"#price-slider-values\").slider('values',0," + Integer.toString( minValue ) + ");jQuery(\"#price-slider-values\").slider('values',1," + Integer.toString( maxValue ) + ");";
		
		javaScriptExecutor.executeScript( javascriptToExecute );
	}
}