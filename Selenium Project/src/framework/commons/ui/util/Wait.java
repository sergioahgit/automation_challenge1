package framework.commons.ui.util;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {

	/**
	 * 
	 * @param <V>
	 * @param driver
	 * @param condition
	 * @param timeout
	 * @return
	 */
	public static <V> V until( WebDriver driver, Function<? super WebDriver, V> condition, int timeout ) {
		
		try {
		
			return new WebDriverWait( driver, timeout ).until( condition );
			
		} catch ( TimeoutException e ) { return null; }
	}
	
	/**
	 * 
	 * @param <V>
	 * @param driver
	 * @param condition
	 * @param message
	 * @param timeout
	 * @return
	 */
	public static <V> V until( WebDriver driver, Function<? super WebDriver, V> condition, String message, int timeout ) {
		
		try {
		
			return new WebDriverWait( driver, timeout ).withMessage( message ).until( condition );
			
		} catch( TimeoutException e ) { return null; }
	}
	
	
	
	/**
	 * 
	 * @param driver
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public static WebElement elementToBeClickable( WebDriver driver, By locator, int timeout ) {
		
		return until( driver, ExpectedConditions.elementToBeClickable( locator ), timeout );
	}
	
	/**
	 * 
	 * @param driver
	 * @param element
	 * @param timeout
	 * @return
	 */
	public static WebElement elementToBeClickable( WebDriver driver, WebElement element, int timeout ) {
		
		return until( driver, ExpectedConditions.elementToBeClickable( element ), timeout );
	}
	
	/**
	 * 
	 * @param driver
	 * @param locator
	 * @param message
	 * @param timeout
	 * @return
	 */
	public static WebElement elementToBeClickable( WebDriver driver, By locator, String message, int timeout ) {
		
		return until( driver, ExpectedConditions.elementToBeClickable( locator ), message, timeout );
	}
	
	/**
	 * 
	 * @param driver
	 * @param element
	 * @param message
	 * @param timeout
	 * @return
	 */
	public static WebElement elementToBeClickable( WebDriver driver, WebElement element, String message, int timeout ) {
		
		return until( driver, ExpectedConditions.elementToBeClickable( element ), message, timeout );
	}
	
	/**
	 * 
	 * @param driver
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public static Boolean invisibilityOfElementLocated( WebDriver driver, By locator, int timeout ) {
		
		return until( driver, ExpectedConditions.invisibilityOfElementLocated( locator), timeout );
	}
	
	/**
	 * 
	 * @param driver
	 * @param locator
	 * @param message
	 * @param timeout
	 * @return
	 */
	public static Boolean invisibilityOfElementLocated( WebDriver driver, By locator, String message, int timeout ) {
	
		return until( driver, ExpectedConditions.invisibilityOfElementLocated( locator), message, timeout );
	}
}