package test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import framework.common.ui.interact.page.ExploreCruisePortsByMapPage;
import framework.common.ui.interact.page.MainPage;
import framework.commons.util.Time;
import framework.driver.WebDriverManager;
import framework.factory.WebDriverManagerFactory;
import framework.factory.WebDriverManagerFactory.DriverType;
/*
 * The ValidateShoreExcursionDestination2 JUnit5 Test case for Scenario 1"
 * 
 * @author : Sergio A. Hernandez
 * @version : 1.0
 * @since 2019-12-25
 * 
 * Scenario 1: Guest explores Ports of Departure
 * 
 * GIVEN a Guest
 * AND I am on Homepage
 * AND I navigated to "Ports" page
 * WHEN I search for "Honolulu" port
 * THEN Map zoomed to show selected port
 * AND Port is on the middle of the map
 * AND Port displayed as "Port Of Departure"
 */
class ValidateShoreExcursionDestinationsScenario1 {

	private static WebDriverManager driverManager;
	private static WebDriver driver;
	
	/* Denotes that the annotated method should be executed before all @Test, @RepeatedTest, @ParameterizedTest, or
	 * @TestFactory method in the current class; analogous to JUnit 4's @BeforeClass 
	 */
	@BeforeAll
	public static void openBrowser( ) {
	
		/* --- Let's get the DriverManager --- */
		driverManager = WebDriverManagerFactory.createDriverManager( DriverType.FIREFOX );
		
		/* --- Let's initialize the browser driver. --- */
		driver = driverManager.getWebDriver( );
	}
	
	@Test
	public void Test( ) {
		
		System.out.println( "Starting Test Code Function : " + new Object( ) { }.getClass( ).getEnclosingMethod( ).getName( ) );
		
		/* --- Let's Instantiate the Main Page (Front Website) to interact with it. --- */
		MainPage mainPage = new MainPage( driver );
		
		/* --- Let's wait for 5 seconds before maximizing the browser. ---*/
		Time.waitFor( 5000 );
		
		/* --- Let's Maximize the browser on the main page. --- */
		mainPage.maximizeBrowser( );
				
		/* Let's scroll to the end of the page.
		 * Waiting 3 seconds before each scroll, with a timeout for a full scroll to bottom of 1 minute.
		*/
		mainPage.scrollToBottom( 3, 60 );
		
		/* --- Let's click the Ports Link. --- */
		if( null == mainPage.clickPorts( ) )
			throw new RuntimeException( mainPage.getLastErrorMsg( ) );
		
		/* At this point we should be on the 'Explore Cruise Ports Map' page.
		 * Let's wait for 10 seconds to let page load.
		 */
		Time.waitFor( 10000 );
		
		/* --- Let's Instantiate the ExploreCruisePortsByMapPage to interact with it. --- */
		ExploreCruisePortsByMapPage exploreCruisePortsByMapPage = new ExploreCruisePortsByMapPage( driver );
		
		/* --- Let's search for text. --- */
		if( null == exploreCruisePortsByMapPage.searchDestination( "Honolulu", 10 ) )
			throw new RuntimeException( exploreCruisePortsByMapPage.getLastErrorMsg( ) );	
		
		/*--- At this point we should see the results. --- */
		System.out.println( "Ending Test Code Function : " + new Object( ) { }.getClass( ).getEnclosingMethod( ).getName( ));
	}
	
	/* Denotes that the annotated method should be executed after all @Test, @RepeatedTest, @ParameterizedTest,
	 * and @TestFactory methods in the current class; analogous to JUnit 4's @AfterClass.
	 */
	@AfterAll
	public static void closeBrowser( ) {
		
		/* --- Let's wait for 7 seconds before closing the browser, so we can see the results. ---*/
		Time.waitFor( 7000 );
		
		/* --- Once we have validated the results, we can then successfully close the browser. ---*/
		driver.quit( ); // Closing the driver once the tests are executed
	}
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
}