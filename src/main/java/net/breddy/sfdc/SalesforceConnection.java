package net.breddy.sfdc;

/**
 * A connection to the Salesforce Enterprise API.
 * 
 * @author Chris Bredesen
 */
public interface SalesforceConnection {

	/**
	 * Close this connection, freeing up any underlying resources.
	 */
	void close();

	/**
	 * Get the Session ID associated with this connection.
	 * 
	 * @return Session ID.
	 */
	String getSessionId();

}
