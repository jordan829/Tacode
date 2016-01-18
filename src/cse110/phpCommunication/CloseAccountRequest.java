package cse110.phpCommunication;

import org.json.JSONObject;

import cse110.models.User;

public class CloseAccountRequest extends Request {
	/**
	 * Public constructor.
	 * @param u Used to identify the user to be deactivated
	 * @param aResp the object to call after parsing JSON
	 */
	public CloseAccountRequest(User u, ActivityResponse aResp){
		this.pairs = PairCreator.getCloseMainAccountPairs(u);
		this.aResp = aResp;
	}
	
	/**
	 * Not applicable in this instantiation
	 */
	protected Object makeResponseObject(JSONObject jOb) {
		return null;
	}
	
}
