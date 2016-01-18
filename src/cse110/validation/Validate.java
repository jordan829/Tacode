package cse110.validation;

import cse110.error.Errors;
import cse110.error.ErrorPair;
import cse110.helpers.Constants;


/**
 * This class contains static methods for validating different
 * types of input text
 * @author Casey
 */
public class Validate {
	
	/**
	 * 
	 * @param s String to validate
	 * @return Errors object containing zero or more ErrorPairs
	 */
	static public Errors validateFirstNameField(String s) {
		Errors errors = new Errors();
		System.out.println("Checking first name: " + s);
		// Checks if s is at least one length 1 and consists
		// of only letters
		if(s.equals("") || !s.matches("[a-zA-Z]+")){
			ErrorPair ep = new ErrorPair(Constants.FIELD_FIRST_NAME,
					Constants.MESSAGE_INVALID_FIRST_NAME);
			errors.add(ep);
		}
		return errors;
	}
	
	/**
	 * 
	 * @param s String to validate
	 * @return Errors object containing zero or more ErrorPairs
	 */
	static public Errors validateLastNameField(String s) {
		Errors errors = new Errors();
		
		// Checks if s is at least one length 1 and consists
		// of only letters
		if(s.equals("") || !s.matches("[a-zA-Z]+")){
			ErrorPair ep = new ErrorPair(Constants.FIELD_LAST_NAME,
					Constants.MESSAGE_INVALID_LAST_NAME);
			errors.add(ep);
		}
		return errors;
	}
	
	/**
	 * 
	 * @param s String to validate
	 * @return Errors object containing zero or more ErrorPairs
	 */
	static public Errors validateAddressField(String s) {
		Errors errors = new Errors();
		
		// Checks if s is an address format
		if(!s.matches("\\d+\\s+[a-zA-Z0-9\\s]+")){
			ErrorPair ep = new ErrorPair(Constants.FIELD_STREET_ADDRESS,
					Constants.MESSAGE_INVALID_STREET_ADDRESS);
			errors.add(ep);
		}
		return errors;
	}
	
	/**
	 * 
	 * @param s String to validate
	 * @return Errors object containing zero or more ErrorPairs
	 */
	static public Errors validateCityField(String s) {
		Errors errors = new Errors();
		
		// Checks if s is a city name
		if(!s.matches("[a-zA-Z]+[a-zA-Z\\s]*")){
			ErrorPair ep = new ErrorPair(Constants.FIELD_CITY,
					Constants.MESSAGE_INVALID_CITY);
			errors.add(ep);
		}
		return errors;
	}
	
	/**
	 * 
	 * @param s String to validate
	 * @return Errors object containing zero or more ErrorPairs
	 */
	static public Errors validateEmailField(String s) {
		Errors errors = new Errors();
		
		// Checks if s is a valid email address format
		if(!s.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")){
			ErrorPair ep = new ErrorPair(Constants.FIELD_EMAIL,
					Constants.MESSAGE_INVALID_EMAIL);
			errors.add(ep);
		}
		return errors;
	}
	
	/**
	 * 
	 * @param s String to validate
	 * @return Errors object containing zero or more ErrorPairs
	 */
	static public Errors validatePasswordField(String s) {
		Errors errors = new Errors();
		
		// Checks to see if s is at least minimum length
		if(s.length() < Constants.MIN_PASSWORD_LENGTH){
			ErrorPair ep = new ErrorPair(Constants.FIELD_PASSWORD,
					Constants.MESSAGE_INVALID_PASSWORD);
			errors.add(ep);
		}
		return errors;
	}
	
	/**
	 * 
	 * @param s String to validate
	 * @return Errors object containing zero or more ErrorPairs
	 */
	static public Errors validateSSNField(String s) {
		Errors errors = new Errors();
		
		// Check to see if s is of pattern ###-##-####
		if(!s.matches("\\d{3}-\\d{2}-\\d{4}")){
			ErrorPair ep = new ErrorPair(Constants.FIELD_SSN, 
					Constants.MESSAGE_INVALID_SSN);
			errors.add(ep);
		}
		return errors;
	}
	
	/**
	 * 
	 * @param s String to validate
	 * @return Errors object containing zero or more ErrorPairs
	 */
	static public Errors validateStateField(String s) {
		Errors errors = new Errors();
		
		// Checks if s is length of 2 and consists of only letters
		if(!s.matches("[a-zA-Z]{2}")){
			ErrorPair ep = new ErrorPair(Constants.FIELD_STATE,
					Constants.MESSAGE_INVALID_STATE);
			errors.add(ep);
		}
		return errors;
	}
	
	/**
	 * 
	 * @param s String to validate
	 * @return Errors object containing zero or more ErrorPairs
	 */
	static public Errors validateDecimalField(String s) {
		Errors errors = new Errors();
		if(!s.matches("[\\d]+[.\\d{2}]{0,1}")){
			ErrorPair ep = new ErrorPair(Constants.FIELD_TRANSACTION,
					Constants.MESSAGE_INVALID_TRANSACTION);
			errors.add(ep);
		}
		return errors;
	}
	
	/**
	 * 
	 * @param s String to validate
	 * @return Errors object containing zero or more ErrorPairs
	 */
	static public Errors validateZipcodeField(String s) {
		Errors errors = new Errors();
		
		// Checks if s is length of 5 and is all digits
		if(!s.matches("\\d{5}")){
			ErrorPair ep = new ErrorPair(Constants.FIELD_ZIPCODE,
					Constants.MESSAGE_INVALID_ZIPCODE);
			errors.add(ep);
		}
		return errors;
	}
	
	/**
	 * 
	 * @param s String to validate
	 * @return Errors object containing zero or more ErrorPairs
	 */
	static public Errors validateUsernameField(String s) {
		Errors errors = new Errors();
		
		// Checks to see if s is at least minimum length and
		// consists of only letters and numbers
		if(s.length() < Constants.MIN_USERNAME_LENGTH
				|| !s.matches("\\w*")){
			ErrorPair ep = new ErrorPair(Constants.FIELD_USERNAME,
					Constants.MESSAGE_INVALID_USERNAME);
			errors.add(ep);
		}
		return errors;
	}
}

