package net.breddy.sfdc.impl;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionRequestInfo;
import javax.security.auth.Subject;

import junit.framework.TestCase;

public class SalesforceManagedConnectionFactoryTest extends TestCase {

	public void testCreateManagedConnection() throws ResourceException {
		SalesforceManagedConnectionFactory mcf = new SalesforceManagedConnectionFactory();
		mcf.setWsdlLocation("https://test.salesforce.com/services/Soap/c/18.0");
		Subject s = new Subject();
		ConnectionRequestInfo cri = new ConnectionRequestInfo() {};
		SalesforceManagedConnection mc = (SalesforceManagedConnection) mcf.createManagedConnection(s, cri);
		assertNotNull(mc);
	}
}
