package com.eugeniojava.backend.controller;

import com.eugeniojava.backend.controller.dto.ProductDto;
import com.eugeniojava.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        return productService.getAll();
    }

    @GetMapping("/filterByTechnology/{technologies}")
    public ResponseEntity<List<ProductDto>> getFilteredByTechnologies(
            @PathVariable List<String> technologies) {
        return productService.getFilteredByTechnologies(technologies);
    }

    @GetMapping("/filterByMarket/{markets}")
    public ResponseEntity<List<ProductDto>> getFilteredByMarkets(
            @PathVariable List<String> markets) {
        return productService.getFilteredByMarkets(markets);
    }
}
