package com.eugeniojava.backend.controller.dto;

import com.eugeniojava.backend.model.Market;
import com.eugeniojava.backend.model.Product;
import com.eugeniojava.backend.model.Technology;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDto {

    private final String productName;
    private final String description;
    private final List<String> targetMarket;
    private final List<String> stack;

    public ProductDto(Product product) {
        this.productName = product.getName();
        this.description = product.getDescription();
        this.targetMarket = product.getMarkets()
                .stream()
                .map(Market::getName)
                .collect(Collectors.toList());
        this.stack = product.getTechnologies()
                .stream()
                .map(Technology::getName)
                .collect(Collectors.toList());
    }

    public static List<ProductDto> fromListModel(List<Product> products) {
        return products
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTargetMarket() {
        return targetMarket;
    }

    public List<String> getStack() {
        return stack;
    }
}
