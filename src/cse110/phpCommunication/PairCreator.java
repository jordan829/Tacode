/**
 * 
 */
package cse110.phpCommunication;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import cse110.helpers.Constants;
import cse110.models.Account;
import cse110.models.Transfer;
import cse110.models.User;

/**
 * Used to make PHP requests related to a user
 * @author Casey
 */
public class PairCreator {
	// Account Create
	/**
	 * Returns a list of necessary NameValuePairs for account creation
	 * @param u User object to pull data from
	 * @return List of NameValuePairs
	 */
	public static List<NameValuePair> getLoginPairs(User u) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		// Create nameValuePairs
		nameValuePairs.add(new BasicNameValuePair(Constants.ACTION, Constants.ACTION_LOGIN));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_USER_USERNAME, u.getUsername()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_USER_PASSWORD, u.getPassword()));
		
		return nameValuePairs;
	}
	
	/**
	 * Returns a list of necessary NameValuePairs for login
	 * @param u User object to pull data from
	 * @return List of NameValuePairs
	 */
	public static List<NameValuePair> getAccountCreatePairs(User u) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		
		// Create nameValuePairs
		nameValuePairs.add(new BasicNameValuePair(Constants.ACTION, Constants.ACTION_CREATE_USER));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_USER_ADDRESS, u.getAddress()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_USER_CITY, u.getCity()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_USER_EMAIL, u.getEmail()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_USER_FIRST_NAME, u.getFirst_name()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_USER_LAST_NAME, u.getLast_name()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_USER_STATE, u.getState()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_USER_PASSWORD, u.getPassword()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_USER_USERNAME, u.getUsername()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_USER_SSN, u.getSsn()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_USER_ZIPCODE, u.getZipcode()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_PERMISSION, Constants.USER));
		
		return nameValuePairs;
	}
	
	/**
	 * Returns a list of necessary NameValuePairs for user's banking accounts
	 * @param u User object to pull data from
	 * @return List of NameValuePairs
	 */
	public static List<NameValuePair> getAccountPairs(User u) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair(Constants.ACTION, Constants.ACTION_GET_ACCOUNTS));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_TRANSACTION_USER_ID, u.getUserid()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_TRANSACTION_TOKEN, u.getToken()));
		return nameValuePairs;
	}
	
	/**
	 * Returns a list of necessary NameValuePairs for any type of transfer
	 * @param u User object to pull data from
	 * @return List of NameValuePairs
	 */
	public static List<NameValuePair> getTransferPairs(Transfer t, boolean isPersonal){
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		if(isPersonal) {
			nameValuePairs.add(new BasicNameValuePair(Constants.ACTION, Constants.ACTION_TRANSFER));
		} else {
			nameValuePairs.add(new BasicNameValuePair(Constants.ACTION, Constants.ACTION_TRANSFER_INTERNAL));
		}		
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_TRANSACTION_DEST_ID, t.getDest()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_TRANSACTION_SOURCE_ID, t.getSrc()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_TRANSACTION_USER_ID, t.getUserid()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_TRANSACTION_TOKEN, t.getToken()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_TRANSACTION_AMOUNT, t.getAmount()));
		return nameValuePairs;
	}
	
	/**
	 * Returns a list of necessary NameValuePairs for closing the main bank account
	 * @param u User object to pull data from
	 * @return List of NameValuePairs
	 */
	public static List<NameValuePair> getCloseMainAccountPairs(User u){
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair(Constants.ACTION, Constants.ACTION_CLOSE_ACCOUNT));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_TRANSACTION_USER_ID, u.getUserid()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_TRANSACTION_TOKEN, u.getToken()));
		return nameValuePairs;
	}
	/**
	 * Returns a list of necessary NameValuePairs for the transaction history
	 * for a specific bank account
	 * @param u User object to pull data from
	 * @return List of NameValuePairs
	 */
	public static List<NameValuePair> getTransactionHistoryPairs(User u, Account a) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair(Constants.ACTION, Constants.ACTION_GET_HISTORY));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_TRANSACTION_TOKEN, u.getToken()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_TRANSACTION_USER_ID, u.getUserid()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_ACCOUNT_ID, a.getID()));
		return nameValuePairs;
	}
	
	/**
	 * Returns a list of necessary NameValuePairs for an account creation
	 * @param u User object to pull data from
	 * @param accountType The type of account the user wishes to create (Savings/Checkings)
	 * @return List of NameValuePairs
	 */
	public static List<NameValuePair> getCreateAccountPairs(User u, String accountType) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair(Constants.ACTION, Constants.ACTION_CREATE_ACCOUNT));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_TRANSACTION_USER_ID, u.getUserid()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_TRANSACTION_TOKEN, u.getToken()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_OPEN_ACCOUNT_TYPE, accountType));
		return nameValuePairs;
	}
	
	/**
	 * Returns a list of necessary NameValuePairs for closing a single bank account
	 * @param u User object to pull data from
	 * @param a ID of the account to be closed
	 * @return List of NameValuePairs
	 */
	public static List<NameValuePair> getCloseOneAccountPairs(User u, String a){
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair(Constants.ACTION, Constants.ACTION_CLOSE_ONE_ACCOUNT));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_TRANSACTION_USER_ID, u.getUserid()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_TRANSACTION_TOKEN, u.getToken()));
		nameValuePairs.add(new BasicNameValuePair(Constants.VALUE_ACCOUNT_ID, a));
		return nameValuePairs;
	}
}
