package com.example.xmlconverter.controller;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/converter")
public class XmlConverterController {

    private XmlMapper xmlMapper = new XmlMapper();


}
