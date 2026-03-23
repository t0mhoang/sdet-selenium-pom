# SDET Selenium POM Framework

A multi-layer Java test framework combining UI automation, API testing, and BDD.

## What this covers
- Page Object Model design pattern with Selenium WebDriver
- BaseTest class for driver lifecycle management
- Explicit waits with WebDriverWait
- REST Assured API testing — GET, POST, status codes, response body assertions
- Cucumber BDD with Gherkin feature files and step definitions
- Allure reporting with Epic, Feature, Story and Severity annotations
- Maven build and test orchestration

## Tech stack
- Java 17
- Selenium 4.18.1
- REST Assured 5.4.0
- Cucumber 7.14.0
- JUnit 5.10.0
- WebDriverManager 5.7.0
- Allure 2.25.0
- Maven

## How to run

Run all tests:

    mvn clean test

Generate Allure report:

    mvn allure:serve