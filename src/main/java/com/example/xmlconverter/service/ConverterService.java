package com.example.xmlconverter.service;

import com.example.xmlconverter.entity.Product;

import java.io.IOException;
import java.util.List;

public interface ConverterService {
    int getNumberOfRecords(String xmlFilePath) throws IOException;
    List<Product> getListOfProducts(String xmlFilePath) throws IOException;
    List<Product> getProductByName(String name, String xmlFilePath) throws IOException;
}
