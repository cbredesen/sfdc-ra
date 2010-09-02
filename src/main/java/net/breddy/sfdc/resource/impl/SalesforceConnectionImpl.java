package net.breddy.sfdc.resource.impl;

import javax.resource.ResourceException;
import javax.resource.cci.ConnectionMetaData;
import javax.resource.cci.Interaction;
import javax.resource.cci.LocalTransaction;
import javax.resource.cci.ResultSetInfo;

import org.jboss.logging.Logger;

import net.breddy.sfdc.resource.SalesforceConnection;

public class SalesforceConnectionImpl implements SalesforceConnection {
	private Logger log = Logger.getLogger(SalesforceConnectionImpl.class);
	
	/**
	 * {@inheritDoc}
	 */
	public void close() throws ResourceException {
		log.trace("Closing connection " + this);
	}

	public Interaction createInteraction() throws ResourceException {
		// TODO Auto-generated method stub
		return null;
	}

	public LocalTransaction getLocalTransaction() throws ResourceException {
		// TODO Auto-generated method stub
		return null;
	}

	public ConnectionMetaData getMetaData() throws ResourceException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultSetInfo getResultSetInfo() throws ResourceException {
		// TODO Auto-generated method stub
		return null;
	}

}
