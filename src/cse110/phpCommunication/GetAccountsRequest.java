package cse110.phpCommunication;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cse100.logging.Tacode_Logger;
import cse110.helpers.Constants;
import cse110.models.Account;
import cse110.models.User;

public class GetAccountsRequest extends Request {
	
	/**
	 * Public constructor
	 * @param u used to identify the user
	 * @param aResp object to be called after parsing JSON
	 */
	public GetAccountsRequest(User u, ActivityResponse aResp){
		this.pairs = PairCreator.getAccountPairs(u);
		this.aResp = aResp;
	}
	
	/**
	 * Creates the list of banking accounts of the user by translating
	 * response from server.
	 * @param jOb
	 * @return accounts
	 */
	protected Object makeResponseObject(JSONObject jOb) {
		String accountIdString;
		String accountBalanceString;
		String accountTypeString;
		List<Account> accounts = new ArrayList<Account>();
		JSONArray jArray = parseArrayFromJSON(jOb, Constants.RETURN_ACCOUNTS_ARRAY);
		
		// get accounts from array
		for(int i = 0; i < jArray.length(); i++) {
			try {
				JSONObject jObElem = jArray.getJSONObject(i);
				accountIdString = parseStringFromJSON(jObElem, Constants.RETURN_ACCOUNT_ID);
				accountBalanceString = parseStringFromJSON(jObElem, Constants.RETURN_BALANCE);
				accountTypeString = parseStringFromJSON(jObElem, Constants.RETURN_ACCOUNT_TYPE);
				accounts.add(new Account(accountTypeString, 
						accountBalanceString, accountIdString));
			} catch (JSONException e) {
				Tacode_Logger.logString(Constants.MESSAGE_JSON_PARSE +
						" object from array");
			}
		}
		
		return accounts;
	}

}
