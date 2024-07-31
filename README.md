# Product Search Web Application

A simple web application for product search built with Spring Boot and RestTemplate. The application integrates with the Naver API to provide product search functionality.

## Description

This project is a Spring Boot-based web application that uses RestTemplate to interact with the Naver API for product search capabilities. It leverages various Spring Boot modules to offer a robust and secure application, including Spring Security, Spring Data JPA, and Thymeleaf.

## Prerequisites

* JDK 17
* Spring Boot 3.3.2

## Dependencies

* Spring Boot Starters: Includes spring-boot-starter-data-jpa, spring-boot-starter-security, spring-boot-starter-thymeleaf, spring-boot-starter-validation, and spring-boot-starter-web.
* Thymeleaf Extras
* Lombok
* MySQL Connector
* JWT
* JSON
* Testing

## Configuration
The application requires several configuration properties to be set up, including database credentials, Naver API credentials, and JWT secret keys. Below is an example of the necessary configuration in application.yml:
```yml
jwt:
  secret:
    key: YOUR_KEY

admin:
  token: YOUR_TOKEN

naver:
  client-id: YOUR_ID
  client-secret: YOUR_SECRET_KEY

spring:
  datasource:
    url: YOUR_DB_URL
    username: USERNAME
    password: PASSWORD
    driver-class-name: com.mysql.cj.jdbc.Driver
```
Ensure to replace the placeholder values for username, password, client-id, client-secret, jwt secret key, and admin token with your actual credentials before running the application. Storing sensitive information securely is crucial; consider using a secret management tool or environment variables for production environments.
