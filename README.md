# XML Converter Project

## Contents

1. [Overview](#overview)
2. [Project Structure](#project-structure)
3. [Key Components](#key-components)
   - [XmlConverterController](#xmlconvertercontroller)
   - [ProductEntity](#productentity)
   - [ProductsEntity](#productsentity)
   - [ConverterService](#converterservice)
   - [ConverterServiceImpl](#converterserviceimpl)
   - [ProductNotFoundException](#productnotfoundexception)
   - [GlobalExceptionHandler](#globalexceptionhandler)
   - [ErrorResponse](#errorresponse)
4. [Example Usage](#example-usage)
   - [Retrieving number of products](#retrieving-number-of-products)
   - [The actual content of XML file](#the-actual-content-of-xml-file)
   - [Getting all products from file as a list](#getting-all-products-from-file-as-a-list)
   - [Getting product by name](#getting-product-by-name)
   - [application.properties file](#applicationproperties-file)
   - [Incorrect path](#incorrect-path)
   - [Incorrect syntax](#incorrect-syntax)
5. [Unit Tests](#unit-tests)
   - [Fields](#fields)
   - [Setup method](#setup-method)
   - [getAllProductsTest()](#getallproductstest)
   - [getProductByNameTest()](#getproductbynametest)
   - [notFoundProductTest()](#notfoundproducttest)
   - [fileNotFoundTest()](#filenotfoundtest)
   - [Tests results](#tests-results)
6. [Conclusion](#conclusion)

## Overview

The project is a Spring Boot application designed to read and process XML files containing product records. It exposes a set of REST endpoints that allow clients to interact with the product data. The core functionalities include fetching the total number of products, retrieving all products in JSON format, and retrieving specific products by their name. The project also includes exception handling and unit tests to ensure the reliability of the implemented features.

## Project Structure

1. **Controller Layer** – manages the HTTP requests and delegates them to the appropriate service methods.
2. **Service Layer** - contains the business logic for processing the XML files and handling the product data.
3. **Entity Layer** - defines the data models representing the product and the product list.
4. **Exception Handling** - custom exceptions to manage scenarios where products are not found.
5. **Unit Tests** - ensures the correctness of the implemented functionalities.

## Key Components

### XmlConverterController
Manages the API endpoints. There are 3 main endpoints:
- **getNumberOfProducts** – returns the total number of product records.
- **getAllProducts** – returns a list of all products in JSON format.
- **getProductByName** – returns the product or list of products with the specified name.

### ProductEntity
Represents a single product with fields such as id, name, category, part number, company name, and active status. Annotations from the Jackson library are used for XML to Java object mapping.

### ProductsEntity
Represents a list of products wrapped as a Products class to facilitate conversion. It provides methods to get the size of the list and add a product to the list.

### ConverterService
Interface for the service layer.

### ConverterServiceImpl
Implementation of the ConverterService interface. It uses XmlMapper to map objects from XML format to POJO notation. Its methods are:
- **getNumberOfRecords** – reads the XML file and returns the number of product records.
- **getListOfProducts** – reads the XML file and returns a list of all products.
- **getProductByName** – retrieves products by name from the file.

### ProductNotFoundException
This custom exception is designed to be thrown when a product or list of products cannot be found in the file.

### GlobalExceptionHandler
This class intercepts exceptions thrown by any controller, processes them and returns in JSON format.

### ErrorResponse
This class is a data transfer object (DTO) used to encapsulate error details in a structured way.

## Example Usage

### Retrieving number of products
Using GET method and endpoint `/api/converter/getNumberOfProducts` we receive the response in integer representing the total number of products found in the XML file.

### The actual content of XML file

### Getting all products from file as a list
By using GET method on endpoint `/api/converter/getAllProducts` we receive a list of all products in JSON format.

If there are no products in the file, we receive an ErrorResponse object in JSON handled by GlobalExceptionHandler.

### Getting product by name
Using endpoint `/api/converter/getProductByName/{name}` we receive the response which is a list of products matching the given name in JSON format.

If no product is found, we receive an ErrorResponse object in JSON handled by GlobalExceptionHandler.

### application.properties file
For demonstration purposes, we store the path to the XML file in `application.properties` file.

### Incorrect path
If we specify an incorrect path, we get the ErrorResponse.

### Incorrect syntax
Incorrect syntax in the file makes it impossible to be parsed, so we also get the ErrorResponse with a status of 400 (bad request).

## Unit Tests

### Fields

### Setup method
Initializes all fields.

### getAllProductsTest()
This method tests the `getListOfProducts` method of ConverterService.

### getProductByNameTest()
This method tests the `getProductByName` method of ConverterService.

### notFoundProductTest()
This method tests the `getProductByName` method to ensure it throws a ProductNotFoundException when the product is not found.

### fileNotFoundTest()
This method tests the `getNumberOfRecords` method to ensure that it throws a FileNotFoundException when the path is invalid.

### Tests results

## Conclusion

The XML Converter project provides a solution for processing XML product records and exposing the data via RESTful endpoints in JSON format. The use of Spring Boot and Jackson for XML processing, combined with comprehensive unit tests, ensures a reliable and maintainable application.
