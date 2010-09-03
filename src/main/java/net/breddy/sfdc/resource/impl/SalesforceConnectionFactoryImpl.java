package net.breddy.sfdc.resource.impl;

import javax.resource.spi.ConnectionManager;

import net.breddy.sfdc.SalesforceConnection;
import net.breddy.sfdc.SalesforceConnectionFactory;

public class SalesforceConnectionFactoryImpl implements SalesforceConnectionFactory {
	private ConnectionManager cm;

	public SalesforceConnectionFactoryImpl(ConnectionManager connectionManager) {
		this.cm = connectionManager;
	}

	public SalesforceConnection getConnection() {
		return new SalesforceConnectionImpl();
	}

}
