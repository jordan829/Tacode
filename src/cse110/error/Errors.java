package cse110.error;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.widget.Toast;
import cse110.helpers.Constants;


/**
 * Used for reporting errors.
 * 
 * @author Vincent
 */
public class Errors {
	List<ErrorPair> errors;		// list of ErrorPairs this object holds
	private int index;			// index to track position in list
	

	/**
	 * Constructor method for Errors
	 */
	public Errors() {
		index = 0;
		errors = new ArrayList<ErrorPair>();
	}
	
	/**
	 * Get element of errors list at index
	 * @param index of array
	 * @return ErrorPair at index, or null if out of bounds
	 */
	public ErrorPair get(int ind){
		if((ind < 0) || ind >= errors.size()) {
			return null;
		}
		return errors.get(ind);
	}
	
	/**
	 * Gets next ErrorPair in the list
	 * @return next ErrorPair or null if at last element
	 */
	public ErrorPair getNext(){
		if(index >= errors.size()) {
			return null;
		}
		return errors.get(index++);
	}
	
	/**
	 * Adds an ErrorPair element to the ErrorPair list errors
	 * @param pair ErrorPair to be added
	 */
	public void add(ErrorPair pair){
		errors.add(pair);
	}
	
	/**
	 * Checks if the ErrorPair list errors is empty
	 * @return TRUE if errors is empty, FALSE otherwise
	 */
	public boolean hasErrors(){
		return !(errors.isEmpty());
	}
	
	/**
	 * Returns a new Errors object containing all the ErrorPairs in this Errors
	 * object that have a field that match the provided string
	 * @param s string to compare to fields
	 * @return Errors object containing all ErrorPairs with a field that matches string s
	 */
	public Errors fieldMatchesString(String s) {
		Errors subsetErrors = new Errors();
		ErrorPair temp;
		
		// Loop through the ArrayList
		for(int i = 0; i < errors.size(); i++){
			temp = errors.get(i);
			
			// If the current ErrorPair's field matches the passed in field, then
			// add it into the subset
			if(temp.getField().compareToIgnoreCase(s) == 0){	// Ignore the case just in case
				subsetErrors.add(temp);
			}
		}
		return subsetErrors;
	}
	
	/**
	 * Checks if any of the ErrorPairs has a field that matches the string
	 * @param s string to compare to fields
	 * @return true if any ErrorPairs have a field that matches string s
	 */
	public boolean hasFieldMatchesString(String s) {
		// Loop through the ArrayList
		for(int i = 0; i < errors.size(); i++){
			if(errors.get(i).getField().compareToIgnoreCase(s) == 0){ // Ignore the case just in case
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Appends an Errors object onto current Errors object.
	 * @param e Errors to be added
	 * 
	 * Precondition: None
	 * Postcondition: Current Errors object's error ArrayList will be 
	 * 				  combined with the ArrayList from the passed in param
	 */
	public void append(Errors e){
		for(int i = 0; i < e.errors.size(); i++){
			this.errors.add(e.get(i));
		}
	}
	
	/**
	 * Displays pop-up errors for every ErrorPair in the given activity
	 * @param a
	 * @param errors
	 */
    public void displayErrorFeedback(Activity a) {
    	String message;			// holds formatted message to display
		ErrorPair error;		
		
		//TODO: Consider a general displayErrorFeedback function that iterates
		// 		over the whole activity
		
		//--- Display all cse110.error messages as pop-up boxes
		while((error = this.getNext()) != null) {
			// only print if global debug level is high enough
			if(error.getDebugLevel() <= Constants.DEBUG_LEVEL) {
				// Format string
				message = error.getErrorMessage();
				// Print string in
				Toast.makeText(a.getApplicationContext(), 
	    				message, Toast.LENGTH_SHORT).show();
			}
		}
    }
}
