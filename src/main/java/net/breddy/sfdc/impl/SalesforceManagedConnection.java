package net.breddy.sfdc.impl;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEvent;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;

import net.breddy.sfdc.SalesforceConnection;
import net.breddy.sfdc.soap.Soap;

import org.jboss.logging.Logger;

/**
 * Encapsulates a Salesforce web service client connection along with JCA resource management 
 * functionality.  This is a long-lived, pooled instance that is used by many clients and cleaned
 * up each time.
 * 
 * @author Chris Bredesen
 * 
 * @see http://java.sun.com/developer/technicalArticles/J2EE/connectorclient/resourceadapter.html
 */
public class SalesforceManagedConnection implements ManagedConnection {
	private final Logger log = Logger.getLogger(SalesforceManagedConnection.class);
	private final ArrayList<ConnectionEventListener> listeners = new ArrayList<ConnectionEventListener>();
	private Soap soapClient;
	
	SalesforceManagedConnection(Soap soap) {
		this.soapClient = soap;
	}

	/**
	 * Get this connection's Soap client stub.  This is the actual EIS connection.
	 * 
	 * @return Soap client in a usable state.
	 */
	public Soap getSoapClient() {
		return soapClient;
	}

	/**
	 * {@inheritDoc}
	 */
	public void addConnectionEventListener(ConnectionEventListener listener) {
		log.trace("Adding connection event listener " + listener);
		listeners.add(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeConnectionEventListener(ConnectionEventListener listener) {
		log.trace("Removing connection event listener " + listener);
		listeners.remove(listener);
	}

	public void associateConnection(Object connection) throws ResourceException {
		throw new UnsupportedOperationException("Associating connection");
	}
	
	void closeHandle(SalesforceConnection handle) {
		log.trace("Closing connection handle " + handle);
		ConnectionEvent event = new ConnectionEvent(this, ConnectionEvent.CONNECTION_CLOSED);
		event.setConnectionHandle(handle);
		for (ConnectionEventListener listener : this.listeners) {
			log.trace("Firing connection closed event on listener " + listener);
			listener.connectionClosed(event);
		}
	}

    // Reinitializes handles created by 
    // instance before the handle is put
    // back in the pool.
	public void cleanup() throws ResourceException {
		log.trace("Cleaning up managed connection " + this);
		// TODO Auto-generated method stub
	}

    // Destroys a physical connection.
    // Typically called by the application 
    // server.
	public void destroy() throws ResourceException {
		log.trace("Destroying managed connection " + this);
		// TODO Auto-generated method stub

	}

	/**
	 * Get what is referred to elsewhere as a connection handle. This
	 * implementation returns a new {@link SalesforceConnectionImpl}.
	 */
	public Object getConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
		log.trace("Creating new connection handle for managed connection " + this);
		return new SalesforceConnectionImpl(this);
	}

	/**
	 * Not supported as this is a non-transactional RA.
	 * 
	 * @throws UnsupportedOperationException always.
	 */
	public LocalTransaction getLocalTransaction() throws ResourceException {
		throw new UnsupportedOperationException("This ManagedConnection does not support transactions");
	}

	/**
	 * Not supported as this is a non-transactional RA.
	 * 
	 * @throws UnsupportedOperationException always.
	 */
	public XAResource getXAResource() throws ResourceException {
		throw new UnsupportedOperationException("This ManagedConnection does not support transactions");
	}

	/**
	 * {@inheritDoc}
	 */
	public ManagedConnectionMetaData getMetaData() throws ResourceException {
		log.trace("Returning managed connection metadata for managed connection " + this);
		return new ManagedConnectionMetaData() {
			
			public String getUserName() throws ResourceException {
				return "TODO";
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

	/**
	 * {@inheritDoc}
	 */
	public void setLogWriter(PrintWriter out) throws ResourceException {
		// TODO should we set the managed connection log writer?
	}

	/**
	 * {@inheritDoc}
	 */
	public PrintWriter getLogWriter() throws ResourceException {
		// TODO will we ever have a managed connection log writer?
		return null;
	}

}
