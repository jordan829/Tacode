/**
 * 
 */
package cse110.phpCommunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;
import cse100.logging.Tacode_Logger;
import cse110.error.ErrorPair;
import cse110.helpers.Constants;

/**
 * Used to communicate with PHP on server (to access MySQL)
 * @author Casey
 */
public class PHPRequester extends AsyncTask<Void, Integer, String>{
	private OnAsyncRequestComplete caller;
	private List<NameValuePair> nameValuePairs = null;
	
	/**
	 * Public constructor.
	 * @param caller the caller making the request to the server
	 * @param pairs the name-value pairs for the request
	 */
	public PHPRequester(OnAsyncRequestComplete caller, List<NameValuePair> pairs) {
		this.caller = caller;
		this.nameValuePairs = pairs;
	}
	
	// Interface for caller to implement
	public interface OnAsyncRequestComplete {
		public void asyncResponse(String response);
	}
	
	/**
	 * Performs the request in a background thread. Only expects one RequestBundle.
	 */
	public String doInBackground(Void...params) {
		return request();
	}
	
	public void onPreExecute() {
		/*pDialog = new ProgressDialog(context);
		pDialog.setMessage(Constants.MESSAGE_LOGIN);
		pDialog.show();*/
	}
	
	public void onProgressUpdate(Integer...progress) {
		// implement a progress bar if desired
	}
	
	public void onPostExecute(String response) {/**
		 * Returns a list of necessary NameValuePairs for user's banking accounts
		 * @param u User object to pull data from
		 * @return List of NameValuePairs
		 */
		Tacode_Logger.logString(response);
		caller.asyncResponse(response);
	}
	
	// General request
	protected String request() {
		try {
			// Create the http request
			HttpParams httpParameters = new BasicHttpParams();

			//Setup timeouts
			HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
			HttpConnectionParams.setSoTimeout(httpParameters, 15000);			
			
			// Create client
			HttpClient client = new DefaultHttpClient(httpParameters);
			
			// Create POST request
			HttpPost httppost = new HttpPost(Constants.PHP_CONTROLLER_URL);
			
			// Encode nameValuePairs into POST request
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			// Loop through nameValuePairs and output
			for(int i = 0; i < nameValuePairs.size(); i++) {
				Tacode_Logger.logError(new ErrorPair(nameValuePairs.get(i).getName(), 
						nameValuePairs.get(i).getValue(), 2));
			}
			// Execute POST
			HttpResponse response = client.execute(httppost);
			
			return responseToString(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return "" + Constants.RESPONSE_PROTOCOL_EXCEPTION;
		} catch (IOException e) {
			e.printStackTrace();
			return "" + Constants.RESPONSE_IO_EXCEPTION;
		}
	}
	
	// Convert response to string
	private String responseToString(HttpResponse response) {
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		
			StringBuffer strBuf = new StringBuffer("");
			String nextLine = "";
			while((nextLine = in.readLine()) != null) {
				strBuf.append(nextLine);
			}
			in.close();
			
			return strBuf.toString();			
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return "" + Constants.PARSE_ILLEGAL_STATE_EXCEPTION;
		} catch (IOException e) {
			e.printStackTrace();
			return "" + Constants.PARSE_PROTOCOL_EXCEPTION;
		}
	}
}