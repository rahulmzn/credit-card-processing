# Credit Card Application
Credit card application allows adding new credit card and fetch existing credit cards from system over Restful API.

<!-- TABLE OF CONTENTS -->
## Table of Contents

<details open="open">
   <ul>
      <li>
          <a href="#requirement">Requirement</a>
      </li>
      <li>
         <a href="#technology-stack-&-other-open---source-libraries">Technology stack &amp; other Open-source libraries</a>
         <ul>
            <li><a href="#data">Database</a></li>
            <li><a href="#server---backend">Server - Backend</a></li>
            <li><a href="#libraries-and-plugins">Libraries and Plugins</a></li>
            <li><a href="#others">Others</a></li>
         </ul>
      </li>
      <li>
         <a href="#getting-started">Getting Started</a>
         <ul>
            <li><a href="#running-credit-card-app-with-maven">Running Credit Card App with Maven</a>
                <ul>
                <li><a href="#on-mac-os">Run on Mac OS</a></li>
                <li><a href="#on-windows-os">Run on Windows OS</a></li>
                </ul>
            </li>
            <li><a href="#creating-executable-jar-and-then-running-the-application">Creating executable JAR and then running the application</a></li>
            <li><a href="#accessing-data-in-h2-database">Accessing Data in H2 Database</a>
                 <ul><li><a href="#h2-console">H2 Console</a></li></ul>
            </li>
         </ul>
      </li>
      <li>
         <a href="#code-coverage">Test Report</a>
         <ul>
            <li><a href="#jacoco">Jacoco</a></li>
         </ul>
      </li>
      <li>
         <a href="#testing-api">Testing API</a>
         <ul>
            <li><a href="#testing-with-postman-runner">Testing with Postman Runner</a></li>
            <li><a href="#testing-with-maven">Testing with Maven</a></li>
         </ul>
      </li>
      <li>
          <a href="#using-application">Using Application</a>
      </li>
      <li><a href="#documentation">Documentation</a></li>
      <li><a href="#contact">Contact</a></li>
   </ul>
</details>

### Requirement
System should allow adding new credit card and fetching all existing cards from database over Restful API.
* Validate credit card number using Luhn10
* New credit card start with £0
* For cards not compatible with Luhn 10, return an error

### Technology stack & other Open-source libraries

### Database
* [H2 Database Engine](https://www.h2database.com/html/main.html) Java SQL database. Embedded and server modes; in-memory databases
      
### Server - Backend

<details open="open">
   <ul>
      <li><a href="http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html">JDK</a> - Java™ Platform, Standard Edition Development Kit</li>
      <li><a href="https://spring.io/projects/spring-boot">Spring Boot</a> - Framework to ease the bootstrapping and development of new Spring Applications</li>
      <li><a href="https://maven.apache.org/">Maven</a> - Dependency Management</li>
   </ul>
</details>

### Libraries and Plugins

<details open="open">
   <ul>
      <li><a href="https://swagger.io/">Swagger</a> - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.</li>
   </ul>
</details>

### Others

<details open="open">
   <ul>
      <li><a href="https://git-scm.com/">Git</a> - Free and Open-Source distributed version control system</li>
   </ul>
</details>

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing
purposes.

## Installing App

#### Running credit card app with maven

Alternatively you can use
the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html)
like so (Maven should be installed in the system and mvn command is accessible):

##### On mac os
```shell
$ git clone https://github.com/rahulmzn/credit-card-app.git
$ cd credit-card-app
$ ./mvnw spring-boot:run
```
##### On windows os
```shell
$ git clone https://github.com/rahulmzn/credit-card-app.git
$ cd credit-card-app
$ ./mvnw.bat spring-boot:run
```

#### Creating executable JAR and then running the application

The code can also be built into a jar and then executed/run. Once the jar is built, run the jar by double-clicking on it
or by using the command

```shell
$ git clone https://github.com/rahulmzn/credit-card-app.git
$ cd credit-card-app
$ mvn package -DskipTests
$ java -jar target/credit-card-app-0.0.1-SNAPSHOT.jar.jar
```

To shut down the jar, follow the below mentioned steps on a Mac os machine.

* In command prompt execute the **lsof -i tcp:8085** command to print a list of all running Java processes
* **kill -9 /PID PROCESS_ID_OF_RUNNING_APP /F** execute this command by replacing the **PROCESS_ID_OF_RUNNING_APP**
  with the actual process id of the running jar found out from executing the previous command
* Press enter


To shut down the jar, follow the below mentioned steps on a Windows machine.

* In command prompt execute the **jcmd** command to print a list of all running Java processes
* **Taskkill /PID PROCESS_ID_OF_RUNNING_APP /F** execute this command by replacing the **PROCESS_ID_OF_RUNNING_APP**
  with the actual process id of the running jar found out from executing the previous command
* Press Ctrl+C
##### Accessing Data in H2 Database

###### H2 Console

URL to access H2 console: **http://localhost:8085/h2-console/login.jsp**
or **https://192.168.99.102:8085/h2-console/login.jsp** if **SSL** is enabled.

Fill the login form as follows and click on Connect:

* Saved Settings: **Generic H2 (Embedded)**
* Setting Name: **Generic H2 (Embedded)**
* Driver class: **org.h2.Driver**
* JDBC URL: **jdbc:h2:mem:bank;DB_CLOSE_ON_EXIT=FALSE**
* User Name: **sa**
* Password:

## Code Coverage

### Jacoco

Generating code coverage reports

##### On mac os
```shell
$ git clone https://github.com/rahulmzn/credit-card-app.git
$ cd credit-card-app
$ ./mvnw clean test jacoco:report

```
##### On windows os
```shell
$ git clone https://github.com/rahulmzn/credit-card-app.git
$ cd credit-card-app
$ ./mvnw.bat clean test jacoco:report

```

This will create a detailed HTML style report showing code coverage statistics gathered via code instrumentation.

**credit-card-app\target\site\jacoco\index.html**

## Testing API

### Testing with Postman Runner

Import the `\docs\CreditCardApp.postman_collection.json` file into postman and run the API tests (Spring Boot service should be running).

### Testing with Maven
* Run only unit tests:

```shell
$ ./mvn clean test
```

## Documentation

* [Swagger](http://localhost:8085/swagger-ui/) - http://localhost:8085/swagger-ui- Documentation & Testing
</br>  
* [Swagger](http://localhost:8085/v2/api-docs) - http://localhost:8085/v2/api-docs- Documentation as JSON

## Contact
Project Link: [CreditCardApp](https://github.com/rahulmzn/credit-card-app)
