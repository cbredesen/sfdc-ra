package net.breddy.sfdc;

/**
 * Exception denoting recoverable problems with the Salesforce Connector.
 * 
 * @author Chris Bredesen
 */
public class SalesforceConnectorException extends Exception {

	public SalesforceConnectorException(String message, Throwable cause) {
		super(message, cause);
	}

	public SalesforceConnectorException(String message) {
		super(message);
	}

}
