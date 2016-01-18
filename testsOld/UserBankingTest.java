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

public class UserBankingTest {

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
      * Input is all ints
      */
     public void testValidateTransaction( ) {
     }
     
}
