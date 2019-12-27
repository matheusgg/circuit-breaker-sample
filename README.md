# Circuit Breaker Sample
#### This is a simple project using [Spring Cloud Circuit Breaker](https://spring.io/projects/spring-cloud-circuitbreaker) and [Spring Webflux with Kotlin Coroutines](https://docs.spring.io/spring/docs/5.2.2.RELEASE/spring-framework-reference/languages.html#coroutines)  

*To run the application*

1. Build the project
``` 
./gradlew clean build
``` 

2. Run the Main class
``` 
./gradlew bootRun
``` 

3. Make requests to endpoints
``` 
curl --location --request GET 'http://localhost:8080/users'

curl --location --request GET 'http://localhost:8080/conditionally/users?raiseError=true'

curl --location --request GET 'http://localhost:8080/timeout/users?timeout=6000'
``` 