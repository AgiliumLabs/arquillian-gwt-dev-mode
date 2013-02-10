/**
 * 
 */
package com.agiliumlabs.arquillian.gwt;

import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import org.jboss.arquillian.container.spi.client.protocol.metadata.ProtocolMetaData;
import org.jboss.arquillian.container.test.impl.enricher.resource.URLResourceProvider;
import org.jboss.arquillian.core.api.Instance;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.test.api.ArquillianResource;

/**
 * @author roman
 *
 */
public class GwtApplicationContextResourceProvider extends URLResourceProvider {

	@Inject
	private Instance<ProtocolMetaData> protocolMetadata;

	@Override
	public boolean canProvide(Class<?> type) {
		return GwtApplicationContext.class.isAssignableFrom(type);
	}

	@Override
	public Object doLookup(ArquillianResource resource, Annotation... qualifiers) {
		URL contextUrl = (URL) super.doLookup(resource, qualifiers);
		Collection<GwtDevModeDeploymentConfiguration> configs = protocolMetadata.get().getContexts(GwtDevModeDeploymentConfiguration.class);
		GwtDevModeDeploymentConfiguration config = configs.iterator().next();
		URL applicationUrl = contextUrl;
		try {
			applicationUrl = new URL(contextUrl, config.getStartPage() + "?gwt.codesvr=" + config.getBindAddress() + ":" + config.getCodeServerPort());
		} catch (MalformedURLException e) {
			throw new RuntimeException("Unable to construct GWT application URL", e);
		}
		return new GwtApplicationContext(applicationUrl, contextUrl);
	}

}