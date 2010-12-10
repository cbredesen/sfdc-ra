package net.breddy.sfdc.impl;

import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.security.auth.Subject;

import net.breddy.sfdc.soap.SforceService;
import net.breddy.sfdc.soap.Soap;

import org.jboss.logging.Logger;

/**
 * Factory for {@link SalesforceManagedConnection} instances. Also serves
 * as the main lifecycle object for this resource adapter -- a single instance
 * can service clients for the lifetime of an application server. Configuration
 * properties set here are used for all managed connections created by it.
 * 
 * @author Chris Bredesen
 */
public class SalesforceManagedConnectionFactory implements ManagedConnectionFactory {
	private Logger log = Logger.getLogger(SalesforceManagedConnectionFactory.class);
	private String wsdlLocation;
	private SforceService service;
	private boolean initialized = false;

	private synchronized void initialize() {
		log.trace("Checking initialization of managed connection factory");
		if (!initialized) {
			log.debug("Managed connection factory is not initialized");
			try {
				log.info("Initializing managed connection factory with WSDL location " + wsdlLocation);
				this.service = new SforceService(new URL(wsdlLocation), null);
				initialized = true;
			} catch (MalformedURLException e) {
				throw new RuntimeException("Could not create Salesforce service client for WSDL at " + wsdlLocation, e);
			}
		}
	}

	/**
	 * Oracle doco says this isn't applicable.
	 * 
	 * @throws UnsupportedOperationException always.
	 */
	public Object createConnectionFactory() throws ResourceException {
		throw new UnsupportedOperationException("Not applicable to n-tier environments");
	}

	/**
	 * {@inheritDoc}
	 */
	public Object createConnectionFactory(ConnectionManager cxManager) throws ResourceException {
		log.info("Creating new SalesforceConnectionFactory");
		return new SalesforceConnectionFactoryImpl(cxManager, this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>This implementation creates a SOAP client and logs in with <code>subject</code>.</p>
	 */
	public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo cri) 
			throws ResourceException {
		initialize();
		log.debug("Creating new managed connection");
		Soap soap = service.getSoap();
		String username = "get this from Subject";
		String password = "get this from Subject";
		try {
//			soap.login(username, password);
		} catch (Exception e) {
			// TODO yuk
			throw new RuntimeException("Unable to log in to Salesforce", e);
		}
		return new SalesforceManagedConnection(soap);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>This implementation returns <code>null</code> and thus never matches.</p>
	 */
	@SuppressWarnings("unchecked")
	public ManagedConnection matchManagedConnections(Set connectionSet,
			Subject subject, ConnectionRequestInfo cxRequestInfo)
			throws ResourceException {
		log.trace("Matching managed connection (but returning null) for subject " + subject);
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public PrintWriter getLogWriter() throws ResourceException {
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	public void setLogWriter(PrintWriter out) throws ResourceException {
		// TODO possibly write a thin wrapper around this class's logger
	}

	/**
	 * Get the WSDL location for the backing SFDC instance.
	 *  
	 * @return WSDL location.
	 */
    public String getWsdlLocation() {
		return wsdlLocation;
	}

    /**
     * Set the WSDL location for the backing SFDC instance.
     * 
     * @param wsdlLocation SFDC instance WSDL location, cannot be <code>null</code>.
     */
	public void setWsdlLocation(String wsdlLocation) {
		this.wsdlLocation = wsdlLocation;
	}

	// Used by matchManagedConnections to check  
    // the criteria of an incoming request with 
    // existing connections. The implementation  
    // depends on the criteria that is specific 
    // to myEIS.
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

    // Used by matchManagedConnections.  The 
    // implementation is specific to myEIS.
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
