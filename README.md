## URL Shortening Service
A URL Shortening Service built with Java Spring Boot, it uses Redis as an in-memory storage.


## POST REQUEST

    POST /api/urlRequest
    
### Description
Shorten a long URL 

### Headers
**Content-Type** : application/json

### RequestBody

    {
	"urlRequest":"https://mvnrepository.com/artifact/com.google.guava/guava/27.0.1-jre"
    }

### Response
    {
    "urlRequest": "https://mvnrepository.com/artifact/com.google.guava/guava/27.0.1-jre",
    "shortUrl": "http://localhost:8080/5dc4551d"
    }
    
## GET REQUEST
 
    GET /api/{urlRequest}


### Redirect to Original URL
    
    GET /5dc4551d
    
