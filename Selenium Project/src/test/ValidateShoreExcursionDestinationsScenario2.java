package test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import framework.common.ui.interact.page.ExcursionPage;
import framework.common.ui.interact.page.ExcursionResultsPage;
import framework.common.ui.interact.page.MainPage;
import framework.commons.util.Time;
import framework.driver.WebDriverManager;
import framework.factory.WebDriverManagerFactory;
import framework.factory.WebDriverManagerFactory.DriverType;

/*
 * The ValidateShoreExcursionDestination2 JUnit5 Test case for Scenario 2"
 * 
 * @author : Sergio A. Hernandez
 * @version : 1.0
 * @since 2019-12-25
 * 
 * Scenario 2: Guest explores shore excursions destinations
 * 
 * GIVEN a Guest
 * AND I am on Homepage
 * AND I navigated to "Shore Excursion" page
 * WHEN I search for destination "Alaska Cruises"
 * THEN Shore Excursions page is present
 * AND Results are filtered by "Alaska Cruises"
 * AND Filter By Ports are only belong to "Alaska, British Columbia"
 */
class ValidateShoreExcursionDestinationsScenario2 {

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
		
		/* --- Let's click the Excursion Link. --- */
		if( null == mainPage.clickExcursion( ) )
			throw new RuntimeException( mainPage.getLastErrorMsg( ) );
		
		/* At this point we should be on the 'Shore Excursion' page.
		 * Let's wait for 2 seconds for some loading.
		 */
		Time.waitFor( 2000 );
		
		/* --- Let's Instantiate the Excursion Page to interact with it. --- */
		ExcursionPage excursionPage = new ExcursionPage( driver );
		
		/* --- Let's click the dropdown and select 'ALASKA CRUISES'. --- */
		if( null == excursionPage.selectDestination( "ALASKA CRUISES" ) )
			throw new RuntimeException( excursionPage.getLastErrorMsg( ) );
		
		/* --- We then go find the Button "FIND EXCURSIONS" to click it. ---- */
		if( null == excursionPage.clickFindExcursions( ) )
			throw new RuntimeException( excursionPage.getLastErrorMsg( ) );
		
		/* --- Let's create an Instance to the results, page. --- */
		ExcursionResultsPage excursionPageResult = new ExcursionResultsPage( driver );
		
		/* --- Let's click the 'Widget - Ports' and expand it. --- */
		if( null == excursionPageResult.clickFilterByPortsWidget( ) )
			throw new RuntimeException( excursionPage.getLastErrorMsg( ) );
		
		/*--- At this point we should get results. --- */
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