package Tacode.tests;

import org.junit.Test;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import junit.framework.TestCase;            // Test case
import android.widget.EditText;
import java.util.*;
import static org.junit.Assert.*;

/*
 * Account creation
 * @author: Sonal, Julie
 */

public class UserAccountCreateTest {

	/*
	 * setUp method initializes the user
	 */
	 public void setUp()
	 {
	    user1 = new User();
	    user2 = new User();
	    user3 = new User();
	 }

	/*
	 * tearDown method de-initializes the user
	 */
	 public void tearDown()
	 {
		 user1 = new User();
		 user2 = new User();
		 user3 = new User();
     }
	
    /**
	 * Checks if inputed username is unique, username is
	 * case-sensitive
	 */
	 public void testValidateUser() {
		 user1.setFirst_name(Jane);
		 user1.setLast_name(Doe);
		 // First and last name stored properly
		 assertTrue(user1.first_name == "Jane");
		 assertTrue(user1.last_name == "Doe");
		 
		 user1.setUsername(janedoe);
		 user2.setUsername(johndoe);
		 user3.setUsername(janedoe); // Result in error
		 assertFalse(user3.getUsername == "janedoe");
     }

    /**
     * No fields can be left blank when creating account
     */
     public void testFilledFields() {
    	user3.setusername(sheryl);
    	user3.setAddress(123 Rainbow Lane);
    	user3.setEmail(sheryl@gmail.com);
    	user3.setcity(Menlo Park);
    	user3.setFirst_name(Sheryl);
    	user3.setLast_name(Sandberg);
    	user3.setPassword(leanin);
    	assertTrue();
	 }
     
     /**
      * Input is all ints
      */
     public void testValidateNumberField( String input ) {
    	 for( int i = 0; i < input.length(); ++i )
    		 assertTrue((input.isDigit(input.charAt(i))));
     }
     
     /**
      * Input is all letters
      */
     public void testValidateTextField ( String input ) {
    	 for( i = 0; i < input.length(); ++i ) 
    		 assertTrue((input.isLetter(input.charAt(i))));
     }
     
     /**
      * Password must be at least 8 chars, have 1 number,
      * and 1 uppercase letter minimums
      */
     public void testValidatePassField(String input) {
    	 user1.setPassword(Abcdefghi9);
    	 assertTrue(user1.getPassword() == "Abcdefghi9");
    	 
    	 // Check length
    	 user1.setPassword(A1cdef);
         assertFalse(user1.getPassword == "A1cdef");
         
         // Check for number
         user2.setPassword(abcdefgH);
         assertFalse(user2.getPassword == "abcdefgH");
         
         // Check for uppercase
         user3.setPassword(abcdefgh);
         assertFalse(user3.getPassword == "abcdefgh");
    	 }
     }
     
     /**
      * Checks for @ symbol
      */
     public void testValidateEmailField() {
   	 
    	 // valid email
    	 user1.setEmail("tacode@gmail.com");    	 
    	 assertTrue( user1.email == "tacode@gmail.com" );
    	 
    	 // invalid email
    	 user2.setEmail( "tacode" );
    	 assertFalse( user2.getEmail == "tacode" );
     }
     
     /**
      * Checks for 9 digits
      * Calls testValidateNumberField
      */
     public void testValidateSSNField() {
    	 user1.setSsn(123456789);
    	 assertTrue(user1.getSsn() == 123456789);
    	 
    	 user1.setSsn(12345);
    	 assertFalse(user1.getSsn() == 12345);
    	 
    	 user1.setSsn(123aaa);
    	 assertFalse(user1.getSsn() == 123aaa);
     }
     
     /**
      * Valid abbreviation for states, case-insensitive, stored as uppercase
      * Calls testValidateTextField
      */
     public void testValidateStateField() {
    	 
    	 // check valid entry
    	 user1.setState("CA");
    	 assertTrue( user1.getState() == "CA" );
    	 
    	 user1.setState("ny");
    	 assertTrue( user1.getState() == "NY" );
    	 
    	 user2.setState("43");
    	 assertFalse( user2.getState() == "43" );
    	 
    	 user2.setState("XYZ");
    	 assertFalse( user2.getState() == "XYZ" );
     }
     
     /**
      * Begins with numbers, space, and ends in all letters or '.'
      */
     public void testValidateAddressField() {

    	 // valid address
    	 user1.setAddress("123 Cherry Lane");
    	 assertTrue( user1.getAddress == "123 Cherry Lane" );
    	 
    	 // invalid address
    	 user2.setAddress("Emerald City");
    	 assertFalse( user2.getAddress == "Emerald City" );
     }
     
     /**
      * Tests for all alpha input for cities
      */
     public void testValidateCityField() {
    	 // valid city
    	 user1.setCity("San Diego");
    	 assertTrue( user1.getCity() == "San Diego" );
    	 
    	 // invalid city
    	 user2.setCity("L4 Jo11a");
    	 assertFalse( user2.getCity() == "L4 Jo11a" );
     }
     
     /**
      * Checks for 5 digits
      * Calls testValidateNumberField
      */
     public void testValidateZipcodeField() {
    	 
    	 // valid zipcode, integer input
    	 user1.setZipcode(12345);
    	 assertTrue(user1.getZipcode() == 12345);
    	 
    	 // valid zipode, string input
    	 user1.setZipcode("12345");
    	 assertTrue(user1.getZipcode == 12345);
    	 
    	 // invalid zipcode, integer input
    	 user2.setZipcode(654321);
    	 assertFalse( user2.getZipcode == 654321 );
    	 
    	 // invalid zipcode, string input
    	 user2.setZipcode("654321");
    	 assertFalse( user2.getZipcode == 654321 );

    	 // invalid zipcode, letter input
    	 user2.setZipcode("abcdef");
    	 assertFalse( user2.getZipcode == abcdef );
    	 
    	 // invalid zipcode, mixed input
    	 user2.setZipcode("654cba321");
    	 assertFalse( user2.getZipcode == 654cba321 );
     }
}
