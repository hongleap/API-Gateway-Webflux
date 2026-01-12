# 🚀 API Gateway with Spring WebFlux + Eureka

A **reactive API Gateway** using **Spring Cloud Gateway**, **Spring WebFlux**, and **Eureka Service Discovery**.  
Dynamically routes requests to microservices with **load balancing**, **filters**, and **path rewriting**.

---

## 🌟 Features

- ⚡ Reactive and non-blocking API Gateway using **Spring WebFlux**
- 🌐 Dynamic service discovery using **Eureka**
- 🔄 Load balancing across multiple instances
- 🛠 Global and route-specific filters (logging, auth, path rewrite)
- 🧩 Optional enhancements: rate limiting, circuit breaker, fallback routes

---

## 🛠 Dependencies (Gradle)

```groovy
plugins {
    id 'org.springframework.boot' version '3.2.0'
    id 'io.spring.dependency-management' version '1.1.0'
    id 'java'
}

group = 'com.example'
version = '1.0.0'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot WebFlux
    implementation 'org.springframework.boot:spring-boot-starter-webflux'

    // Spring Cloud Gateway
    implementation 'org.springframework.cloud:spring-cloud-starter-gateway'

    // Eureka Discovery Client
    implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'

    // Actuator (optional)
    implementation 'org.springframework.boot:spring-boot-starter-actuator'

    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:2023.0.5"
    }
}
```
## ⚙️ Configuration (application.yml)

```groovy

server:
    port: 8080
    
spring:
    application:
        name: api-gateway

    cloud:
        gateway:
            default-filters:
                - StripPrefix=1
            discovery:
                locator:
                enabled: true
                lower-case-service-id: true
            routes:
                - id: fallback
                uri: http://httpbin.org:80
                predicates: 
                    - Path=/fallback

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
        register-with-eureka: true
        fetch-registry: true

```
## 🏗 Gateway Application (ApiGatewayApplication.java)

```groovy

package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}

```
## 🔗 Example: User Service Registration
```groovy

spring:
    application:
        name: user-service
server:
    port: 8081

eureka:
    client:
        service-url:
            defaultZone: http://localhost:8761/eureka/
        register-with-eureka: true
        fetch-registry: true


```

Access via Gateway:
```
http://localhost:8080/user-service/users/1
```

## 🛡 Optional Enhancements
- 📝 Custom Filters: Logging, authentication, or JWT validation
- ⏱ Rate Limiting: Limit requests per user or per service
- 🔧 Circuit Breaker: Use Resilience4j or Spring Cloud Circuit Breaker
- 🚨 Global Fallback: Redirect failed requests to a fallback endpoint


## 🔍 Monitoring
With Spring Boot Actuator:
```
http://localhost:8080/actuator/gateway/routes
http://localhost:8080/actuator/health
```
