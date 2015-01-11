play2048
========
Plays 2048 in a browser using Selenium.

Compiling/running with Maven
----------------------------
After cloning, just run

	mvn compile exec:java

To use Maven to generate an Eclipse project, run

	mvn eclipse:eclipse

To use the classes as a library, run

	mvn package

which will create a jar file in the target/ directory.
Then simply add the jar to your classpath.

If you're using Maven, you can install to your local Maven repository by running

	mvn install

Then update your pom.xml file's dependencies section:

	<dependency>
		<groupId>com.github.davidthomas426</groupId>
		<artifactId>play2048</artifactId>
		<version>1.0</version>
	</dependency>
