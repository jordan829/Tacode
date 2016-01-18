package cse110.error;

/**
 * Used for reporting errors on specific fields
 * 
 * @author Vincent
 */

public class ErrorPair {
	private String field;		// The field of the error
	private String errorMessage;// Description message of the error
	private int debug_level = 0;// indicates the level of this error (0 by def)
	
	/**
	 * Constructor method for ErrorPair
	 * @param f The field of the error
	 * @param e Description of the error
	 */
	public ErrorPair(String f, String e){
		this.field = f;
		this.errorMessage = e;
	}
	
	/**
	 *  Constructor method for ErrorPair
	 * @param f The field of the error
	 * @param e Description of the error
	 * @param debug_level indicates the level of this error
	 */
	public ErrorPair(String f, String e, int debug_level){
		this.field = f;
		this.errorMessage = e;
		this.debug_level = debug_level;
	}
	
	/**
	 * Allows access to view global variable field
	 * @return field
	 */
	public String getField(){
		return field;
	}
	
	/**
	 * Accesses the global variable errorMessage
	 * @return errorMessage
	 */
	public String getErrorMessage(){
		return errorMessage;
	}
	
	/**
	 * Accesses the global variable debug_level
	 * @return debug_level
	 */
	public int getDebugLevel() {
		return debug_level;
	}
	
	/**
	 * Allows field to be edited
	 * @param f String to be set to field
	 */
	public void setField(String f){
		field = f;
	}
	
	/**
	 * Allows errorMessage to be edited
	 * @param e String to be passed as an error message
	 */
	public void setErrorMessage(String e){
		errorMessage = e;
	}
}
