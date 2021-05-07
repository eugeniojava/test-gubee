package com.eugeniojava.backend.service;

import com.eugeniojava.backend.controller.dto.ProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<List<ProductDto>> getAll();

    ResponseEntity<List<ProductDto>> getFilteredByTechnologies(
            List<String> technologies);

    ResponseEntity<List<ProductDto>> getFilteredByMarkets(
            List<String> markets);
}
