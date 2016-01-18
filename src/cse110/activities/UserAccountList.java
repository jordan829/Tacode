package cse110.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import cse110.helpers.ActionBarHelper;
import cse110.helpers.Constants;
import cse110.models.Account;
import cse110.models.User;
import cse110.tacode.R;

/**
 * Activity class responsible for displaying the user's accounts
 * in the Accounts page. It dynamically lists all account and their balance
 * in buttons. It also allows the user to visit each account simply by
 * clicking on the button it is displayed on.
 * 
 * @author
 *
 */
public class UserAccountList extends Activity implements OnClickListener {
	/* Global variables */
	private User user;
	int numAccounts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().setHomeButtonEnabled(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_account_list);
		
		// Fill user
		user = User.getUser();
		
		layoutBuilder();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_bar_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		
		// Handling Action Bar item selection
		switch (item.getItemId()) {
		case R.id.action_settings:
			ActionBarHelper.goToSettings(this);
			return true;
		case R.id.action_logout:
			ActionBarHelper.logout(this);
			return true;
		case android.R.id.home:
		     ActionBarHelper.goHome(this);
		     return true;
		default:
			return super.onOptionsItemSelected(item);
	}
	}
	
	/**
	 * Creates an account button for each user account. Adds a transfers and 
	 * close account button
	 */
	private void layoutBuilder() {
		// Adding button for accounts
		// Get parent
		LinearLayout lLayout = (LinearLayout) findViewById(R.id.lLayout);
		for(numAccounts = 0; numAccounts < user.getAccounts().size(); numAccounts++) {
			addButton(user.getAccounts().get(numAccounts), numAccounts, lLayout);
		}
	}
	
	/**
	 * Adds another button that displays a user account's type and amount
	 * 
	 * @param account Account to be displayed
	 * @param id  Account id
	 * @param parent ViewGroup element that will add a view
	 * 					object
	 */
	private void addButton(Account account, int id, ViewGroup parent) {	
		// Setting Layout parameters
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.WRAP_CONTENT);
		
		// Setting proper view dimensions
		final float scale = getResources().getDisplayMetrics().density;
		int padding_20dp = (int) (20 * scale + 0.5f);
		
		// Setting padding
		params.setMargins(0, padding_20dp, 0, 0);
		
		// Setting button displaying one of the user's accounts
	    Button b1 = new Button(this);
	    b1.setText(account.getType() + "(" + account.getID() + ")" + " $" + account.getBalance());
	    b1.setBackgroundResource(R.drawable.ui_homebuttons);
	    b1.setLayoutParams(params);
	    b1.setTextColor(Color.rgb(255, 255, 255));
	    b1.setTextSize(18);	    
	    b1.setTag(id);
	    b1.setOnClickListener(this);
	    // Displaying button
	    parent.addView(b1);
	}
	

	@Override
	public void onClick(View v) {
		startAccountActivity(v);
	}
	/**
	 * Redirects the user back to the accounts page
	 * 
	 * @param view reference to the view that called this handler
	 */
	private void startAccountActivity(View view) {
    	// instantiate new intent to redirect to UserAccount activity
    	Intent intent = new Intent(this, UserAccount.class);
    	
    	// Put the index into the accounts array to indicate which account page
    	intent.putExtra(Constants.INTENT_ACCOUNT_INDEX, (Integer) view.getTag());

    	// start UserAccountCreate activity
    	startActivity(intent);
    }
}
