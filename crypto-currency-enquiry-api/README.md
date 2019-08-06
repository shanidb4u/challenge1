# Crypto Currency Enquiry API
API to retrieve the crypto currency prices and Trade profit. Currently this app supports Bitcoin, Ethereum and Litecoin. 
Apart from listing prices, this app display the best possible profit that would have been made from buying a currency at a given price
and selling it later on the same day.

## Endpoints  
Following endpoints are supported by this API.    
- GET /prices/{currency}
    This endpoint is to retrieve the currency prices for a crypto currency for a particular day, currency should be passed as path parameter.  
    This endpoint accepts optional query parameter date in ISO_LOCAL_DATE format (yyyy-mm-dd). If no date is 
    passed then api will fetch the details for the previous day.
    example:     
    `curl -X GET "http://localhost:8080/cryptocurrencyapi/prices/BTC" -H "accept: */*"`
    `curl -X GET "http://localhost:8080/cryptocurrencyapi/prices/BTC?date=2018-05-07" -H "accept: */*"`
- GET /tradeProfit/{currency}     
    Api endpoint to retrieve the best possible profit that would have been made from buying a currency at a given price
    and selling it later on the same day. This endpoint accepts optional query parameter date in ISO_LOCAL_DATE format (yyyy-mm-dd). If not date is 
    passed the api will fetch the details for the previous day.
    `curl -X GET "http://localhost:8080/cryptocurrencyapi/tradeProfit/BTC" -H "accept: */*"`
      

## Technologies Used
``` 
SpringBoot,  
Java8,  
Gradle,  
Junit,   
RestAssured,  
Lombok,  
H2 DB,  
Mockito,  
Swagger,
Jacoco
  ```   

## Build and Deployment

run the below command to build and start the application.  
 
`./gradlew clean build bootRun`

Or Application can be deployed by running the command 

```./gradlew clean build && java -jar build/libs/crypto-currency-enquiry-api-0.0.1-SNAPSHOT.jar```

## Testing

This application is using Junit/Mockito for unit testing and RestAssured for API testing.
Jacoco tool for code coverage.
Run the below command for generating jacoco code coverage report.

`./gradlew clean test jacocoTestReport`

Report is generated at
/build/reports/jacoco/test/html/index.html

## Other Information

Swagger is used for API documentation , swagger can be accessed via below url.  
`http://localhost:8080/cryptocurrencyapi/swagger-ui.html#/`

H2 in-memory DB is used in this application and h2 console can be accessed via below url.   
`http://localhost:8080/cryptocurrencyapi/h2-console/login.do`

This application is using Lombok to reduce the boilerplate code. 
Please make sure to install Lombok plugin and enable annotation processor in IDE.

