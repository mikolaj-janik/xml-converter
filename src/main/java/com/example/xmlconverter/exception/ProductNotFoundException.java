package com.example.xmlconverter.exception;

import java.io.IOException;

public class ProductNotFoundException extends IOException {
    public ProductNotFoundException(String name) {
        super("No product found with name: " + name);
    }
    public ProductNotFoundException() {
        super("No products were found ");
    }
}
