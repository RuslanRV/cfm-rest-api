Maven Commands
==============

	mvn clean install        						- will build, run all tests and create war file
	
	mvn -D skipTests clean install -Denv=dev		- will build, run NO tests, copy "dev" environment config to ${user.home}/conf
	
	mvn clean install -Psoapui				- will build, package a war file WITH /conf/dev embeded i the war, use the tomcat plugin to startup a new tomcat server within maven and run the soap ui tests
	
	mvn clean install -Pjmeter						- will build, run unit and jmeter performance tests	
