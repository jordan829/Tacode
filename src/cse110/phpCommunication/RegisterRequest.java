package cse110.phpCommunication;

import org.json.JSONObject;

import cse110.models.User;

/**
 * Handles the register request to the server
 * 
 * @author Vincent
 *
 */
public class RegisterRequest extends Request{

	/*
	 * Constructor of RegisterRequest
	 * @param u User from which info is being validated for registration
	 * @param aResp the object to call after parsing JSON
	 */
	public RegisterRequest(User u, ActivityResponse aResp){
		this.pairs = PairCreator.getAccountCreatePairs(u);
		this.aResp = aResp;
	}
	
	/*
	 * Not applicable in this instantiation
	 */
	protected Object makeResponseObject(JSONObject jOb) {
		return null;
	}

}
