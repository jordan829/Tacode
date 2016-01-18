package cse110.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import cse110.helpers.ActionBarHelper;
import cse110.helpers.PopUp;
import cse110.models.Account;
import cse110.models.Transfer;
import cse110.models.User;
import cse110.phpCommunication.ActivityResponse;
import cse110.phpCommunication.Request;
import cse110.phpCommunication.Response;
import cse110.phpCommunication.TransferRequest;
import cse110.tacode.R;

/**
 * Activity class responsible for money transactions among personal accounts
 * and internal accounts
 * 
 * @author 
 */
public class UserTransfer extends Activity {
	/* Global variables */
	private List<Account> accounts = new ArrayList<Account>();
	private String[] accountsList;
	private AlertDialog.Builder dialogBuilder;
	boolean isPersonal;
	private String account1, account2, amount;
	private User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//
		getActionBar().setHomeButtonEnabled(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_transfer);

		// Get accounts
		user = User.getUser();
		accounts = user.getAccounts();	
		
		// Fill accountsList
		accountsList = new String[accounts.size()];
		for(int i = 0; i < accountsList.length; i++) {
			accountsList[i] = accounts.get(i).getType() + " (" + accounts.get(i).getID() + ") - $" + accounts.get(i).getBalance();
		}
		
		// display personal transfer
		personalTransfer(null);
		
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
	 * Event handler for the select account fields in both, personal 
	 * and internal transfers. It displays a PopUp Window that lists
	 * the user's accounts.
	 * 
	 * @param view reference to the view that called this handler
	 */
	public void onClick(View view) {
		
		//variables
		dialogBuilder = new AlertDialog.Builder(this);
		final EditText editText = (EditText)view;
				
		//Process: Setting title to PopUp Window and listing accounts
		dialogBuilder.setTitle("Choose an Account");
		dialogBuilder.setItems(accountsList, new DialogInterface.OnClickListener() {	
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Setting text field to selected account
				editText.setText(accountsList[which]);
				// Disabling keyboard on text field
				editText.setKeyListener(null);
				
				// Update account1/2
				if(isPersonal) {
					// Check if first or second field
					if(editText.getId() == R.id.personal_field1) {
						account1 = accounts.get(which).getID();
					} else {
						account2 = accounts.get(which).getID();
					}
				} else {
					account1 = accounts.get(which).getID();
				}
			}
				});
				
		//Display PopUp window
		AlertDialog dialogFromAccount = dialogBuilder.create();
		dialogFromAccount.show();
	}
	
	/**
     * Event handler for the personalTransfer button used to initiate
     * a transfer between a user's own accounts
     * 
     * @param view reference to the view that called this handler
     */
	public void personalTransfer(View view)
	{
		isPersonal = true;
		
		//Make submit button visible
		Button submit = (Button) findViewById(R.id.activity_user_transfer_submit);
		submit.setVisibility(1);
		
		//Toggles visibility of personal transfer fields
		ViewGroup view1 = (ViewGroup)findViewById(R.id.lLayout);
		view1.removeAllViews();
		LayoutInflater myInflater = getLayoutInflater(); 
		View myView = myInflater.inflate(R.layout.personal_transfer, view1, false);
		view1.addView(myView);
		// Disabling keyboard on text field
		((EditText) findViewById(R.id.personal_field1)).setKeyListener(null);
		((EditText) findViewById(R.id.personal_field2)).setKeyListener(null);
	}
	
	/**
     * Event handler for the internalTransfer button used to initiate
     * a transfer between a user's own account and another user's account
     * 
     * @param view reference to the view that called this handler
     */
	public void internalTransfer(View view)
	{
		isPersonal = false;
		//Make submit button visible
		Button submit = (Button) findViewById(R.id.activity_user_transfer_submit);
		submit.setVisibility(1);
		
		//Toggles visibility of internal transfer fields
		ViewGroup view1 = (ViewGroup)findViewById(R.id.lLayout);
		view1.removeAllViews();
		LayoutInflater myInflater = getLayoutInflater(); 
		View myView = myInflater.inflate(R.layout.internal_transfer, view1, false);
		view1.addView(myView);
		// Disabling keyboard on text field
		((EditText) findViewById(R.id.internal_field1)).setKeyListener(null);
	}
	
	/**
	 * Submitting the transfer to the server
	 * 
	 * @param view reference to the view that called this handler
	 */
	public void transferSubmit(View view) {
		// Make transfer object
		Transfer trans = makeTransferObject();
		
		// Build a request
		Request request = new TransferRequest(trans, isPersonal, new ActivityResponse() {
			public void response(Response r) {
				transferResponse(r);
				PopUp.stop();
			}
		});
		
		// Execute request
		request.execute();
		PopUp.start(this);
	}
	
	/**
	 * Called when request is finished
	 * @param r response object
	 */
	private void transferResponse(Response r) {
		if(r.getError().hasErrors()) {
			r.getError().displayErrorFeedback(this);
		} else {
			// Go to home page
			Intent intent = new Intent(this, UserHome.class);
			startActivity(intent);
			this.finish();
		}
	}
	
	/**
	 * Gets the user's desired transaction money from the input fields
	 * 
	 * @return String money amount
	 */
	private String getAmmount() {
		if(isPersonal) {
			return ((EditText) findViewById(R.id.personal_field3)).getText().toString();
		}
		return ((EditText) findViewById(R.id.internal_field3)).getText().toString();
	}
	
	/**
	 * Is responsible for creating a transfer object with the 
	 * specifications given by the user
	 * 
	 * @return Transfer object that will handle transfer
	 */
	private Transfer makeTransferObject() {
		
		Transfer trans;
		
		// Properly assigning transfer type
		if(isPersonal) {
			amount = getAmmount();
			// Build transaction
			trans = new Transfer(amount, account2, account1, user.getUserid(), user.getToken());
		} else {
			amount = getAmmount();
			account2 = ((EditText) findViewById(R.id.internal_field2)).getText().toString();
			// Build transaction
			trans = new Transfer(amount, account2, account1, user.getUserid(), user.getToken());
		}
		
		return trans;
	}
}
