/**
 * 
 */
package com.agiliumlabs.arquillian.gwt;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.util.FileUtils;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.Assignable;

/**
 * @author roman
 *
 */
public class GwtWebAppContext implements Assignable {

	/**
	 * Underlying delegate
	 */
	private final Archive<?> archive;

	/**
	 * Creates a new {@link GwtWebAppContext} using the 
	 * specified underlying archive
	 * 
	 * @throws IllegalArgumentException If the archive is not specified
	 */
	public GwtWebAppContext(final Archive<?> archive) throws IllegalArgumentException {
		super();

		if (archive == null) {
			throw new IllegalArgumentException("archive must be specified");
		}

		// Remember the archive from which we're created
		this.archive = archive;
	}
	
	public void export(GwtDevModeDeploymentConfiguration config) throws IOException {
		archive.as(WarExporter.class).exportTo(new File(config.getWar()));
	}
	
	public void delete(GwtDevModeDeploymentConfiguration config) {
		FileUtils.delete(new File(config.getWar()));
	}

	/**
	 * {@inheritDoc}
	 * @see org.jboss.shrinkwrap.api.Assignable#as(java.lang.Class)
	 */
	@Override
	public <TYPE extends Assignable> TYPE as(final Class<TYPE> clazz) {
		return archive.as(clazz);
	}
	
}