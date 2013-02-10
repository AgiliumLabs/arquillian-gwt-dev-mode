package com.agiliumlabs.arquillian.gwt;

/**
 * 
 */
import org.jboss.arquillian.container.spi.client.container.DeployableContainer;
import org.jboss.arquillian.container.spi.client.container.DeploymentException;
import org.jboss.arquillian.container.spi.client.container.LifecycleException;
import org.jboss.arquillian.container.spi.client.protocol.ProtocolDescription;
import org.jboss.arquillian.container.spi.client.protocol.metadata.HTTPContext;
import org.jboss.arquillian.container.spi.client.protocol.metadata.ProtocolMetaData;
import org.jboss.arquillian.container.spi.client.protocol.metadata.Servlet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.descriptor.api.Descriptor;
import org.mortbay.jetty.servlet.ServletHolder;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * @author roman
 *
 */
public class GwtDevModeDeploymentContainer implements DeployableContainer<GwtDevModeDeploymentConfiguration> {

	private GwtDevModeDeploymentConfiguration config;
	private GwtDevMode devMode;
	private GwtWebAppContext ctx;
	
	@Override
	public Class<GwtDevModeDeploymentConfiguration> getConfigurationClass() {
		return GwtDevModeDeploymentConfiguration.class;
	}

	@Override
	public void setup(GwtDevModeDeploymentConfiguration configuration) {
		this.config = configuration;
	}

	@Override
	public void start() throws LifecycleException {
	}
	
	private void doStart(GwtArchive archive) throws LifecycleException {
		try {
			devMode = new GwtDevMode();
			devMode.start(config, archive);
		} catch (Exception e) {
			throw new LifecycleException("Could not start container", e);
		}
	}

	@Override
	public void stop() throws LifecycleException {
		try {
			devMode.doShutDownServer();
		} catch (Exception e) {
			throw new LifecycleException("Could not stop container", e);
		}
	}

	@Override
	public ProtocolDescription getDefaultProtocol() {
		return new ProtocolDescription("Servlet 2.5");
	}

	@Override
	public ProtocolMetaData deploy(Archive<?> archive) throws DeploymentException {
		try {
			ctx = archive.as(GwtWebAppContext.class);
			ctx.export(config);
			doStart((GwtArchive) archive);
			WebAppContext wctx = devMode.getWebAppContext();
			HTTPContext httpContext = new HTTPContext(config.getBindAddress(), config.getPort());
	        for(ServletHolder servlet : wctx.getServletHandler().getServlets())
	            httpContext.add(new Servlet(servlet.getName(), wctx.getContextPath()));
			return new ProtocolMetaData().addContext(httpContext).addContext(config);
		} catch (Exception e) {
			throw new DeploymentException("Could not deploy " + archive.getName(), e);
		}
	}

	@Override
	public void undeploy(Archive<?> archive) throws DeploymentException {
		try {
			stop();
			ctx.delete(config);
		} catch (Exception e) {
			throw new DeploymentException("Error undeploying GWT application", e);
		}
	}

	@Override
	public void deploy(Descriptor descriptor) throws DeploymentException {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void undeploy(Descriptor descriptor) throws DeploymentException {
		throw new UnsupportedOperationException("Not yet implemented");
	}

}