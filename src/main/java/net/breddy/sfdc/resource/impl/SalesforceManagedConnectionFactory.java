package net.breddy.sfdc.resource.impl;

import java.io.PrintWriter;
import java.util.Set;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.security.auth.Subject;

import org.jboss.logging.Logger;


public class SalesforceManagedConnectionFactory implements ManagedConnectionFactory {
	private Logger log = Logger.getLogger(SalesforceManagedConnectionFactory.class);

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
	public Object createConnectionFactory(ConnectionManager cxManager)
			throws ResourceException {
		log.info("Creating new SalesforceConnectionFactory");
		return new SalesforceConnectionFactoryImpl(cxManager, this);
	}

    // Creates a new physical connection 
    // to myEIS in an EIS specific way.
    // The subject parameter is related to 
    // the security contract and will be
    // described later.
	public ManagedConnection createManagedConnection(Subject subject,
			ConnectionRequestInfo cri) throws ResourceException {
		log.debug("Creating new SalesforceManagedConnection");
		return new SalesforceManagedConnection();
	}

    // Determines if there is an existing 
    // connection, from the parameter
    // ConnectionSet, that can be used as 
    // the connection to myEIS.  The check
    // is based on criteria that is 
    // specific to myEIS.

    // Must return null if a match does 
    // not exist.

    // The subject parameter is related to 
    // the security contract and will
    // be described later.
	public ManagedConnection matchManagedConnections(Set connectionSet,
			Subject subject, ConnectionRequestInfo cxRequestInfo)
			throws ResourceException {
		// TODO Auto-generated method stub
		return null;
	}

    // Returns the log writer of the instance.  
    // Specific to myEIS.
	public PrintWriter getLogWriter() throws ResourceException {
		return null;
	}


	// Registers an output stream, for logging 
    // and tracing, with the instance.
    // Implementation is specific to myEIS.
	public void setLogWriter(PrintWriter out) throws ResourceException {
		// TODO possibly write a thin wrapper around this class's logger
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
