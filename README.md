Arquillian GWT Developlement Mode Container Extension
=====================================================

The extension makes it easy to write Arquillian tests against GWT applications.

How To Use
----------
* pom.xml:
```xml
<dependency>
    <groupId>com.agiliumlabs.arquillian</groupId>
    <artifactId>arquillian-gwt-dev-mode</artifactId>
    <version>1.0.0.Final</version>
    <scope>test</scope>
</dependency>
```

* src/test/resources/arquillian.xml:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<arquillian xmlns="http://jboss.org/schema/arquillian" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://jboss.org/schema/arquillian http://jboss.org/schema/arquillian/arquillian_1_0.xsd">
    <container qualifier="gwt" default="true">
    	<configuration>
    		<property name="modules">package.Module1,package.Module2</property>
    	</configuration>
    </container>
</arquillian>
```

* YourTest.java:
```java
@RunWith(Arquillian.class)
public class YourTest {

    @Deployment
    public static GwtArchive buildDeployment() {
        GwtArchive.create().addPackages(true, "your.app.package").
				addAsWebResource(new File("src/main/webapp/App.html"));   
	}

  	/* The rest of your test goes here */
```

Licensing
---------
The extension is licensed under GNU lesser general public license version 3.

See Also
--------
* http://arquillian.org[Arquillian Project Site]
