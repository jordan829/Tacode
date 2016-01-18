package cse110.phpCommunication;

import org.json.JSONObject;

import cse110.helpers.Constants;
import cse110.models.Transfer;

public class TransferRequest extends Request{
	/**
	 * Public constructor
	 * @param t used to obtain the transfer information
	 * @param isPersonal flag for personal or internal transfer
	 * @param aResp the object to be called after parsing JSON
	 */
	public TransferRequest(Transfer t, boolean isPersonal, ActivityResponse aResp){
		this.pairs = PairCreator.getTransferPairs(t, isPersonal);
		this.aResp = aResp;
	}
	
	protected Object makeResponseObject(JSONObject jOb) {
		String successString = parseStringFromJSON(jOb, Constants.RETURN_SUCCESS);
		return successString;
	}

}
