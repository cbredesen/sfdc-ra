package net.breddy.sfdc.resource.impl;

import net.breddy.sfdc.SalesforceConnection;

import org.jboss.logging.Logger;

public class SalesforceConnectionImpl implements SalesforceConnection {
	private Logger log = Logger.getLogger(SalesforceConnectionImpl.class);
	
	/**
	 * {@inheritDoc}
	 */
	public void close() {
		log.info("Closing connection " + this);
	}

	public String getSessionId() {
		return "42";
	}

}
