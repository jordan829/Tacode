package cse110.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import cse110.error.Errors;
import cse110.helpers.Constants;
import cse110.helpers.PopUp;
import cse110.helpers.ViewHelpers;
import cse110.helpers.ViewTransformations;
import cse110.models.User;
import cse110.phpCommunication.ActivityResponse;
import cse110.phpCommunication.RegisterRequest;
import cse110.phpCommunication.Request;
import cse110.phpCommunication.Response;
import cse110.tacode.R;
import cse110.validation.TextChangedValidator;

/**
 * Activity class responsible for displaying to the user
 * the account creation page. The user will be able
 * to create an account with the bank after filling the 
 * fields displayed by this activity and submitting. 
 * 
 * @author
 *
 */
public class UserAccountCreate extends Activity{
	/**
	 * Global variables
	 */
	private User regUser; // global user
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_account_create);
		
		//--- Set the tags for each EditText view
		setRegistrationTags();
		
		//--- Create onTextChangedListners for every EditText view to validate input
		for(int i = 0; i < Constants.USER_ACCOUNT_CREATE_IDS.length; i++) {
			TextChangedValidator.onTextChangedValidator((EditText) findViewById(Constants.USER_ACCOUNT_CREATE_IDS[i]));
		}	
		//--- Create onTextChangedListner for FIELD_SSN to add dashes
		ViewHelpers.addOnTextChangedSSN((EditText) findViewById(R.id.user_create_account_text_socialSecurityNumber_field));
		
		/*****************************************************************************/
	    //Calculate screen dimensions
	    Display display = getWindowManager().getDefaultDisplay();
	    Point size = new Point();
	    display.getSize(size);
	    int width, height;
	      		
	    //Set width and height of logo to scale based on % of display size
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
	      		
	    //Set dimensions of logo
	    ImageView logo = (ImageView) findViewById(R.id.logo_image);
	    android.view.ViewGroup.LayoutParams layoutParams = logo.getLayoutParams();
	    layoutParams.width = width;
	    layoutParams.height = height;
	    logo.setLayoutParams(layoutParams);
	    /*****************************************************************************/

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_account_create, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Event handler for the "create account" button
	 * @param view the calling view
	 */
	public void user_create_account_submitUserAccount(View view) {
		Errors errorsEncountered;		// errors object with any errors encountered
		
		//--- Create a User object with fields filled from registration form
		// Instantiate new User object to fill with registration info
		regUser = User.getUser();

		// Fill regUser's data
		errorsEncountered = fillUserObject(regUser);
		
		// Check for errors in filling regUser
		if(!errorsEncountered.hasErrors()) {
			// If no errors, create request to register
			Request requester = new RegisterRequest(regUser, new ActivityResponse(){
				public void response(Response resp){
					registerResponse(resp);
					PopUp.stop();
				}
			});
			
			requester.execute();
			PopUp.start(this);
		} else {
			// else, there were errors
			errorsEncountered.displayErrorFeedback(this);
			boxHighlighting(errorsEncountered);
		}
	}
	
	/**
	 * Helper function to fill a User object's fields from the registration form
	 * @param u User object to fill
	 */
	private Errors fillUserObject(User u) {
		Errors errors = new Errors();
		
    	//--- Get each EditText's field and store in regUser
    	// First name
    	errors.append(u.setFirst_name(ViewHelpers.getViewText(this, R.id.user_create_account_text_firstName_field)));
    	
    	// Last name
    	errors.append(u.setLast_name(ViewHelpers.getViewText(this, R.id.user_create_account_text_lastName_field)));
    	
    	// Street address
    	errors.append(u.setAddress(ViewHelpers.getViewText(this, R.id.user_create_account_text_streetAddress_field)));
    	
    	// City
    	errors.append(u.setCity(ViewHelpers.getViewText(this, R.id.user_create_account_text_city_field)));
    	
    	// State
    	errors.append(u.setState(ViewHelpers.getViewText(this, R.id.user_create_account_text_state_field)));
    	
    	// Zip Code
    	errors.append(u.setZipcode(ViewHelpers.getViewText(this, R.id.user_create_account_text_zipCode_field)));
    	
    	// FIELD_SSN
    	errors.append(u.setSsn(ViewHelpers.getViewText(this, R.id.user_create_account_text_socialSecurityNumber_field)));
    	
    	// Email
    	errors.append(u.setEmail(ViewHelpers.getViewText(this, R.id.user_create_account_text_email_field)));
    	
    	// Username
    	errors.append(u.setUsername(ViewHelpers.getViewText(this, R.id.user_create_account_text_createUsername_field)));
    	
    	// Password
    	errors.append(u.setPassword(ViewHelpers.getViewText(this, R.id.user_create_account_text_createPassword_field)));
    	
		return errors;
	}

	/**
	 * Helper method to set the tags of each user_account_create field. The tags will be used for
	 * validation checks.
	 */
	private void setRegistrationTags(){
		EditText text;
		for(int i = 0; i < Constants.USER_ACCOUNT_CREATE_IDS.length; i++){
			text = (EditText)findViewById(Constants.USER_ACCOUNT_CREATE_IDS[i]);
			text.setTag(Constants.USER_ACCOUNT_CREATE_NAMES[i]);
		}
	}
	

	/**
	 * Helper function that displays the necessary feedback to the user for each
	 * cse110.error
	 * @param e an Errors object to display errors from
	 */
	private void boxHighlighting(Errors errors) {
		//--- Individual box highlighting
		for(int i = 0; i < Constants.USER_ACCOUNT_CREATE_IDS.length; i++) {
			if(errors.hasFieldMatchesString(Constants.USER_ACCOUNT_CREATE_NAMES[i])) {
				ViewTransformations.highlightTextViewBorder((EditText) findViewById(Constants.USER_ACCOUNT_CREATE_IDS[i]));
			}
		}
	}

	/**
	 *  Action to take based on the server's response to the register request
	 * @param r Response object 
	 */
	private void registerResponse(Response r) {
		// Display errors if any
		if(r.getError().hasErrors()){
			r.getError().displayErrorFeedback(this);
			boxHighlighting(r.getError());
		} else {
			// Go to login page
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			this.finish();
		}	
	}
}