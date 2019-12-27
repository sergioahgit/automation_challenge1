package framework.commons.ui.condition.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

/*
 * This class defines a condition when a page has successfully scrolldown to the bottom of the page.
 * Use this class with the framework.commons.ui.util.Wait class
 * 
 * @author : Sergio A. Hernandez
 * @version : 1.0
 * @since 2019-12-25
 * 
 */
public class ScrollToBottomPage implements ExpectedCondition< Boolean > {

	private long timeout;
	private boolean printStackTrace;
	
	public ScrollToBottomPage( long timeout, boolean printStackTrace ) {
		
		this.timeout         = timeout;
		this.printStackTrace = printStackTrace;
	}
	
	/**
	 * Scrolls to the end of the page.
	 * 
	 * @param je                    The JavascriptExecutor
	 * @param timeout               Time to wait between scroll.  If less than or equal to zero, do not sleep at all.
	 * @throws InterruptedException If interrupted while sleeping
	*/
	private void scrollDownToEnd( JavascriptExecutor je, long timeout, boolean printStackTrace ) throws InterruptedException {
	
		/* --- Let's get the scroll height. --- */
		long lastHeight = ( long )je.executeScript( "return document.body.scrollHeight" );
		long currentHeight = lastHeight;
		
		while( true ) {
			
			/* --- Let's scroll to the bottom of the page. --- */
			je.executeScript( "window.scrollTo( 0, document.body.scrollHeight);" );
			
			try {
			
				/* --- Let's wait for 3 seconds before maximizing the browser. ---*/
		        TimeUnit.MILLISECONDS.sleep( timeout );
		     
		        /* --- Let's now get the new height. --- */
				currentHeight = ( long )je.executeScript( "return document.body.scrollHeight" );
				
				/* --- Let's compare the heights. --- */
				if( currentHeight == lastHeight ) 
					break;
				
				/* --- Otherwise, we got a new height and need to continue scrolling. --- */
				lastHeight = currentHeight;
				
		    } catch( InterruptedException e ) {
		    	
		    	/* --- Let's print the stack trace. --- */
		    	if( printStackTrace )
		    		e.printStackTrace( );
		    	
		    	throw e;
		        
		    }	
		}
	}
	
	@Override
	public Boolean apply( WebDriver driver ) {
		
		try {
			
			/* --- Let's scroll to the end of the page. --- */
			scrollDownToEnd( ( JavascriptExecutor )driver, this.timeout, this.printStackTrace );
			
		} catch( InterruptedException e ) {
	
			/* --- Return false if there was a problem. --- */
			return false;
		}
		
		return true;
	}
}