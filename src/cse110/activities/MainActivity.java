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
import cse110.helpers.PopUp;
import cse110.helpers.ViewHelpers;
import cse110.models.User;
import cse110.phpCommunication.ActivityResponse;
import cse110.phpCommunication.AuthenticateRequest;
import cse110.phpCommunication.Request;
import cse110.phpCommunication.Response;
import cse110.tacode.R;

/**
 * Activity class responsible for the Login page UI. The 
 * user will be able to view the accounts by filling
 * the text fields displayed in this activity.
 * @author Casey
 *
 */
public class MainActivity extends Activity {		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
      //Calculate screen dimensions
      	Display display = getWindowManager().getDefaultDisplay();
      	Point size = new Point();
      	display.getSize(size);
      	int width, height;
      		
      	//Set width and height of logo to scale based on % of display size
      	if (size.x < size.y){ //Portrait mode
      		width = (int) size.x/2;
      		height = width;
      	}
      		
      	else{ //Landscape mode
      		width = (int) size.y/2;
      		height = width;
      	}
      		
      	//Set dimensions of logo
      	ImageView logo = (ImageView) findViewById(R.id.logo_image);
      	android.view.ViewGroup.LayoutParams layoutParams = logo.getLayoutParams();
      	layoutParams.width = width;
      	layoutParams.height = height;
      	logo.setLayoutParams(layoutParams);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	
        return super.onOptionsItemSelected(item);
    }
    
    /**
     * Event handler for the clear_pass button used to clear text
     * entered in the username field
     * 
     * @param view reference to the view that called this handler
     */
    public void clearUser(View view)
    {
    	EditText edittext;
    	edittext = (EditText) findViewById(R.id.main_text_username_field);
    	edittext.setText("");
    }  
    
    /**
     * Event handler for the clear_pass button used to clear text
     * entered in the password field
     * 
     * @param view reference to the view that called this handler
     */
    public void clearPass(View view)
    {
    	EditText edittext;
    	edittext = (EditText) findViewById(R.id.main_text_password_field);
    	edittext.setText("");
    }
    
    /**
     * Event handler for the main_submitLogin button. Attempts to log-in the
     * user. If authentication fails, returns user to login screen.
     * 
     * @param view reference to the view that called this handler
     */
    public void submitLogin(View view) {
    	Errors errors;				 	// indicates if user is authenticated
    	String username="";				// input from main_text_username_field
    	String password="";   			// input from main_text_password_field
    	User loginUser;
    	
    	/**
    	 * Get user-name and password strings
    	 */		
		username = ViewHelpers.getViewText(this, R.id.main_text_username_field);
		password = ViewHelpers.getViewText(this, R.id.main_text_password_field);
		
		// stores user-name and password in User object
		loginUser = User.getUser();
		
		// add ErrorPairs from setter functions
		errors = (loginUser.setUsername(username));
		errors.append(loginUser.setPassword(password));
		
		// if there are any errors from setters
		if(!errors.hasErrors()) {
	    	// Create new request
	    	Request requester = new AuthenticateRequest(loginUser, new ActivityResponse() {
	    		public void response(Response resp) {
	    			authenticateResponse(resp);
	    			PopUp.stop();
	    		}
	    	});
	    	// Execute request
	    	requester.execute();
	    	
	    	// display loading
	    	PopUp.start(this);
	    	
		}
		
		// else errors
		errors.displayErrorFeedback(this);
    }
    
    /**
     * Event handler for the userRegistration button. Redirects user to the
     * registration page.
     * 
     * @param view reference to the view that called this handler
     */
    public void userRegistration(View view) {
    	// instantiate new intent to redirect to UserAccountCreate activity
    	Intent intent = new Intent(this, UserAccountCreate.class);
    	
    	// start UserAccountCreate activity
    	startActivity(intent);
    }

    /**
     * Response from user login attempt.
     * Note: Can ignore response because static user is set in response parsing
     * @param r the response from the server
     * @return
     */
	private void authenticateResponse (Response r) {
		PopUp.stop();
		if(r.getError().hasErrors()) {
			r.getError().displayErrorFeedback(this);
		} else {
			// Go to home page
			Intent intent = new Intent(this, UserHome.class);
			startActivity(intent);
			finish();
		}
	}
}