package cse110.models;

/**
 * Stores information about an account's transaction
 * @author Ashmp
 *
 */
public class Transaction {
	private String type, amt, date;
	
	// Constructor
	public Transaction(String amt, String date, String type) {
		this.amt = amt;
		this.date = date;
		this.type = type;
	}

	/**
	 * Allows access to the amount of the transaction
	 * @return string indicating amount
	 */
	public String getAmt() {
		return amt;
	}
	
	/**
	 * Allows access to the date in which transaction occurred
	 * @return date of transaction
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Allows access to the type of account in which transaction
	 * occurred
	 * 
	 * @return string indicating type of account
	 */
	public String getType() {
		return type;
	}
}
