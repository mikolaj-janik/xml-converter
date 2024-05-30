package com.example.xmlconverter.controller;

import com.example.xmlconverter.entity.Product;
import com.example.xmlconverter.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/converter")
public class XmlConverterController {

    private ConverterService converterService;

    @Value("${converter.file.path}")
    private String filePath;

    @Autowired
    public XmlConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping(value = "getNumberOfProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getNumberOfProducts() throws IOException {
        return converterService.getNumberOfRecords(filePath);
    }

    @GetMapping(value = "/getAllProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProducts() throws IOException {
        return converterService.getListOfProducts(filePath);
    }

    @GetMapping(value = "/getProductByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProductByName(@PathVariable("name") String name) throws IOException {
        return converterService.getProductByName(name, filePath);
    }

}

