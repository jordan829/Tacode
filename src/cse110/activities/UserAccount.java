package cse110.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cse100.logging.Tacode_Logger;
import cse110.helpers.ActionBarHelper;
import cse110.helpers.Constants;
import cse110.helpers.PopUp;
import cse110.models.Account;
import cse110.models.Transaction;
import cse110.models.User;
import cse110.phpCommunication.ActivityResponse;
import cse110.phpCommunication.Request;
import cse110.phpCommunication.Response;
import cse110.phpCommunication.TransactionHistoryRequest;
import cse110.tacode.R;

/**
 * Activity class that is responsible for the user's accounts UI.
 * It uses the UserAccountList activity to dynamically display
 *  all of the user's accounts in the bank and their balances. 
 *  The user will be able to visit each account simply by clicking on it.
 *
 * @author 
 */
public class UserAccount extends Activity {
	 /* Global variables */
	private Account account;
	private int accountIndex;
	List<Transaction> transactions;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().setHomeButtonEnabled(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_account);
		
		// get accountIndex into accounts array
		Intent intent = getIntent();
		accountIndex = intent.getIntExtra(Constants.INTENT_ACCOUNT_INDEX, 0);
		account = User.getUser().getAccounts().get(accountIndex);
		
		// set balance
		setBalance(account.getBalance());
		((TextView) findViewById(R.id.activity_user_account_name_text)).setText(account.getType() + " (" + account.getID() + ")");
		
		// Make request for history (the response method calls layoutBuilder()
		Request request = new TransactionHistoryRequest(account, User.getUser(), new ActivityResponse() {
			public void response(Response resp) {
				historyResponse(resp);
				PopUp.stop();
			}
		});
		request.execute();
		PopUp.start(this);
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
	 * Sets user balance to the account's view field so the
	 * user can see the account balance
	 * 
	 * @param b Account money amount
	 */
	public void setBalance(String b) {
		TextView edit = (TextView) findViewById(R.id.activity_user_balance_text);
		edit.setText("$" + b);		
		Tacode_Logger.logString("" + b);
	}
	
	/**
	 * Goes through user's transactions and passes
	 * each of them to addText
	 */
	private void layoutBuilder() {
		
		// Get parent
		LinearLayout lLayout = (LinearLayout) findViewById(R.id.user_account_history);
		
		// Iterate over each transaction in list
		for(int numTrans = 0; numTrans < transactions.size(); numTrans++) {
			Transaction t = transactions.get(numTrans);
			addText(t.getType(), t.getDate(), t.getAmt(), lLayout);
		}
	}

	private void addText(String transType, String transDate, String transAmt, ViewGroup parent) {		
		//LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(vDem.getWidth(), vDem.getHeight());
		
		TextView history = new TextView(this);
		history.setText(transDate + " : " + transType + " $" + transAmt);
		history.setTextSize(18);
		
		parent.addView(history);
	}
	
	@SuppressWarnings("unchecked")
	private void historyResponse(Response r) {
		if(r.getError().hasErrors()) {
			r.getError().displayErrorFeedback(this);
		} else {
			transactions = (List<Transaction>) r.getResponse();
			layoutBuilder();
		}
	}
}
