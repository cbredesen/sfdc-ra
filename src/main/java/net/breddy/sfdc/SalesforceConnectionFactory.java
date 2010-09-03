package net.breddy.sfdc;


/**
 * Client source for {@link SalesforceConnection} instances.
 * 
 * @author Chris Bredesen
 */
public interface SalesforceConnectionFactory {

	SalesforceConnection getConnection();

}