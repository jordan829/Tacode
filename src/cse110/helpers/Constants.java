package cse110.helpers;

import cse110.tacode.R;

/**
 * This class is used to define any constants used
 * 
 * @author Casey
 */
public final class Constants {
	//--- Global
	// Indicates what level of debug messages should be printed
	// 0 -> none, 1 -> some 2 -> all
	public static final int DEBUG_LEVEL = 2;
	public static final String LOG_HEADER = "Tacode.out";
	
	// Used for the "field" String in an ErrorPair
	public static final String FIELD_FIRST_NAME = "First name";
	public static final String FIELD_LAST_NAME = "Last name";
	public static final String FIELD_STREET_ADDRESS = "Street address";
	public static final String FIELD_CITY = "City";
	public static final String FIELD_STATE = "State";
	public static final String FIELD_ZIPCODE = "Zipcode";
	public static final String FIELD_SSN = "Social Security Number";
	public static final String FIELD_EMAIL = "Email address";
	public static final String FIELD_USERNAME = "Username";
	public static final String FIELD_PASSWORD = "Password";
	public static final String FIELD_JSON_OBJECT = "JSON Object";
	public static final String FIELD_TOKEN = "Token";
	public static final String FIELD_ERRORS = "Errors";
	public static final String FIELD_SERVER_RESPONSE = "Server response";
	public static final String FIELD_TRANSACTION = "Transfer";
	
	// Used for the "errorMessage" String in an ErrorPair
	public static final String MESSAGE_INVALID_SSN = "Invalid SSN.";
	public static final String MESSAGE_INVALID_USERNAME = "Invalid username.";
	public static final String MESSAGE_INVALID_PASSWORD = "Password must be at least" +
			" 8 characters long";
	public static final String MESSAGE_INVALID_ZIPCODE = "Invalid zipcode.";
	public static final String MESSAGE_INVALID_STATE = "Invalid state.";
	public static final String MESSAGE_INVALID_FIRST_NAME = "Invalid first name.";
	public static final String MESSAGE_INVALID_LAST_NAME = "Invalid last name.";
	public static final String MESSAGE_INVALID_CITY = "Invalid city.";
	public static final String MESSAGE_INVALID_STREET_ADDRESS = "Invalid address.";
	public static final String MESSAGE_INVALID_EMAIL = "Invalid email.";
	public static final String MESSAGE_JSON_CREATE = "Could not create JSON Object from server response.";
	public static final String MESSAGE_JSON_PARSE = "Could not parse field from JSON Object.";
	public static final String MESSAGE_INVALID_TOKEN = "Invalid token.";
	public static final String FIELD_ERROR_PAIRS_NO_MATCH = "Not an equal num of fields and errors.";
	public static final String MESSAGE_INVALID_TRANSACTION = "Invalid transaction.";
	public static final String MESSAGE_CONNECTION_TIMEOUT = "Server connection timed-out.";
	
	
	// Used for checking min/max lengths
	public static final int MIN_USERNAME_LENGTH = 5;
	public static final int MIN_PASSWORD_LENGTH = 8;
	
	//--- MainActivity.java
	public static final String INVALID_LOGIN_MESSAGE = "Incorrect username or" +
			" password";
	public final static String INTENT_USERNAME = "cse110.helpers.USERNAME";
	public final static String INTENT_USER_ID = "cse110.helpers.USERID";
	public final static String INTENT_TOKEN = "cse110.helpers.TOKEN";
	public final static String INTENT_ACCOUNT_ID = "cse110.helpers.ACCOUNT_ID";
	public final static String INTENT_BALANCE = "cse110.helpers.BALANCE";
	public final static String INTENT_ACCOUNT_TYPES = "cse110.helpers.ACCOUNT_TYPES";
	public final static String INTENT_ACCOUNT_IDS = "cse110.helpers.ACCOUNT_IDS";
	public final static String INTENT_ACCOUNT_BALANCES = "cse110.helpers.ACCOUNT_BALANCES";
	public final static String INTENT_ACCOUNT_TYPE = "cse110.helpers.ACCOUNT_TYPE";
	public final static String INTENT_ACCOUNT_INDEX = "cse110.helpers.INTENT_ACCOUNT_INDEX";
	
	
	
	//--- UserAccountCreate.java
	// Array of each EditText's id
	public static final int[] USER_ACCOUNT_CREATE_IDS = {
		R.id.user_create_account_text_firstName_field,
		R.id.user_create_account_text_lastName_field,
		R.id.user_create_account_text_streetAddress_field,
		R.id.user_create_account_text_city_field,
		R.id.user_create_account_text_state_field,
		R.id.user_create_account_text_zipCode_field,
		R.id.user_create_account_text_socialSecurityNumber_field,
		R.id.user_create_account_text_email_field,
		R.id.user_create_account_text_createUsername_field,
		R.id.user_create_account_text_createPassword_field
	};
	// Array of each EditText's name
	public static final String[] USER_ACCOUNT_CREATE_NAMES = {
		FIELD_FIRST_NAME,
		FIELD_LAST_NAME,
		FIELD_STREET_ADDRESS,
		FIELD_CITY,
		FIELD_STATE,
		FIELD_ZIPCODE,
		FIELD_SSN,		FIELD_EMAIL,
		FIELD_USERNAME,
		FIELD_PASSWORD
	};
	
	//--- PHPRequester
	// URL
	public static final String PHP_CONTROLLER_URL = "http://star-destroyer.asuscomm.com/service_layer/controller.php/";
	public static final String MESSAGE_LOGIN = "Loading...";
	// Errors
	public static final int RESPONSE_PROTOCOL_EXCEPTION = 1;
	public static final int RESPONSE_IO_EXCEPTION = 2;
	public static final int PARSE_ILLEGAL_STATE_EXCEPTION = 3; 
	public static final int PARSE_PROTOCOL_EXCEPTION = 4;
	// Actions
	public static final String ACTION = "action";
	//public static final String ACTION_AUTHENTICATE = "authenticate
	public static final String ACTION_LOGIN = "login";
	public static final String ACTION_CREATE_USER = "create_user";
	public static final String ACTION_DEBIT = "debit";
	public static final String ACTION_CREDIT = "credit";
	public static final String ACTION_GET_ACCOUNTS = "get_list_of_accounts";
	public static final String ACTION_TRANSFER = "transfer";
	public static final String ACTION_CLOSE_ACCOUNT = "deactivate_user";
	public static final String ACTION_CLOSE_ONE_ACCOUNT = "deactivate_account";
	public static final String ACTION_GET_HISTORY = "get_history";
	public static final String ACTION_CREATE_ACCOUNT = "create_account";
	public static final String ACTION_TRANSFER_INTERNAL = "internal_transfer";
	// Values
	public static final String VALUE = "value";
	
	// Values (account)
	public static final String VALUE_ACCOUNT_BALANCE = "balance";
	public static final String VALUE_ACCOUNT_ID = "account_id";
	public static final String VALUE_ACCOUNT_INTEREST_RATE = "interest_rate";
	public static final String VALUE_ACCOUNT_TRANSACTIONS = "transactions";
	
	// Values (user)
	public static final String VALUE_USER_ACCOUNTS = "accounts";
	public static final String VALUE_USER_ADDRESS = "address";
	public static final String VALUE_USER_CITY = "city";
	public static final String VALUE_USER_EMAIL = "email";
	public static final String VALUE_USER_FIRST_NAME = "first_name";
	public static final String VALUE_USER_LAST_NAME = "last_name";
	public static final String VALUE_USER_STATE = "state";
	public static final String VALUE_USER_PASSWORD = "password";
	public static final String VALUE_USER_USERNAME = "username";
	public static final String VALUE_USER_SSN = "ssn";
	public static final String VALUE_USER_ZIPCODE = "zipcode";
	public static final String VALUE_USER_USERACTIVE = "user_active";
	public static final String VALUE_TRANSACTION_IS_PERSONAL = "personal";
	public static final String VALUE_USER_ID = "id";
	public static final String VALUE_PERMISSION = "permission";
	public static final String USER = "user";
	
	//Values (transaction)
	public static final String VALUE_TRANSACTION_SOURCE_ID = "account_id_from";
	public static final String VALUE_TRANSACTION_DEST_ID = "account_id_to";
	public static final String VALUE_TRANSACTION_AMOUNT = "amount";
	public static final String VALUE_TRANSACTION_TYPE = "account_type";
	public static final String VALUE_TRANSACTION_USER_ID = "user_id";
	public static final String VALUE_TRANSACTION_TOKEN = "token";
	
	// Return (vals)
	public static final String RETURN_ERRORS = "errors";
	public static final String RETURN_VALUE = "value";
	public static final String RETURN_TOKEN = "token";
	public static final String RETURN_BALANCE = "balance";
	public static final String RETURN_ACCOUNT_ID = "account_id";
	public static final String RETURN_USER_ID = "user_id";
	public static final String RETURN_ACCOUNTS_ARRAY = "accounts";
	public static final String RETURN_ACCOUNT_TYPE = "type";	
	public static final String RETURN_SUCCESS = "success";
	
	public static final String RETURN_TRANSACTION = "history";
	public static final String RETURN_TRANSACTION_TYPE = "type";
	public static final String RETURN_TRANSACTION_AMMOUNT = "amt";
	public static final String RETURN_TRANSACTION_DATE = "date";

	//--- ViewHelpers
	public static final char SSN_DELIMETER = '-';
	public static final int SSN_FIRST_DASH_INDEX = 3;
	public static final int SSN_SECOND_DASH_INDEX = 6;
	public static final int SSN_LENGTH = 11;
	
	//--- TextChangedValidator
	public static final String VIEW_HINT_FIRST_NAME = "First Name";
	public static final String VIEW_HINT_STATE = "State";

	public static final String VIEW_HINT_CITY = "City";
	public static final String VIEW_HINT_ZIPCODE = "Zipcode";
	
	// Transfer type IDs
	public static final String TYPE_DEBIT = "debit";
	public static final String TYPE_CREDIT = "credit";
	public static final String TYPE_TRANSFER = "transfer";
	
	// Account types
	public static final String ACCOUNT_TYPE_CHECKINGS = "Checkings";
	public static final String ACCOUNT_TYPE_SAVINGS = "Savings";
	
	//
	public static final String VALUE_OPEN_ACCOUNT_CHECKINGS = "checking";
	public static final String VALUE_OPEN_ACCOUNT_SAVINGS = "savings";
	public static final String VALUE_OPEN_ACCOUNT_TYPE = "account_type";
	
	public static final int HOME_TRANSFER_OFFSET = 1;
	public static final int HOME_CLOSE_OFFSET = 2;
	public static final int HOME_LOGOUT_OFFSET = 3;
	
	// Deleting bank accounts
	public static final String DELETED_LAST_ACCOUNT = "none";
}
