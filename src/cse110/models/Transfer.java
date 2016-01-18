package cse110.models;

/**
 * Stores information about an account money transfer
 *
 * @author Casey
 *
 */
public class Transfer {
	/* Global variables */
	private String amount;
	private String dest;
	private String src;
	private String userid;
	private String token;
	
	/**
	 * Constructor for Transfer object
	 * @param a monetary amount of transfer
	 * @param d account that will receive the transfer amount
	 * @param s account from which transfer will orginate 
	 * @param u user id
	 * @param t token to server
	 */
	public Transfer(String a, String d, String s, String u, String t){
		amount = a;
		dest = d;
		src = s;
		userid = u;
		token = t;
	}

	
	// Getters for fields of Transfer object
	/**
	 * Allows access to the transfer amount
	 * @return string indicating transfer amount
	 */
	public String getAmount(){
		return amount;
	}
	
	/**
	 * Allows access to the destination account
	 * @return string indicating destination
	 */
	public String getDest(){
		return dest;
	}
	
	/**
	 * Allows access to the source account
	 * @return string indicating source
	 */
	public String getSrc(){
		return src;
	}
	
	/**
	 * Allows access to the user id
	 * @return string indicating user id
	 */
	public String getUserid(){
		return userid;
	}
	
	/**
	 * Allows access to the token
	 * @return string indicating token
	 */
	public String getToken(){
		return token;
	}
}
