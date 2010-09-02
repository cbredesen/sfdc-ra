package net.breddy.sfdc.resource;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.resource.ResourceException;
import javax.resource.cci.Connection;
import javax.resource.cci.ConnectionSpec;
import javax.resource.cci.RecordFactory;
import javax.resource.cci.ResourceAdapterMetaData;

/**
 * Creates {@link SalesforceConnection} instances.
 * 
 * @author Chris Bredesen
 */
public interface SalesforceConnectionFactory {

	Connection getConnection() throws ResourceException;

	Connection getConnection(ConnectionSpec properties) throws ResourceException;

	ResourceAdapterMetaData getMetaData() throws ResourceException;

	RecordFactory getRecordFactory() throws ResourceException;

	void setReference(Reference reference);

	Reference getReference() throws NamingException;

}