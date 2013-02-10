/**
 * 
 */
package com.agiliumlabs.arquillian.gwt;

import org.jboss.arquillian.container.spi.client.container.DeployableContainer;
import org.jboss.arquillian.core.spi.LoadableExtension;
import org.jboss.arquillian.test.spi.enricher.resource.ResourceProvider;

/**
 * @author roman
 *
 */
public class GwtExtension implements LoadableExtension {
	
	@Override
	public void register(ExtensionBuilder builder) {
		builder.service(DeployableContainer.class, GwtDevModeDeploymentContainer.class);
		builder.service(ResourceProvider.class, GwtApplicationContextResourceProvider.class);
	}

}