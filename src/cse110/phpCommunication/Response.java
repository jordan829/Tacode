package cse110.phpCommunication;

import cse110.error.Errors;

/**
 * Used for storing the response from the server.
 * 
 * @author Vincent
 */
public class Response {
	private Errors error;	// The errors returned from the response
	private Object obj;		// The response
	
	// Constructor for Response
	public Response(){}
	
	/**
	 * Allows access to error field
	 * @return error field from this class
	 */
	public Errors getError(){
		return error;
	}
	
	/**
	 * Allows access to obj field
	 * @return obj field from this class
	 */
	public Object getResponse(){
		return obj;
	}
	
	/**
	 * Allows error field to be modified
	 * @param e Error object 
	 */
	public void setError(Errors e){
		error = e;
	}
	
	 /**
	 * Allows obj field to be modified
	 * @param obj 
	 */
	public void setResponse(Object obj){
		this.obj = obj;
	}
}

