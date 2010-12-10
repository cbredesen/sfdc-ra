package net.breddy.sfdc.impl;

import java.io.Serializable;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.resource.Referenceable;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ManagedConnectionFactory;

import net.breddy.sfdc.SalesforceConnection;
import net.breddy.sfdc.SalesforceConnectionFactory;
import net.breddy.sfdc.SalesforceConnectorException;

/**
 * Allocates connections using the provided {@link ConnectionManager} and {@link ManagedConnectionFactory}.
 * 
 * @author Chris Bredesen
 */
public class SalesforceConnectionFactoryImpl implements SalesforceConnectionFactory, Serializable, Referenceable {
	private ConnectionManager cm;
	private ManagedConnectionFactory mcf;
	private Reference reference;

	SalesforceConnectionFactoryImpl(ConnectionManager connectionManager, 
			ManagedConnectionFactory managedConnectionFactory) {
		this.cm = connectionManager;
		this.mcf = managedConnectionFactory;
	}

	/**
	 * {@inheritDoc}
	 */
	public SalesforceConnection getConnection() throws SalesforceConnectorException {
		try {
			return (SalesforceConnection) cm.allocateConnection(mcf, null);
		} catch (ResourceException e) {
			throw new SalesforceConnectorException("Could not allocate connection", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReference(Reference reference) {
		this.reference = reference;
	}

	/**
	 * {@inheritDoc}
	 */
	public Reference getReference() throws NamingException {
		return reference;
	}

}
