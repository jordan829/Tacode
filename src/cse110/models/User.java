package cse110.models;

import java.util.List;

import cse110.error.Errors;
import cse110.validation.Validate;

/**
 * Stores information about a user.
 * @author Casey
 */
public class User {
	/* Global variables */
	private List<Account> accounts;
	private String address;
	private String city;
	private String email;
	private String first_name;
	private String last_name;
	private String state;
	private String password;
	private String phone;
	private String username;
	private String userid;
	private String ssn;
	private static User user;
	private String token;
	private String zipcode;	
	private boolean userActive;
	
	/**
	 * Private constructor because using Singleton pattern.
	 */
	private User () {
		
	}
	
	/**
	 * User creates itself 
	 * 
	 * @return user
	 */
	public static synchronized User getUser() {
		if(user == null) {
			user = new User();
		}
		
		return user;
	}

	/* Getter Functions */
	/**
	 * Allows access to the list of the user's accounts
	 * @return List of Account items containing the user's accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public String getPassword() {
		return password;
	}
	public String getPhone() {
		return phone;
	}
	public String getSsn() {
		return ssn;
	}
	public String getState() {
		return state;
	}
	public String getToken() {
		return token;
	}
	public String getUserid() {
		return userid;
	}
	public String getUsername() {
		return username;
	}
	public String getZipcode() {
		return zipcode;
	}
	/* End of Getters */
	public boolean isUserActive() {
		return userActive;
	}
	/* Setter Functions 
	 * They check if user input is valid according to the 
	 * requirements of each text field
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	} 
	
	public Errors setAddress(String address) {
		
		Errors err = Validate.validateAddressField(address);
		
		if(!(err.hasErrors()))
			this.address = address;
		return err;
	}
	public Errors setCity(String city) {
		
		Errors err = Validate.validateCityField(city);
		
		if(!(err.hasErrors()))
			this.city = city;
		return err;
	}
	public Errors setEmail(String email) {
		
		Errors err = Validate.validateEmailField(email);
		
		if(!(err.hasErrors()))
			this.email = email;
		return err;
	}
	public Errors setFirst_name(String first_name) {
		
		Errors err = Validate.validateFirstNameField(first_name);
		
		if(!(err.hasErrors()))
			this.first_name = first_name;
		return err;
	}
	public Errors setLast_name(String last_name) {
		
		Errors err = Validate.validateLastNameField(last_name);
		
		if(!(err.hasErrors()))
			this.last_name = last_name;
		return err;
	}
	public Errors setPassword(String password) {
		
		Errors err = Validate.validatePasswordField(password);
		
		if(!(err.hasErrors()))
			this.password = password;
		return err;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * Setter that parses string to integer
	 * @param ssn string to be converted to ssn
	 */
	public Errors setSsn(String ssn) {
		Errors err = Validate.validateSSNField(ssn);
		if(!(err.hasErrors()))
			this.ssn = ssn;
		
		return err;
	}
	public Errors setState(String state) {
		Errors err = Validate.validateStateField(state);
		
		if(!(err.hasErrors()))
			this.state = state;
		
		return err;
	}
	
	public Errors setToken(String token) {
		Errors err = new Errors();
		
		this.token = token;
		
		return err;
	}
	public void setUserActive(boolean userActive) {
		this.userActive = userActive;
	}

	public Errors setUserid(String userid) {
		this.userid = userid;
		return new Errors();
	}
	public Errors setUsername(String username) {
		Errors err = Validate.validateUsernameField(username);
		
		if(!(err.hasErrors()))
			this.username = username;
		
		return err;
	}
	
	public Errors setZipcode(String zipcode) {
		Errors err = Validate.validateZipcodeField(zipcode);
		
		if(!(err.hasErrors())) {
			this.zipcode = zipcode;
		}
		
		return err;
	}
	/* End of Setters */
}
