package com.example.xmlconverter.entity;

import lombok.Data;
@Data
public class Product {
    private int id;
    private String name;
    private String category;
    private String partNumberNr;
    private String companyName;
    private boolean active;
}
