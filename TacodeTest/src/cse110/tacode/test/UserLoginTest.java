package Tacode.tests;

import org.junit.Test;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import junit.framework.TestCase;            // Test case
import android.widget.EditText;
import java.util.*;
import static org.junit.Assert.*;

/*
 * User Banking
 * @author: Sonal, Julie
 */

public class UserLoginTest {

	/*
	 * setUp method initializes the user, and Authentication class
	 */
	 public void setUp() {
	    user1 = new User();
	    auth = new Authentication();
	 }

	/*
	 * tearDown method de-initializes the user
	 */
	 public void tearDown() {
		 user1 = new User();
     }
	
     /**
      * Expected valid login with correct password + user
      */
     public void testValidLogin( ) {
    	 
     }
     
     /**
      * Expected invalid login
      */
     public void testInvalidLogin() {
 
    	 
    	 // invalid user
    	 
    	 // valid user, invalid password
     }
}
