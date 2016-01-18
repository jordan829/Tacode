package cse110.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import cse100.logging.Tacode_Logger;
import cse110.helpers.ActionBarHelper;
import cse110.helpers.PopUp;
import cse110.models.Account;
import cse110.models.User;
import cse110.phpCommunication.ActivityResponse;
import cse110.phpCommunication.GetAccountsRequest;
import cse110.phpCommunication.Request;
import cse110.phpCommunication.Response;
import cse110.tacode.R;

/**
 * Activity class responsible for displaying the user's home page.
 * It allows the user to go to their accounts page and transfers page.
 * 
 * @author 
 */
public class UserHome extends Activity{
	/* Global variables */
	String userid;
	String token;
	int numAccounts;
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		getActionBar().setHomeButtonEnabled(false);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_home);
		
		user = User.getUser();
		displayUserId();
		
	    //Calculate screen dimensions
  		Display display = getWindowManager().getDefaultDisplay();
  		Point size = new Point();
  		display.getSize(size);
  		int width, height;
  		
  		if (size.x < size.y) //Portrait mode
  		{
  			width = (int) size.x/2;
  			height = width;
  		}
  		
  		else //Landscape mode
  		{
  			width = (int) size.y/2;
  			height = width;
  		}
  		
  		// Tacode Animation!! :D
		final Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.rotation_animation);
		Button btnRotate = (Button)findViewById(R.id.logo);
		
  		//Set dimensions of logo on homepage
  		android.view.ViewGroup.LayoutParams layoutParams = btnRotate.getLayoutParams();
  		layoutParams.width = width;
  		layoutParams.height = height;
  		btnRotate.setLayoutParams(layoutParams);
		
  		// Rotates Tacode logo when it is clicked
		btnRotate.setOnClickListener(new Button.OnClickListener(){

			  public void onClick(View view) {
			   view.startAnimation(animRotate);
			  }});
		
		// Fill user
		Tacode_Logger.logString("Username: " + user.getUsername() + " Password: " + user.getPassword());
		
		// Get user's accounts
		Request request = new GetAccountsRequest(user, new ActivityResponse() {
			public void response(Response resp) {
				getAccountsResponse(resp);
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
	 * Event handler for the transfer button.
	 * 
	 * @param view reference to the view that called this handler
	 */
	public void startTransferActivity(View view) {
		Intent intent = new Intent(this, UserTransfer.class);
		startActivity(intent);
	}
	
    /**
     * Event handler for the account button. Redirects user to the
     * account page.
     * 
     * @param view reference to the view that called this handler
     */
    public void userAccountList(View view) {
    	// instantiate new intent to redirect to UserAccount activity
    	Intent intent = new Intent(this, UserAccountList.class);

    	// start UserAccountCreate activity
    	startActivity(intent);
    }
    
    /**
     * Response from server when getting accounts
     * @param r the response object from the request
     */
	@SuppressWarnings("unchecked")
	private void getAccountsResponse(Response r) {		
		if(r.getError().hasErrors()) {
			r.getError().displayErrorFeedback(this);
		} else {
			user.setAccounts((List<Account>) r.getResponse());
		}
	}
	
	/**
	 * Display user-id.
	 */
	private void displayUserId() {
		TextView t = (TextView) findViewById(R.id.activity_user_account_name_banner);
		t.setText(t.getText() + " (" + user.getUserid() + ")");
	}
}