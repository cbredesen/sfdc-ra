package net.breddy.sfdc;

public interface SalesforceConnection {

	void close();

	String getSessionId();

}
