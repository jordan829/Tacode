
package cse110.models;

/**
 * Stores information about a user account
 * 
 * @author Casey
 *
 */
public class Account {
	private String type;		// specifies whether the account is checkings or savings
	private String balance;		// the current balance in the account
	private String id;				// used to identify the account *should equal to user.accounts.size()*
	
	/**
	 * Constructor for Account
	 * @param accountType Specifies whether the account is checkings or savings
	 * @param accountBal The initial balance of the account
	 * @param accountID The account ID
	 */
	public Account(String accountType, String accountBal, String accountID){
		type = accountType;
		balance = accountBal;
		id = accountID;
	}
	
	/**
	 * Constructor for Account without a balance
	 * @param accountType Specifies whether the account is checkings or savings
	 * @param accountID The account ID
	 */
	public Account(String accountType, String accountID){
		type = accountType;
		id = accountID;
	}
	
	// Getters
	/**
	 * Allows access to account type
	 * @return string indicating account type
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * Allows access to the account balance
	 * @return string indicating account balance
	 */
	public String getBalance(){
		return balance;
	}
	
	/**
	 * Allows access to the account id
	 * @return string containing account id
	 */
	public String getID(){
		return id;
	}
}
