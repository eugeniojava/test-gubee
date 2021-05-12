package com.eugeniojava.backend.controller.dto;

import com.eugeniojava.backend.model.Market;
import com.eugeniojava.backend.model.Product;
import com.eugeniojava.backend.model.Technology;
import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class ProductDto {

    private final String productName;
    private final String description;
    private final List<String> targetMarket;
    private final List<String> stack;

    public ProductDto(Product product) {
        this.productName = product.getName();
        this.description = product.getDescription();
        this.targetMarket = product.getMarkets().stream().map(Market::getName).collect(toList());
        this.stack = product.getTechnologies().stream().map(Technology::getName).collect(toList());
    }

    public static ProductDto fromDomain(Product product) {
        return new ProductDto(product);
    }
}
