/**
 * 
 */
package com.agiliumlabs.arquillian.gwt;

import org.jboss.arquillian.container.spi.ConfigurationException;
import org.jboss.arquillian.container.spi.client.container.ContainerConfiguration;


/**
 * @author roman
 *
 */
public class GwtDevModeDeploymentConfiguration implements ContainerConfiguration {
	
	private int port = 8888;
	private LogLevel logLevel = LogLevel.DEBUG;
	private String bindAddress = "127.0.0.1";
	private int codeServerPort = 9997;
	private String war = "target/arquillian-gwt";
	private String[] modules;
	
	@Override
	public void validate() throws ConfigurationException {
		return;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public String getBindAddress() {
		return bindAddress;
	}

	public void setBindAddress(String bindAddress) {
		this.bindAddress = bindAddress;
	}

	public int getCodeServerPort() {
		return codeServerPort;
	}

	public void setCodeServerPort(int codeServerPort) {
		this.codeServerPort = codeServerPort;
	}

	public String getWar() {
		return war;
	}

	public void setWar(String war) {
		this.war = war;
	}
	
	public String[] getModulesArray() {
		return modules;
	}
	
	public String getModules() {
		if (modules == null)
			return null;
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String module : modules) {
			if (!first)
				sb.append(",");
			else 
				first = false;
			sb.append(module);
		}
		return sb.toString();
	}

	public void setModules(String modules) {
		this.modules = modules.split(",");
	}

	public static enum LogLevel {
		
		ERROR, WARN, INFO, TRACE, DEBUG, SPAM, ALL;
		
	}

}
