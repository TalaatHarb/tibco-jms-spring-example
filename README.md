# tibco-jms-spring-example
Example of using Tibco as a JMS bus for sending a message

For the application to work you need to have the following:
- tibco jars installed on your local maven repository
- a bus running with defined user and queue

## Installing the jars
inside the tibco installation folder usually at C:\tibco\ems\8.5\lib you will need to run  the command line and run the follownig commands:

mvn install:install-file -DgroupId=javax.jms -DartifactId=jms -Dversion=2.0 -Dpackaging=jar -Dfile=jms-2.0.jar

mvn install:install-file -DgroupId=com.tibco -DartifactId=tibjms -Dversion=8.5.1 -Dpackaging=jar -Dfile=tibjms.jar


## Running a bus
inside the tibco installation folder usually at C:\tibco\ems\8.5\samples\config

you will need to run the following commands

mkdir datastore

tibemsd -config tibemsd.conf

and in another command line you need to run the follwoing commands:

tibemsadmin -server "tcp://localhost:7222"

create user user password=password

create queue test.queue