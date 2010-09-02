package net.breddy.sfdc.resource.impl;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.resource.ResourceException;
import javax.resource.cci.Connection;
import javax.resource.cci.ConnectionFactory;
import javax.resource.cci.ConnectionSpec;
import javax.resource.cci.RecordFactory;
import javax.resource.cci.ResourceAdapterMetaData;
import javax.resource.spi.ConnectionManager;

import net.breddy.sfdc.resource.SalesforceConnectionFactory;

public class SalesforceConnectionFactoryImpl implements ConnectionFactory, SalesforceConnectionFactory {
	private ConnectionManager cm;

	public SalesforceConnectionFactoryImpl(ConnectionManager connectionManager) {
		this.cm = connectionManager;
	}

	public Connection getConnection() throws ResourceException {
		return new SalesforceConnectionImpl();
	}

	/**
	 * Not implemented yet (or possibly ever).
	 * 
	 * @throws UnsupportedOperationException always.
	 */
	public Connection getConnection(ConnectionSpec properties) throws ResourceException {
		throw new UnsupportedOperationException("We don't need no stinking ConnectionSpec");
	}

	public ResourceAdapterMetaData getMetaData() throws ResourceException {
		// TODO Auto-generated method stub
		return null;
	}

	public RecordFactory getRecordFactory() throws ResourceException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setReference(Reference reference) {
		throw new UnsupportedOperationException("blammo");
	}

	public Reference getReference() throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

}
