package cse110.phpCommunication;

import org.json.JSONObject;

import cse110.models.User;

public class OpenAccountRequest extends Request {
	/**
	 * Public constructor.
	 * @param u used to identify the user
	 * @param type the type of account to be made (checking or savings)
	 * @param aResp the object to be called after parsing JSON
	 */
	public OpenAccountRequest(User u, String type, ActivityResponse aResp) {
		this.pairs = PairCreator.getCreateAccountPairs(u, type);
		this.aResp = aResp;
	}
	
	@Override
	protected Object makeResponseObject(JSONObject jOb) {
		return null;
	}

}
