package framework.driver;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends WebDriverManager {

	@Override
	protected void createWebDriver() {
		
		System.setProperty( "webdriver.gecko.driver", "C:\\geckodriver-v0.26.0-win64\\geckodriver.exe" );
		
		/* --- Let's initialize the browser driver. --- */
		driver = new FirefoxDriver( );
		System.out.println( "Creating WebDriver" );
	}
}
