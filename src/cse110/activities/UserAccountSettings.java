package cse110.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cse110.helpers.ActionBarHelper;
import cse110.helpers.Constants;
import cse110.helpers.PopUp;
import cse110.models.Account;
import cse110.models.User;
import cse110.phpCommunication.ActivityResponse;
import cse110.phpCommunication.CloseAccountRequest;
import cse110.phpCommunication.CloseOneAccountRequest;
import cse110.phpCommunication.OpenAccountRequest;
import cse110.phpCommunication.Request;
import cse110.phpCommunication.Response;
import cse110.tacode.R;

/**
 * Activity that allows the user to close or open an account and
 * to close the main account. 
 * 
 * @author 
 */
public class UserAccountSettings extends Activity {
	/* Global variables */
	private User user;
	private List<Account> accounts = new ArrayList<Account>();
	private String[] accountsList;
	private String accountType = "";
	private String accountToClose;
	private AlertDialog.Builder dialogBuilder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().setHomeButtonEnabled(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_account_settings);
		
		user = User.getUser();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Get accounts
		user = User.getUser();
		accounts = user.getAccounts();	
		
		// Setting string array to be used for account selection
		accountsList = new String[accounts.size()];
		for(int i = 0; i < accountsList.length; i++) {
			accountsList[i] = accounts.get(i).getType() + " (" + accounts.get(i).getID() + ") - $" + accounts.get(i).getBalance();
		}
		
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
	 * Event handler for the Open Bank Account button. Opens pop up menu
	 * to specify which type of account to open
	 * 	
	 * @param view reference to the view that called this handler
	 */
	public void openBankAccount(View view) {
		// Setting title and message for PopUp Window
		dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder.setTitle("Open Sub Account");
		dialogBuilder.setMessage("Choose account type");
		
		// Setting Account Checkings type option in PopUp Window
		dialogBuilder.setNegativeButton("Checkings", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				accountType = Constants.VALUE_OPEN_ACCOUNT_CHECKINGS;
				openBankAccount();
			}
		});
		
		// Setting Account Savings type option in PopUp Window
		dialogBuilder.setPositiveButton("Savings", new DialogInterface.OnClickListener() {	
			@Override
			public void onClick(DialogInterface dialog, int which) {
				accountType = Constants.VALUE_OPEN_ACCOUNT_SAVINGS;
				openBankAccount();
			}
		});
		
		// Display PopUp Window
		AlertDialog alert = dialogBuilder.create();
		alert.show();
	}
	
	/**
	 * Submitting request to open an account to the server
	 */
	private void openBankAccount() {
		// Building request
		Request request = new OpenAccountRequest(user, accountType, new ActivityResponse() {
			public void response(Response resp) {
				openBankAccountResponse(resp);
				PopUp.stop();
			}
		});
		request.execute();
		
		PopUp.start(this);
	}
	
	/**
	 * Called when open account request is over in order to determine
	 * next action
	 * 
	 * @param r response object
	 */
	private void openBankAccountResponse(Response r) {
		PopUp.stop();
		// If there are no errors, user is redirected to home page
		if(r.getError().hasErrors()) {
			r.getError().displayErrorFeedback(this);
		} else {
			Intent intent = new Intent(this, UserHome.class);
			startActivity(intent);
		}
	}
	
	/**
	 * Event handler for the Close Bank Account button. Redirects user to the
	 * close account settings page.
	 * 	
	 * @param view reference to the view that called this handler
	 */
	public void closeBankAccount(View view) {
		// 	instantiate new intent to redirect to UserCloseBankAccount (?) activity
		//TODO Intent intent = new Intent(this, );
		
		dialogBuilder = new AlertDialog.Builder(this);
		//final EditText editText = (EditText)view;
				
		//Process
		dialogBuilder.setTitle("Choose Account");
		dialogBuilder.setItems(accountsList, new DialogInterface.OnClickListener() {	
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				accountToClose = accounts.get(which).getID();
				closeBankAccount(accountToClose);
			}
				});
				
		//Display
		AlertDialog dialogFromAccount = dialogBuilder.create();
		dialogFromAccount.show();
		// start UserCloseBankAccount activity
		//startActivity(intent);
	}
	
	/**
	 * Submitting request to close an account to the server
	 */
	public void closeBankAccount(String accountID) {
		// Get user's accounts
		Request request = new CloseOneAccountRequest(user, accountID, new ActivityResponse() {
			public void response(Response resp) {
				closeBankAccountResponse(resp);
				PopUp.stop();
			}
		});
		request.execute();
		
		PopUp.start(this);
	}
	
	/**
	 * Called when close a bank account request is over in order to determine
	 * next action
	 * 
	 * @param r response object
	 */
	public void closeBankAccountResponse(Response r) {
		PopUp.stop();
		if(r.getError().hasErrors()) {
			// If the last account was deleted, the user will be deactivated so go to login page
			if(r.getError().hasFieldMatchesString(Constants.DELETED_LAST_ACCOUNT)){
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				this.finish();
			}
			r.getError().displayErrorFeedback(this);
		}
		else {
			Intent intent = new Intent(this, UserHome.class);
			startActivity(intent);
		}
	}
	
	/**
	 * Event handler for the Close Main Account button. Redirects user to the
	 * close main account settings page.
	 * 	
	 * @param view reference to the view that called this handler
	 */
	public void closeMainAccount(View view) {
		// 	instantiate new intent to redirect to UserCloseMainAccount (?) activity
		//TODO Intent intent = new Intent(this, );
		// start UserCloseMainAccount activity
		//startActivity(intent);
		
		
		dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder.setTitle("Confirm");
		dialogBuilder.setMessage("Are you sure you want to deactivate your account?");
		
		dialogBuilder.setNegativeButton("Yes", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				closeMainAccount();
			}
		});
		
		dialogBuilder.setPositiveButton("No", new DialogInterface.OnClickListener() {	
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		
		AlertDialog alert = dialogBuilder.create();
		alert.show();
		
	}
	
	/**
	 * Submitting request to close the main account to the server
	 */
    private void closeMainAccount() {
		Request request = new CloseAccountRequest(user, new ActivityResponse() {
			public void response(Response resp) {
				closeMainAccountResponse(resp);
				PopUp.stop();
			}
		});
		request.execute();
		
		PopUp.start(this);
    }
    
    /**
	 * Called when the close main account request is over in order to determine
	 * next action
	 * 
	 * @param r response object
	 */
	private void closeMainAccountResponse(Response r) {
		PopUp.stop();
		if(r.getError().hasErrors()) {
			r.getError().displayErrorFeedback(this);
		} else {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			this.finish();
		}
	}
}
