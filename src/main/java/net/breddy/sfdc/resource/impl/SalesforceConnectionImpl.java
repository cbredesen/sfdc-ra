package net.breddy.sfdc.resource.impl;

import javax.resource.spi.ManagedConnection;

import net.breddy.sfdc.SalesforceConnection;

import org.jboss.logging.Logger;

/**
 * A connection handle. Encapsulates a {@link ManagedConnection} and a
 * Salesforce WS client stub.
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
		assertUsable();
		log.trace("Closing connection " + this);
		mc.closeHandle(this);
		this.mc = null;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getSessionId() {
		assertUsable();
		return "42";
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
