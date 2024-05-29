package com.example.xmlconverter.controller;

import com.example.xmlconverter.entity.Product;
import com.example.xmlconverter.service.ConverterService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/converter")
public class XmlConverterController {

    private ConverterService converterService;

    @Autowired
    public XmlConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @SneakyThrows
    @GetMapping(value = "getNumberOfProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getNumberOfProducts() {
        return converterService.getNumberOfRecords("C:/Users/RetailAdmin/Desktop/products.xml");
    }


    @GetMapping(value = "/getAllProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProducts() throws IOException {
        return converterService.getListOfProducts("C:/Users/RetailAdmin/Desktop/products.xml");
    }

    @GetMapping(value = "/getProductByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProductByName(@PathVariable("name") String name) throws IOException {
        return converterService.getProductByName(name, "C:/Users/RetailAdmin/Desktop/products.xml");
    }
}
