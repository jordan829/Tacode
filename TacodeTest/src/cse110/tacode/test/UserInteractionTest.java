package Tacode.tests;

import org.junit.Test;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import junit.framework.TestCase;            // Test case
import android.widget.EditText;
import java.util.*;
import static org.junit.Assert.*;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/*
 * User interaction test
 * @author: Julie
 */

public class UserInteractionTest extends UiAutomatorTestCase {

	public UserInteractionTest() {
		
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		// disable touch mode for automated testing
		setActivityInitialTouchMode(false);		
	}

	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * Tests that UI elements are displayed correctly
	 */
	public void testLayout throws UiObjectNotFoundException {
		
	}
}
