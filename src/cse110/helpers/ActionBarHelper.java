package cse110.helpers;


import android.app.Activity;
import android.content.Intent;
import cse110.activities.MainActivity;
import cse110.activities.UserAccountSettings;
import cse110.activities.UserHome;
import cse110.models.User;

/**
 * Helper class that is responsible for the functionality of the 
 * items in the action bar
 * 
 * @author
 */

public class ActionBarHelper {

	/**
	 * Directs the user to the Settings page
	 * 
	 * @param a Activity from which the redirection
	 * 			to the settings page was selected
	 */
	public static void goToSettings(Activity a) {
		//Go to Settings page
		Intent intent = new Intent(a, UserAccountSettings.class);
    	a.startActivity(intent);
	}
	
	/**
	 * Lets the user logout from bank account
	 * 
	 * @param a Activity from which logout was selected
	 */
	public static void logout(Activity a) {
		// Directing to the LogIn page
		Intent intent = new Intent(a, MainActivity.class);
		User.getUser().setToken("");
		User.getUser().setUserid("");
		User.getUser().setAccounts(null);
		a.startActivity(intent);
		a.finish();	
	}
	
	/**
	 * Redirects the user to the home page
	 * @param a Activity from which home was selected
	 */
	public static void goHome(Activity a) {
		
		Intent intent = new Intent(a, UserHome.class);
		a.startActivity(intent);
	}
}
