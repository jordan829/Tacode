package cse110.validation;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;
import cse110.error.Errors;
import cse110.helpers.Constants;
import cse110.helpers.ViewTransformations;

/**
 * Responsible for checking that user input is valid as
 * user types it into a text field
 * 
 * @author Casey
 */
public class TextChangedValidator {
	/**
	 * Adds a validator that updates every time a user modifies an EditText view
	 * and indicates if the field meets certain criteria
	 * @param t EditText object to listen to
	 */
	public static void onTextChangedValidator(final EditText t) {	
		Validator val;
		//--- Use input type to determine how to validate
		switch(t.getInputType()) {
			case (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD):
				// Implement the interface to pass function as parameter
				val = new Validator() {	public Errors validate(String s) {return Validate.validatePasswordField(s);}};
				// Create onTextChangedValidator
				addOnTextChangedValidator(t, val);
				break;
			case (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS):
				// Implement the interface to pass function as parameter
				val = new Validator() {	public Errors validate(String s) {return Validate.validateEmailField(s);}};
				// Create onTextChangedValidator
				addOnTextChangedValidator(t, val);
				break;
			case (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME):
				// Implement the interface to pass function as parameter
				if(t.getTag().toString().equalsIgnoreCase(Constants.VIEW_HINT_FIRST_NAME)) {
					val = new Validator() {	public Errors validate(String s) {return Validate.validateFirstNameField(s);}};
				}
				else if (t.getTag().toString().equalsIgnoreCase(Constants.VIEW_HINT_STATE)) {
					val = new Validator() {	public Errors validate(String s) {return Validate.validateStateField(s);}};
				}
				else if (t.getTag().toString().equalsIgnoreCase(Constants.VIEW_HINT_CITY)) {
					val = new Validator() { public Errors validate(String s) {return Validate.validateCityField(s);}};
				}
				else {
					val = new Validator() {	public Errors validate(String s) {return Validate.validateLastNameField(s);}};
				}
				// Create onTextChangedValidator
				addOnTextChangedValidator(t, val);
				break;
			case (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS):
				// Implement the interface to pass function as parameter
				val = new Validator() {	public Errors validate(String s) {return Validate.validateAddressField(s);}};
				// Create onTextChangedValidator
				addOnTextChangedValidator(t, val);
				break;
			case (InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL):
				// Implement the interface to pass function as parameter
				if(t.getTag().toString().equalsIgnoreCase(Constants.VIEW_HINT_ZIPCODE)){
					val = new Validator() { public Errors validate(String s) {return Validate.validateZipcodeField(s);}};
				}
				// Create onTextChangedValidator
				//addOnTextChangedValidator(t, val);
				break;
			case (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL):
				// Implement the interface to pass function as parameter
				//val = new Validator() {	public Errors validate(String s) {return Validate.validateTextField(s);}};
				// Create onTextChangedValidator
				//addOnTextChangedValidator(t, val);
				break;
			case (InputType.TYPE_CLASS_PHONE):
				// Implement the interface to pass function as parameter
				val = new Validator() {	public Errors validate(String s) {return Validate.validateSSNField(s);}};
				// Create onTextChangedValidator
				addOnTextChangedValidator(t, val);
				break;
			default:
				// If inputType doesn't match, then don't validate
				break;
		}
	}
	
	/**
	 * Helper function to instantiate the textChangedListner depending on the 
	 * specific Validator function passed in
	 * @param t
	 * @param val
	 */
	private static void addOnTextChangedValidator(final EditText t, final Validator val) {
		t.addTextChangedListener(new TextWatcher() {
			// First two are not used
			@Override
	        public void onTextChanged(CharSequence s, int st, int b, int c) {}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			// After text changes, recheck cse110.validation
			@Override
			public void afterTextChanged(Editable s) {
				if(s.length() == 0) {
					return;
				}
				else if(val.validate( s.toString() ).hasErrors()) {
					ViewTransformations.highlightTextViewBorder(t);
				}
				else {
					ViewTransformations.restoreEditTextBackground(t);
				}
			}
		});
	}
	
	/**
	 * Private interface used for function passing
	 */
	private interface Validator{
		public Errors validate(String s);
	}
}
