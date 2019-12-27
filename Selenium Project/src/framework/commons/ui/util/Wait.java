package framework.commons.ui.util;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * This class defines a condition methods when interacting with the website.
 * For example:
 * 
 *  1.  Let's wait until the page has successfully loaded.
 *  2.  Let's wait until the page has successfully scrolled to the bottom of the page.
 *  3.  Let's wait until a WebElment is clickable.
 *  4.  etc ...
 *  
 * Use this class with the framework.commons.ui.util.Wait class
 * 
 * @author : Sergio A. Hernandez
 * @version : 1.0
 * @since 2019-12-25
 * 
 */
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