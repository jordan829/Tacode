package cse110.helpers;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Singleton progressDialog pop-up.
 * @author Casey
 *
 */
public class PopUp {
	static ProgressDialog pDialog = null;
	
	/**
	 * Displays loading dialog
	 * @param a current activity
	 */
	public static void start(Activity a) {
		pDialog = new ProgressDialog(a);
		pDialog.setMessage(Constants.MESSAGE_LOGIN);
		pDialog.show();
	}
	
	/**
	 * Dismisses the loading dialog
	 */
	public static void stop() {
		// Close processDialog
		if(pDialog != null && pDialog.isShowing()) {
			pDialog.dismiss();
		}
	}
}
