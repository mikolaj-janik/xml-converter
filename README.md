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

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/d591a0f1-0f17-4b3e-a7d7-a3abf990c516)


## Key Components

### XmlConverterController
Manages the API endpoints. There are 3 main endpoints:
- **getNumberOfProducts** – returns the total number of product records.
- **getAllProducts** – returns a list of all products in JSON format.
- **getProductByName** – returns the product or list of products with the specified name.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/1d75ca92-db06-4422-9371-a5f366e49267)



### ProductEntity
Represents a single product with fields such as id, name, category, part number, company name, and active status. Annotations from the Jackson library are used for XML to Java object mapping.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/6180c817-1a9c-428c-a6b4-07641cc503f3)

### ProductsEntity
Represents a list of products wrapped as a Products class to facilitate conversion. It provides methods to get the size of the list and add a product to the list.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/13ea5d1e-5aaa-4402-85f5-da37bf14f224)

### ConverterService
Interface for the service layer.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/0ab1c2d0-0b2d-43e4-ab9c-90d6d97b0bc0)



### ConverterServiceImpl
Implementation of the ConverterService interface. It uses XmlMapper to map objects from XML format to POJO notation. Its methods are:
- **getNumberOfRecords** – reads the XML file and returns the number of product records.
- **getListOfProducts** – reads the XML file and returns a list of all products.
- **getProductByName** – retrieves products by name from the file.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/f592147d-f294-47f4-af6f-6bfbf25e9671)
![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/9b4c119c-0854-4c7e-9d1c-38dd9d4e1609)



### ProductNotFoundException
This custom exception is designed to be thrown when a product or list of products cannot be found in the file.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/e482726e-a412-4487-a8dc-b502f64efdff)


### GlobalExceptionHandler
This class intercepts exceptions thrown by any controller, processes them and returns in JSON format.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/4c7645f0-d53e-4b28-8c44-023eb4c94130)


### ErrorResponse
This class is a data transfer object (DTO) used to encapsulate error details in a structured way.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/be5b5397-a9f3-453a-aaee-0ce40aff2b64)


## Example Usage

### Retrieving number of products
Using GET method and endpoint `/api/converter/getNumberOfProducts` we receive the response in integer representing the total number of products found in the XML file.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/c1c31225-f1eb-43d7-89c7-d9684080b884)


### The actual content of XML file

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/66a038fd-296a-4981-9663-e463b4175be4)

### Getting all products from file as a list
By using GET method on endpoint `/api/converter/getAllProducts` we receive a list of all products in JSON format.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/2fd7279e-42d6-414b-b4a0-dd96ec46ee2a)

If there are no products in the file, we receive an ErrorResponse object in JSON handled by GlobalExceptionHandler.
![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/8b7c07ac-728a-4c3d-bae8-5228c0bb4295)

### Getting product by name
Using endpoint `/api/converter/getProductByName/{name}` we receive in JSON the response which is a list of products matching the given name.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/2647f730-ebaf-48a2-a4cc-f6797bbf3142)

If no product is found, we receive an ErrorResponse object in JSON handled by GlobalExceptionHandler.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/31a0908a-19eb-4476-a45c-b13767e08a73)

### application.properties file
For demonstration purposes, we store the path to the XML file in `application.properties` file.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/31838d07-236b-45be-ab58-7174df0b9be4)

### Incorrect path
If we specify an incorrect path, we get the ErrorResponse.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/5c68427f-5164-4982-a97c-a954d3cf2ba8)
![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/b3370e40-7c08-4f84-8b18-c9e6b4affe4c)

### Incorrect syntax
Incorrect syntax in the file makes it impossible to be parsed, so we also get the ErrorResponse with a status of 400 (bad request).

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/a0bfaeaa-60c1-4eb3-8f37-f08fe620b92d)
![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/08cc902f-c495-4847-8ddf-f69fe90aaa13)


## Unit Tests

### Fields

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/4e38ac95-17ed-4600-aa94-d3aee3fe5fbd)

### Setup method
Initializes all fields.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/11e2907b-4128-448c-847a-966e90fa9c45)

### getAllProductsTest()
This method tests the `getListOfProducts` method of ConverterService.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/2272d25a-39dd-4754-b95f-b0fd48c767d0)

### getProductByNameTest()
This method tests the `getProductByName` method of ConverterService.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/65fac440-5145-441e-8b76-99b519ce0a5b)

### notFoundProductTest()
This method tests the `getProductByName` method to ensure it throws a ProductNotFoundException when the product is not found.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/dba47dcb-b479-4d4d-ac86-7ebe079a3332)

### fileNotFoundTest()
This method tests the `getNumberOfRecords` method to ensure that it throws a FileNotFoundException when the path is invalid.

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/1fb54b92-f075-4987-ba49-a8bbde88285c)

### Tests results

![image](https://github.com/mikolaj-janik/xml-converter/assets/127678386/caf4bb4a-d1a2-465a-a370-6f7993431bed)

## Conclusion

The XML Converter project provides a solution for processing XML product records and exposing the data via RESTful endpoints in JSON format. The use of Spring Boot and Jackson for XML processing, combined with comprehensive unit tests, ensures a reliable and maintainable application.
