package cse110.phpCommunication;

/**
 * Interface that is implemented when a request is being made.
 * Each request has a different response.
 */
public interface ActivityResponse {
	void response(Response r);
}
