/**
 * 
 */
package com.agiliumlabs.arquillian.gwt;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;

/**
 * @author roman
 *
 */
public class WarExporterTest {

	private static final String TEST_DIR = "target/test";
	private File testDir;
	
	@Before
	public void setUp() throws IOException {
		testDir = new File(TEST_DIR);
		FileUtils.deleteDirectory(testDir);
	}
	
	/**
	 * Test method for {@link com.agiliumlabs.arquillian.gwt.WarExporter#exportTo(java.io.File)}.
	 * @throws IOException 
	 */
	@Test
	public void testExportTo() throws IOException {
		WebArchive archive = ShrinkWrap.create(WebArchive.class).addPackages(true, "com.agiliumlabs.arquillian.gwt.test");
		new WarExporter<WebArchive>(archive).exportTo(testDir);
		File output = new File(testDir, "WEB-INF/classes/com/agiliumlabs/arquillian/gwt/test/TestArtifact.class");
		assertTrue(output.exists());
	}

}
