package com.example.xmlconverter.service;

import com.example.xmlconverter.entity.Product;
import com.example.xmlconverter.entity.Products;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConverterServiceImpl implements ConverterService {

    private final XmlMapper xmlMapper = new XmlMapper();
    @Override
    public int getNumberOfRecords(String xmlFilePath) throws IOException {
        File file = new File(xmlFilePath);
        Products products = xmlMapper.readValue(file, Products.class);

        return products.getSize();
    }

    @Override
    public List<Product> getListOfProducts(String xmlFilePath) throws IOException {
        File file = new File(xmlFilePath);
        Products products = xmlMapper.readValue(file, Products.class);

        return products.getProductList();
    }

    @Override
    public List<Product> getProductByName(String name, String xmlFilePath) throws IOException {

        List<Product> list = getListOfProducts(xmlFilePath);
        List<Product> finalList = new ArrayList<>();

        for (Product product : list) {
            if (product.getName().equals(name))
                finalList.add(product);
        }

        return finalList;
    }
}
