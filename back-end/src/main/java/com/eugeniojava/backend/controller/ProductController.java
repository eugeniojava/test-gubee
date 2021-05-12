package com.eugeniojava.backend.controller;

import com.eugeniojava.backend.controller.dto.ProductDto;
import com.eugeniojava.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable long id) {
        return productService.findById(id)
                .map(ProductDto::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> listAll() {
        var products = productService.listAll();

        return ResponseEntity.ok(products.stream().map(ProductDto::fromDomain).collect(toList()));
    }

    @GetMapping("/filterByTechnology/{technologies}")
    public ResponseEntity<List<ProductDto>> getFilteredByTechnologies(@PathVariable List<String> technologies) {
        var products = productService.getFilteredByTechnologies(technologies);

        return ResponseEntity.ok(products.stream().map(ProductDto::fromDomain).collect(toList()));
    }

    @GetMapping("/filterByMarket/{markets}")
    public ResponseEntity<List<ProductDto>> getFilteredByMarkets(@PathVariable List<String> markets) {
        var products = productService.getFilteredByMarkets(markets);

        return ResponseEntity.ok(products.stream().map(ProductDto::fromDomain).collect(toList()));
    }
}
