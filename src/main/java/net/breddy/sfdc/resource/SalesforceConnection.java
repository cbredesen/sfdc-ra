package net.breddy.sfdc.resource;

import javax.resource.ResourceException;
import javax.resource.cci.Connection;
import javax.resource.cci.ConnectionMetaData;
import javax.resource.cci.Interaction;
import javax.resource.cci.LocalTransaction;
import javax.resource.cci.ResultSetInfo;

/**
 * A Salesforce connection "handle".
 * 
 * @author Chris Bredesen
 */
public interface SalesforceConnection extends Connection {

	/**
	 * Close this "physical" connection.
	 */
	void close() throws ResourceException;

	Interaction createInteraction() throws ResourceException;

	LocalTransaction getLocalTransaction() throws ResourceException;

	ConnectionMetaData getMetaData() throws ResourceException;

	ResultSetInfo getResultSetInfo() throws ResourceException;
}