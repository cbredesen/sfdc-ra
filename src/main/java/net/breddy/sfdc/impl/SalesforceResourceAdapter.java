package net.breddy.sfdc.impl;

import javax.resource.ResourceException;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.BootstrapContext;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.transaction.xa.XAResource;

/**
 * The resource adapter. Primarily responsible for providing lifecycle handlers
 * that we don't use. Alas, we are required to specify a class name for the
 * adapter itself so here it is. Does absolutely nothing useful, other than
 * exist.
 * 
 * @author Chris Bredesen
 */
public class SalesforceResourceAdapter implements ResourceAdapter {

	@Override
	public void endpointActivation(MessageEndpointFactory endpointFactory,
			ActivationSpec spec) throws ResourceException {
	}

	@Override
	public void endpointDeactivation(MessageEndpointFactory endpointFactory,
			ActivationSpec spec) {
	}

	/**
	 * This is a non-transactional resource adapter so we will always return
	 * <code>null</code>.
	 * 
	 * @return <code>null</cod>, always.
	 */
	@Override
	public XAResource[] getXAResources(ActivationSpec[] specs)
			throws ResourceException {
		return null;
	}

	@Override
	public void start(BootstrapContext ctx)
			throws ResourceAdapterInternalException {
	}

	@Override
	public void stop() {
	}

	/**
	 * Advised to create this by IronJacamar.
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/**
	 * Advised to create this by IronJacamar.
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

}