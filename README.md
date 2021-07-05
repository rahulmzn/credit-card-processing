# Credit Card Processing API
Credit card processing system allows adding new credit card to account and fetch existing credit cards by accessing Restful API's.

The credit card processing system has been built upon Spring Boot with support of Embedded MongoDB, 
Spring REST Docs and AsciiDoctor to provide quick access on resources.

This application provides below endpoints
```    
[POST] /api/v1/cards   : Add new credit card
[GET] /api/v1/cards    : Fetch existing cards
```
Full details for above endpoints are documented [here](https://github.com/rahulmzn/credit-card-processing/tree/master/docs)

### API Validation
* API will except JSON formatted request only.
* Credit card number will be checked based on Luhn10 algorithm
* Card number will be validated for number

Here we have used both type of validation (Custom and From Lib) 
* Basic validation provided by libraries like NotNull etc. ( To prove less code & more config)
* Custom Implementation for card validation (As asked in Assignment, Using Luh10 Algorithm, Implementation of Luhn10 can be found [here](https://github.com/rahulmzn/credit-card-processing/tree/master/src/main/java/com/bank/credit/card/api/constraints))

 > **_NOTE:_** Credit card number will be validated by our Luhn10 implementation which will be handle by our own created custom validation annotation applied on card->number field .

### API Documents
Credit card processing application provides below types of documentation 	
* Automatically generated API documentation (To see follow credit-card-processing/target/generated-docs directory)
* Swagger Open API specification

**For more details refer how to test section below**
    
### API docs PDF Samples	
See [credit-card-processing.pdf](https://github.com/rahulmzn/credit-card-processing/blob/master/docs/credit-card-processing.pdf) 

See [credit-card-processing-swagger-doc.pdf](https://github.com/rahulmzn/credit-card-processing/blob/master/src/main/apidocs/CreditCardApplicationSwagger.pdf)
### API Class Diagram
class diagrams can be accessed from class-diagram folder [here](https://github.com/rahulmzn/credit-card-processing/tree/master/class-diagram) 

### Embedded Mongo DB Config
```
* Database: cards
* Collection Name: cards
* url: localhost
* port: 27057
```
### Application properties Config
See [application.properties](https://github.com/rahulmzn/credit-card-processing/blob/master/src/main/resources/application.properties) 


## Prerequisites to Build & Run Credit Card Application
This application can be test based on availability of below tools & software.	 

#### Essential
    Java version 8
    
#### Optional
    Development IDE
    REST API Testing Tools (like postman)
    Maven version 3 or later (Good to have)

### Download source code from GitHub

 Source code of credit card application can be download [from here](https://github.com/rahulmzn/credit-card-processing/)

#### Download as Zip 

Zip file of full source code can be downloaded [from here](https://github.com/rahulmzn/credit-card-processing/archive/refs/heads/master.zip)

#### Download as source repository 
    
    git clone https://github.com/rahulmzn/credit-card-processing.git
  
## How to Build Application without Maven? 
>_NOTE_: If Maven software not installed (or configured) on system

>_NOTE_: To run any of below commands system command prompt should point inside downloaded application folder

        e.g cd credit-card-processing

#### Build and Generate auto documentation for API
#####  On Mac OS or Unix Systems
	./mvnw clean install 
##### On Windows Systems
	./mvnw.cmd clean install 

#### Build as Package	
##### On Mac OS or Unix Systems
    ./mvnw package
##### On Windows Systems
    ./mvnw.cmd package


# How to Run Credit Card Application ?
To run credit card application execute below steps

#### Run application as Standalone Restful API
##### On Mac OS or Unix Systems
    ./mvnw spring-boot:run
##### On Windows Systems
    ./mvnw.cmd spring-boot:run
 
### How to Access & Test Credit Card API's?	
Application will be by default published on port 8080 at localhost, 
>_NOTE:_ This also can be customised by changing port value for server.port property [application.properties](https://github.com/rahulmzn/credit-card-processing/blob/master/src/main/resources/application.properties).
>Once port has been changed, do update below endpoint on newly configured port

#### Test Credit Card API via swagger
After deployment of application [Test Restful API](http://localhost:8080/swagger-ui-custom.html) link can be followed to test API's. 

    Access Test API : http://localhost:8080/swagger-ui-custom.html

>_NOTE:_ Published by default on environment: http://localhost:8080/

#### Test Credit Card API from command prompt (terminal) using CURL commands
After deployment of application [Test Restful API](http://localhost:8080/swagger-ui-custom.html) link can be followed to test API's. 
#### Add new card
     : curl --location --request POST 'http://localhost:8080/api/v1/cards' --header 'Content-Type: application/json' --data-raw '{ "brand": "VISA", "type": "testing type", "currency": "GBP", "limit": "1000", "funding": "", "number": "4012888888881881", "country": "GB", "name": "Rahul Kumr" }'
#### Fetch All cards
     : curl --header 'Content-Type: application/json' 'http://localhost:8080/api/v1/cards'

### Executed Test Reports
All the reports will be generated under `credit-card-processing/target` after running maven scripts as given above


### Additional Information about application   

#### How to Build Application with Maven? (Optional)  
>_NOTE_: If Maven software pre-installed on system.

##### Build and generate API documentation
	mvn clean install 

##### Build as Package
	mvn package


#### Run application from java
Find generated application packaged jar file inside credit-card-processing/target/credit-card-processing-0.0.1-SNAPSHOT.jar	
>_NOTE:_  Move terminal to target folder path using cd commend if mvn install command has been executed.
    
    java -jar credit-card-processing-0.0.1-SNAPSHOT.jar

