package cse110.phpCommunication;

import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cse100.logging.Tacode_Logger;
import cse110.error.ErrorPair;
import cse110.error.Errors;
import cse110.helpers.Constants;
import cse110.phpCommunication.PHPRequester.OnAsyncRequestComplete;

/**
 * Used for requesting information from the server.
 * 
 * @author Vincent
 */
public abstract class Request implements OnAsyncRequestComplete {
	ActivityResponse aResp;
	List<NameValuePair> pairs;
	
	/**
	 * Executes a PHP request after Request object has been created
	 */
	public void execute() {
		new PHPRequester(this, pairs).execute();
	}
	
	/**
	 * Parse a JSON object from a string.
	 * @param response server's response (formatted as a JSON)
	 * @return the parse JSONObject
	 */
	protected JSONObject strToJSONObject(String response) {
		JSONObject jOb;
		// Convert response to JSON Object
		try {
			jOb = new JSONObject(response);
			return jOb;
		} catch (JSONException e) {
			Tacode_Logger.logString(Constants.MESSAGE_JSON_PARSE);
			return null;
		}
	}
	
	/**
	 * Return the string value for a given name-value pair.
	 * @param jOb the JSONObject to get string form
	 * @param name the name of the name-value pair
	 * @return the value of the name-value pair
	 */
	protected String parseStringFromJSON(JSONObject jOb, String name) {
		// Get errors from JSON Obj
		try {
			return jOb.getString(name);
		} catch (JSONException e) {
			Tacode_Logger.logString(Constants.MESSAGE_JSON_PARSE + name);
			return "";
		}
	}
	
	/**
	 * Return the array of the given name
	 * @param jOb the JSONObject to get array form
	 * @param name the name of the array
	 * @return the JSONArray
	 */
	protected JSONArray parseArrayFromJSON(JSONObject jOb, String name) {
		// Get errors from JSON Obj
		try {
			return jOb.getJSONArray(name);
		} catch (JSONException e) {
			Tacode_Logger.logString(Constants.MESSAGE_JSON_PARSE + name);
			return null;
		}
	}
	
	protected abstract Object makeResponseObject(JSONObject jOb);
	
	@Override
	public void asyncResponse(String responseStr) {
		Errors errorsEncountered = new Errors();
		String errorString = "";
		Response response;
		JSONObject jOb;
		
		// Instantiate objects
		response = new Response();
		
		// Get JSONObject
		jOb = strToJSONObject(responseStr);
		
		// Check if null, then timed out
		if(jOb == null) {
			errorsEncountered.add(new ErrorPair(Constants.FIELD_JSON_OBJECT,
					Constants.MESSAGE_CONNECTION_TIMEOUT));
			response.setError(errorsEncountered);
			aResp.response(response);
			return;
		}
		
		// Get errors from JSON
		errorString = parseStringFromJSON(jOb, Constants.RETURN_ERRORS);
		
		// Parse errors
		errorsEncountered = PHPHelpers.parseErrors(errorString);
		
		// Check for errors
		if(!errorsEncountered.hasErrors()) {
			response.setResponse(makeResponseObject(jOb));
		}

		response.setError(errorsEncountered);
		
		// Call response function
		aResp.response(response);
	}
}