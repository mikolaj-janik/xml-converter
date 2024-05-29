package com.example.xmlconverter.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
@Data
@JacksonXmlRootElement(localName = "Product")
public class Product {
    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private int id;

    @JacksonXmlProperty(localName = "Name")
    private String name;

    @JacksonXmlProperty(localName = "Category")
    private String category;

    @JacksonXmlProperty(localName = "PartNumberNR")
    private String partNumberNr;

    @JacksonXmlProperty(localName = "CompanyName")
    private String companyName;

    @JacksonXmlProperty(localName = "Active")
    private boolean active;
}
