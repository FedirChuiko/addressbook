Running the application as localhost:
Just build jar with maven and then run jar file. It will run Tomcat web container integrated into spring-boot.
example: java -jar MY_JAR.jar
To deploy it onto other container follow the https://spring.io/blog/2014/03/07/deploying-spring-boot-applications/
Used technologies and frameworks:
Vaadin - Because I am not experienced with any other frontend framework anyway.
Spring-Boot - brilliantly suitable framework for small projects, so to develop and run it with minimum fuss.
Spring Data JPA repositories - easy data access for small DB 
H2 Database - Java based DB, lightweight and you don't have to install it separately.
(As for now h2 is in memory mode, but you can persist data on disk by adjusting application.properties file.)
Maven - just because I have more experience with it than with gradle and others.
JUnit - I started develoment by writing test, but service/server layer is so thin, that tests looks really ridiculous,
and I am really not familiar with UI layer testing. That's why application have only one unit test.
libphonenumber library from Google - for phone numbers validation
P.S. This application is kinda free demo, that's why security, lockings, for multiuser safe data editing,
and other advanced features are not supported.:-)