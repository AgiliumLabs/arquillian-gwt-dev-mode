/*
 * Copyright 2011 JBoss, by Red Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.agiliumlabs.arquillian.gwt;

import java.io.File;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.impl.base.spec.WebArchiveImpl;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.jboss.shrinkwrap.resolver.impl.maven.MavenBuilderImpl;

/**
 * @author Christian Sadilek <csadilek@redhat.com>
 */
public class GwtArchive extends WebArchiveImpl {

	public GwtArchive(Archive<?> delegate) {
		super(delegate);
	}

	public static GwtArchive create() {
		GwtArchive archive = ShrinkWrap.create(GwtArchive.class);
		archive.addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml"))
			.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		File jettyEnv = new File("src/main/webapp/WEB-INF/jetty-env.xml");
		if (jettyEnv.exists())
			archive.addAsWebInfResource(jettyEnv);
		MavenBuilderImpl libs = (MavenBuilderImpl) DependencyResolvers.use(MavenDependencyResolver.class)
			.configureFrom("../settings/settings.xml")
			.includeDependenciesFromPom("pom.xml");
		archive.addAsLibraries(libs.resolveAs(JavaArchive.class));
		return archive;
	}
	
	public GwtArchive addJpa() {
		addAsResource(new File("target/classes/META-INF/persistence.xml"), "META-INF/persistence.xml");
		addAsResource("import.sql");
		return this;
	}
	
	public GwtArchive addErrai() {
		addAsResource("ErraiApp.properties");
		addAsResource("ErraiService.properties");
		return this;
	}
	
}