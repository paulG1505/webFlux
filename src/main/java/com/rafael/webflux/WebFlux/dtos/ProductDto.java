package com.rafael.webflux.WebFlux.dtos;

import com.rafael.webflux.WebFlux.documents.Product;

public class ProductDto {

    private String code,name,description;

    private double price;

    public ProductDto() {
    }

    public ProductDto(String code, String name, String description, double price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductDto(Product product){

        this(
          product.getCode(),
          product.getName(),
          product.getDescription(),
          product.getPrice()
        );
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
