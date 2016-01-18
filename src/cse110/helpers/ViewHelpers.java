/**
 * 
 */
package cse110.helpers;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * This class holds static functions for interacting with view elements
 * @author Casey
 */
public class ViewHelpers {
	/**
	 * Extract text from an TextView.
	 * PRECONDITION: Assumes id represents a valid EditText view
	 * @param id the id of an EditText view to get string from
	 * @return string contained in EditText view with id
	 */
	public static String getViewText(Activity a, int id) {
		EditText editTextView = (EditText) a.findViewById(id);
		String fieldText = editTextView.getText().toString();
		return fieldText;
	}
	/**
	 * Adds a listener to a FIELD_SSN EditText to add necessary dashes
	 * @param t EditText box for FIELD_SSN
	 */
	public static void addOnTextChangedSSN(final EditText t) {
		t.addTextChangedListener(new TextWatcher() {
			boolean isShrinking = false;
			int last = 0;
			
			// Not used
			@Override
	        public void onTextChanged(CharSequence s, int st, int b, int c) {}
			// Not used
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {
				// If now is less than last, then shrinking
				if(s.length() < last) {
					isShrinking = true;
				}
				else {
					isShrinking = false;
				}
				// If typed fourth number and is growing
				if((s.length() == (Constants.SSN_FIRST_DASH_INDEX )) && (!isShrinking)) {
					// add dash
					s.append(Constants.SSN_DELIMETER);
				}
				// If typed fifth number (sixth char) and is growing
				else if((s.length() == (Constants.SSN_SECOND_DASH_INDEX )) && (!isShrinking)) {
					// add dash
					s.append(Constants.SSN_DELIMETER);
				}
				else if(s.length() > Constants.SSN_LENGTH) {
					// Prevent too many chars from being entered
					s.replace(11, 12, "");
				}
				
				last = s.length();
			}
		});
	}
}
