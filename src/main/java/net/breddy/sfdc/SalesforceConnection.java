package net.breddy.sfdc;

import net.breddy.sfdc.soap.QueryResult;

/**
 * A connection to the Salesforce Enterprise API. Implementations are generally
 * short-lived handles to underlying managed connections which are pooled and
 * reused.
 * 
 * @author Chris Bredesen
 */
public interface SalesforceConnection {
	
	/**
	 * Unwrap a custom client Soap binding.
	 * 
	 * @param <T> Soap client binding type.
	 * @param clientType
	 * 
	 * @return A custom Soap client binding based on this connection's security context.
	 */
	<T> T unwrap(Class<T> clientType) throws SalesforceConnectorException;

	/**
	 * Close this connection, freeing up any underlying resources.
	 */
	void close();

	/**
	 * Execute a query.
	 * 
	 * @param query SOQL.
	 * 
	 * @return Query result.
	 */
	QueryResult query(String query) throws SalesforceConnectorException;
}
