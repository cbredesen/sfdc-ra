package net.breddy.sfdc.resource.impl;

import java.io.PrintWriter;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;

/**
 * Encapsulates a Salesforce web service client connection along with JCA resource managment 
 * functionality.
 *  
 * @author Chris Bredesen
 * 
 * @see http://java.sun.com/developer/technicalArticles/J2EE/connectorclient/resourceadapter.html
 */
public class SalesforceManagedConnection implements ManagedConnection {

    // Registers a connection event 
    // listener with the instance.
	public void addConnectionEventListener(ConnectionEventListener listener) {
		throw new UnsupportedOperationException("Adding connection event listener");
	}

	public void associateConnection(Object connection) throws ResourceException {
		throw new UnsupportedOperationException("Associating connection");
	}

    // Reinitializes handles created by 
    // instance before the handle is put
    // back in the pool.
	public void cleanup() throws ResourceException {
		// TODO Auto-generated method stub

	}

    // Destroys a physical connection.
    // Typically called by the application 
    // server.
	public void destroy() throws ResourceException {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>This implementation returns a new {@link SalesforceConnectionImpl}.</p>
	 */
	public Object getConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
	    // Creates an application-level handle 
	    // to myEIS in an EIS specific way.
	    // The subject parameter is related to 
	    // the security contract and will
	    // be described later.
		return new SalesforceConnectionImpl(this);
	}

	public LocalTransaction getLocalTransaction() throws ResourceException {
		throw new UnsupportedOperationException("This ManagedConnection does not support transactions");
	}

    // Returns metadata information for myEIS 
    // and ManagedConnection instance.
	public ManagedConnectionMetaData getMetaData() throws ResourceException {
		return new ManagedConnectionMetaData() {
			
			public String getUserName() throws ResourceException {
				return "fred";
			}
			
			public int getMaxConnections() throws ResourceException {
				return 1;
			}
			
			public String getEISProductVersion() throws ResourceException {
				return "1";
			}
			
			public String getEISProductName() throws ResourceException {
				return "Salesforce";
			}
		};
	}

	public XAResource getXAResource() throws ResourceException {
		throw new UnsupportedOperationException("This ManagedConnection does not support transactions");
	}

    // Removes a connection event 
    // listener from the instance.
	public void removeConnectionEventListener(ConnectionEventListener listener) {
		throw new UnsupportedOperationException("Removing connection event listener");
	}

    // Allows the default log writer, provided 
    // by the factory instance, to be overridden.  
    // Should be set to null when the connection 
    // instance is returned to the pool.

    // Specific to myEIS.
	public void setLogWriter(PrintWriter out) throws ResourceException {
		// TODO Auto-generated method stub

	}

	public PrintWriter getLogWriter() throws ResourceException {
		// TODO Auto-generated method stub
		return null;
	}


}
