package framework.commons.util;

import java.util.concurrent.TimeUnit;

/*
 * This class defines a Time methods static methods that may be used throughout the framework.
 * 
 * @author : Sergio A. Hernandez
 * @version : 1.0
 * @since 2019-12-25
 * 
 */
public class Time {

	protected Time( ) { }
	
	/**
	 * Performs a Thread.sleep using time unit.
	 * This is a convenience method that converts time arguments into the required by the Thread.sleep method.
	 * Parameters:milliseconds the minimum time to sleep. If less than or equal to zero, do not sleep at all.
	 * Throws:InterruptedException - if interrupted while sleeping

	 * @param milliseconds  Waits for a number of milliseconds.
	 * @return True if sleep succeeded.  Otherwise, false.
	 * 
	 */
	public static boolean waitFor( long milliseconds ) {
		
		try {
			
	        TimeUnit.MILLISECONDS.sleep( milliseconds );
	        
	    } catch( InterruptedException e ) {
	    	
	        e.printStackTrace( );
	        
	        return false;
	    }
		
		return true;
	} 
}
