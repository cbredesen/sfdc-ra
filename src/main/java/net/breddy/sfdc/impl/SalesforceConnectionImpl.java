package net.breddy.sfdc.impl;

import javax.resource.spi.ManagedConnection;

import net.breddy.sfdc.SalesforceConnection;
import net.breddy.sfdc.SalesforceConnectorException;
import net.breddy.sfdc.soap.QueryResult;

import org.jboss.logging.Logger;

/**
 * A connection handle. Encapsulates a {@link ManagedConnection} and a
 * SOAP client stub.
 * 
 * @author Chris Bredesen
 */
public class SalesforceConnectionImpl implements SalesforceConnection {
	private Logger log = Logger.getLogger(SalesforceConnectionImpl.class);
	private SalesforceManagedConnection mc;

	/**
	 * Create a new connection backed by <code>managedConnection</code>.
	 * 
	 * @param managedConnection Underlying managed connection.
	 */
	SalesforceConnectionImpl(SalesforceManagedConnection managedConnection) {
		this.mc = managedConnection;
	}

	/**
	 * Get a SOAP binding for a specific SFDC implementation. I have not
	 * thought this through yet but we may be able to find the ObjectFactory
	 * used and provide instances that way. This is our gateway to the
	 * business-custom SOAP code.
	 */
	@Override
	public <T> T unwrap(Class<T> clientType) throws SalesforceConnectorException {
		try {
			return clientType.newInstance();
		} catch (InstantiationException e) {
			throw new SalesforceConnectorException("Unable to create instance of type " + clientType.getName(), e);
		} catch (IllegalAccessException e) {
			throw new SalesforceConnectorException("Unable to create instance of type " + clientType.getName(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void close() {
		assertUsable();
		log.trace("Closing connection " + this);
		mc.closeHandle(this);
		this.mc = null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public QueryResult query(String query) throws SalesforceConnectorException {
		assertUsable();
		try {
			return mc.getSoapClient().query(query);
		} catch (Throwable t) {
			throw new SalesforceConnectorException("Could not execute query", t);
		}
	}

	/**
	 * Fail with ISE if this connection handle is in an unusable state. This
	 * protects against bad code that attempts to use a connection after it has
	 * been closed.
	 */
	private void assertUsable() {
		if (this.mc == null) {
			throw new IllegalStateException("No managed connection availble, connection may have already been closed.");
		}
	}

}
