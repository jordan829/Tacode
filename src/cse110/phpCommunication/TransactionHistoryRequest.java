package cse110.phpCommunication;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cse100.logging.Tacode_Logger;
import cse110.helpers.Constants;
import cse110.models.Account;
import cse110.models.Transaction;
import cse110.models.User;

public class TransactionHistoryRequest extends Request {
	/**
	 * Public constructor.
	 * @param u user object with a username and password field filled
	 * @param aResp the object to call after done parsing JSON
	 */
	public TransactionHistoryRequest(Account a, User u, ActivityResponse aResp) {
		this.pairs = PairCreator.getTransactionHistoryPairs(u,a);
		this.aResp = aResp;
	}
	
	protected Object makeResponseObject(JSONObject jOb) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		// Get array out of jOb
		JSONArray transactionsArray = parseArrayFromJSON(jOb, Constants.RETURN_TRANSACTION);
		
		String amt, date, type;
		// Loop through array
		for(int i = 0; i < transactionsArray.length(); i++) {
			try {
				// Get Date, Amt, Type
				JSONObject jObElem = transactionsArray.getJSONObject(i);
				amt = parseStringFromJSON(jObElem, Constants.RETURN_TRANSACTION_AMMOUNT);
				date = parseStringFromJSON(jObElem, Constants.RETURN_TRANSACTION_DATE);
				type = parseStringFromJSON(jObElem, Constants.RETURN_TRANSACTION_TYPE);
				
				// Create a new transaction
				Transaction t = new Transaction(amt, date, type);
				
				// Add transaction to list
				transactions.add(t);
			} catch (JSONException e) {
				Tacode_Logger.logString(Constants.MESSAGE_JSON_PARSE +
						" object from array");
			}
		}
		return transactions;
	}
}
