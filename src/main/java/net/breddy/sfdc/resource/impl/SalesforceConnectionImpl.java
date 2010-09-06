package net.breddy.sfdc.resource.impl;

import javax.resource.spi.ManagedConnection;

import net.breddy.sfdc.SalesforceConnection;

import org.jboss.logging.Logger;

/**
 * A connection handle.  Encapsulates a {@link ManagedConnection} and a Salesforce WS client stub.
 * 
 * @author Chris Bredesen
 */
public class SalesforceConnectionImpl implements SalesforceConnection {
	private Logger log = Logger.getLogger(SalesforceConnectionImpl.class);
	private SalesforceManagedConnection mc;

	SalesforceConnectionImpl(SalesforceManagedConnection managedConnection) {
		this.mc = managedConnection;
	}

	/**
	 * {@inheritDoc}
	 */
	public void close() {
		assertValid();
		log.trace("Closing connection " + this);
		mc.closeHandle(this);
		this.mc = null;
	}

	public String getSessionId() {
		assertValid();
		return "42";
	}
	
	private void assertValid() {
		if (this.mc == null) {
			throw new IllegalStateException("Connection is invalid - no ManagedConnection available");
		}
	}

}
