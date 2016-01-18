package Tacode.tests;

import org.junit.Test;

import cse110.tacode.Constants;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import junit.framework.TestCase;            // Test case
import android.widget.EditText;
import java.util.*;
import static org.junit.Assert.*;

/*
 * Tests Validate.java
 * @author: Sonal, Julie
 */

public class ValidateTest {
	
	/*
	 * setUp method initializes error(s)
	 */
	 public void setUp()
	 {
	    errors = new Errors();
	 }

	/*
	 * tearDown method de-initializes error(s)
	 */
	 public void tearDown()
	 {
		 errors = new Errors();
     }
	 
	 public void testValidateFirstNameField() {
		 validateFirstNameField(Sam123);
		 assertTrue(errors == Constants.FIELD_FIRST_NAME,
					Constants.MESSAGE_INVALID_FIRST_NAME);
		 validateFirstNameField(John);
		 assertTrue(!errors);
	 }
	 
	 public void testValidateLastNameField() {
		 validateFirstNameField(Smith12);
		 assertTrue(errors == Constants.FIELD_LAST_NAME,
					Constants.MESSAGE_INVALID_LAST_NAME);
		 validateLastNameField(Smith);
		 assertTrue(!errors);
	 }
	 
	 public void testValidateAddressField() {
		 validateAddressField(Lane 43 St);
		 assertTrue(errors == Constants.FIELD_STREET_ADDRESS,
					Constants.MESSAGE_INVALID_STREET_ADDRESS);
		 validateAddressField(365 Gilman Dr #65482);
		 assertTrue(!errors);
	 }
	 
	 public void testValidateCityField() {
		 validateCityField(Palo123 Alto);
		 assertTrue(errors == Constants.FIELD_CITY,
					Constants.MESSAGE_INVALID_CITY)
		 validateCityField(Mountain View);
		 assertTrue(!errors);
	 }
	 
	 public void testValidateEmailField() {
		 validateEmailField(sp123.gmail.com);
		 assertTrue(errors == Constants.FIELD_EMAIL,
					Constants.MESSAGE_INVALID_EMAIL);
		 validateEmailField(soprasad@ucsdedu);
		 assertTrue(errors == Constants.FIELD_EMAIL,
					Constants.MESSAGE_INVALID_EMAIL);
		 validateEmailField(soprasad@ucsd.edu);
		 assertTrue(!errors);
	 }
	 
	 
}