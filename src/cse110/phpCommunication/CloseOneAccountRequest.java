package cse110.phpCommunication;

import org.json.JSONObject;


import cse110.models.User;

public class CloseOneAccountRequest extends Request{
	
	/**
	 * Public constructor.
	 * @param u Used to identify the userid
	 * @param a Used to identify the banking account to be closed
	 * @param aResp the object to call after parsing JSON
	 */
	public CloseOneAccountRequest(User u, String a,  ActivityResponse aResp){
		this.pairs = PairCreator.getCloseOneAccountPairs(u, a);
		this.aResp = aResp;
	}
	
	/**
	 * Not applicable in this instantiation
	 */
	protected Object makeResponseObject(JSONObject jOb) {
		return null;
	}

}
