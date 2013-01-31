/**
 * 
 */
package com.agiliumlabs.arquillian.gwt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.codehaus.plexus.util.IOUtil;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.Node;
import org.jboss.shrinkwrap.impl.base.AssignableBase;
import org.jboss.shrinkwrap.impl.base.path.PathUtil;

/**
 * @author roman
 *
 */
public class WarExporter<T extends Archive<T>> extends AssignableBase<T> {

	public WarExporter(T archive) {
		super(archive);
	}

	public void exportTo(File dir) throws IOException {
		FileUtils.deleteDirectory(dir);
		dir.mkdirs();
		Map<ArchivePath, Node> content = super.getArchive().getContent();
		for (Entry<ArchivePath, Node> entry : content.entrySet()) {
			ArchivePath path = entry.getKey();
			Node node = entry.getValue();
	        String pathName = PathUtil.optionallyRemovePrecedingSlash(path.get());
	        File target = new File(dir, pathName);
	        boolean isDirectory = node.getAsset() == null;
	        InputStream stream = null;
	        if (!isDirectory) {
	            stream = node.getAsset().openStream();
	            target.getParentFile().mkdirs();
		        OutputStream out = new FileOutputStream(target);
		        IOUtil.copy(stream, out);
	        }
	        else
	        	target.mkdirs();
	        target.deleteOnExit();
		}
	}

}