/**
 * 
 */
package com.agiliumlabs.arquillian.gwt;

import java.net.URL;

/**
 * @author roman
 *
 */
public class GwtApplicationContext {

	private URL applicationUrl;
	private URL contextUrl;
	
    public GwtApplicationContext(URL applicationUrl, URL contextUrl) {
    	this.applicationUrl = applicationUrl;
    	this.contextUrl = contextUrl;
    }
    
    public URL getApplicationUrl() {
    	return applicationUrl;
    }
    
    public URL getContextUrl() {
    	return contextUrl;
    }
    
}
