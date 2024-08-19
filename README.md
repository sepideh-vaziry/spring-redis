# Spring Boot Caching with Redis

## Overview
This project is a simple Spring Boot application that demonstrates how to implement caching using Redis. 
Caching is a critical optimization technique in software development, which helps to reduce the number of database calls and improve the performance of your application. 
Redis, an in-memory data structure store, is used here as the caching provider.

## Features
- Spring Boot: Leveraging the power of Spring Boot to build a robust application with minimal configuration.
- Caching: Implemented using Spring's caching abstraction.
- Redis Integration: Utilizing Redis as the caching provider for storing cache entries.
- REST API: A simple REST API to demonstrate caching with various endpoints.

## Prerequisites
Before you begin, ensure you have met the following requirements:

- Java 17: The project is built with Java 17.
- Maven: For building and managing dependencies.
- Redis Server: A running Redis instance (locally or via Docker).

## Getting Started
Clone the Repository

```shell
git clone git@github.com:sepideh-vaziry/spring-redis.git
cd spring-redis
```

Build the Project: Run application on different ports for demonstrating cache across several application instances.

```shell
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8080

mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8090
```

## Caching Annotations
This project uses the following caching annotations:

- @Cacheable: Caches the result of a method invocation.
- @CacheEvict: Removes one or more cache entries.
- @CachePut: Updates the cache without interfering with the method execution.

## Testing the Caching Behavior
You can test the caching behavior using the provided REST API endpoints:

- Create product:
    ```
    curl --location 'http://localhost:8080/api/v1/products' \
    --header 'Content-Type: application/json' \
    --data '{
    "name": "Product 1",
    "price": 10000,
    "quantity": 50
    }'
    ```
  
- Fetch a product by id:
    ```
    Get http://localhost:8080/api/v1/products/1
    ```

- Fetch products list:
    ```
    Get http://localhost:8080/api/v1/products
    ```