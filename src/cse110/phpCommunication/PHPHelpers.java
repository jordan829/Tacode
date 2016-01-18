/**
 * 
 */
package cse110.phpCommunication;

import cse110.error.ErrorPair;
import cse110.error.Errors;
import cse110.helpers.Constants;

/**
 * This class holds static function to help with PHP communication
 * @author Casey
 */
public class PHPHelpers {
	/**
	 * Parse a string returned from server into an Errors object.
	 * Format is "Field:Message:Field:Message:Field:Message"...
	 * PRECONDITION: String must be in specified format
	 * @param str String in the specified format
	 * @return Errors object, may be empty if string is empty
	 */
	public static Errors parseErrors(String str) {
		//Checking if errors exist. If they exist, and error object is returned
		Errors errors = new Errors();
		if(str.isEmpty()) {
			return errors;
		}
		
		String delims = "[:]";
		String[] tokens = str.split(delims);
		ErrorPair ep;
		
		// make sure correct number of Field:Message pairs
		if((tokens.length%2) != 0) {
			errors.add(new ErrorPair(Constants.FIELD_ERRORS, Constants.FIELD_ERROR_PAIRS_NO_MATCH));
			return errors;
		}
		for(int i = 0; i < (tokens.length); i+=2) {
			ep = new ErrorPair(tokens[i], tokens[i+1]);
			errors.add(ep);
		}
		return errors;
	}
}
