package net.breddy.sfdc.resource.impl;

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
public class SalesforceConnectionFactoryImpl implements SalesforceConnectionFactory {
	private ConnectionManager cm;
	private ManagedConnectionFactory mcf;

	SalesforceConnectionFactoryImpl(ConnectionManager connectionManager, 
			ManagedConnectionFactory managedConnectionFactory) {
		this.cm = connectionManager;
		this.mcf = managedConnectionFactory;
	}

	public SalesforceConnection getConnection() throws SalesforceConnectorException {
		try {
			return (SalesforceConnection) cm.allocateConnection(mcf, null);
		} catch (ResourceException e) {
			throw new SalesforceConnectorException("Could not allocate connection", e);
		}
	}

}
