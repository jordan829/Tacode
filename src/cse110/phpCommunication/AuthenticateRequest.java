package cse110.phpCommunication;

import org.json.JSONObject;

import cse110.helpers.Constants;
import cse110.models.User;

public class AuthenticateRequest extends Request {
	
	/**
	 * Public constructor.
	 * @param u user object with a username and password field filled
	 * @param aResp the object to call after done parsing JSON
	 */
	public AuthenticateRequest(User u, ActivityResponse aResp) {
		this.pairs = PairCreator.getLoginPairs(u);
		this.aResp = aResp;
	}
	
	/**
	 * Gets the token and userid from the response from the server to set
	 * the User object's token and userid field.
	 * @param jOb the JSON object returned from the server
	 * @return user the User object with the token and userid fields newly set
	 */
	protected Object makeResponseObject(JSONObject jOb) {
		String tokenString;
		String useridString;
		User user;
		// Parses the token and userid from JSON object
		tokenString = parseStringFromJSON(jOb, Constants.RETURN_TOKEN);
		useridString = parseStringFromJSON(jOb, Constants.RETURN_USER_ID);
		// Fill user object
		user = User.getUser();
		user.setToken(tokenString);
		user.setUserid(useridString);
		return user;
	}
}
