package net.breddy.sfdc;

/**
 * Factory for {@link SalesforceConnection} instances (akin to a DataSource).
 * 
 * @author Chris Bredesen
 */
public interface SalesforceConnectionFactory {

	/**
	 * Get an open connection to the Salesforce API.
	 * 
	 * @return Open connection.
	 */
	SalesforceConnection getConnection() throws SalesforceConnectorException;

}