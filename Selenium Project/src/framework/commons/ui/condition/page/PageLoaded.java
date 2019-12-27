package framework.commons.ui.condition.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class PageLoaded implements ExpectedCondition< Boolean > {

	private String expectedTitle = "";
	private String expectedUrl = "";
	
	public PageLoaded( String expectedTitle, String expectedUrl ) {
		
		this.expectedTitle = expectedTitle;
		this.expectedUrl   = expectedUrl;
	}
	
	@Override
	public Boolean apply( WebDriver driver ) {
		
		return driver.getTitle( ).contains( this.expectedTitle ) && driver.getCurrentUrl( ).contains( this.expectedUrl );
	}
}
