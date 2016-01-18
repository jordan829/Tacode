/**
 * 
 */
package cse100.logging;

import cse110.error.ErrorPair;
import cse110.helpers.Constants;

/**
 * This class holds static methods for logging debug messages.
 * @author Casey
 */
public class Tacode_Logger {
	
	/**
	 * Outputs the ErrorPair to console.
	 * @param e ErrorPair to output to log
	 */
	public static void logError(ErrorPair e) {
		if(e.getDebugLevel() <= Constants.DEBUG_LEVEL) {
			System.err.println(Constants.LOG_HEADER  + ": "  + e.getField() + ": "
					+ e.getErrorMessage());
			
		}
	}
	
	/**
	 * 
	 * @param s
	 */
	public static void logString(String s) {
		System.err.println(Constants.LOG_HEADER + ": " + s);
	}
}
