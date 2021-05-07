package com.eugeniojava.backend.service.impl;

import com.eugeniojava.backend.controller.dto.ProductDto;
import com.eugeniojava.backend.model.Product;
import com.eugeniojava.backend.repository.ProductRepository;
import com.eugeniojava.backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDto>> getAll() {
        List<Product> products = productRepository.findAll();

        if (!products.isEmpty()) {
            return new ResponseEntity<>(ProductDto.fromListModel(products),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<ProductDto>> getFilteredByTechnologies(
            List<String> technologies) {
        List<Product> products =
                productRepository.findByTechnologiesNameIn(technologies);

        if (!products.isEmpty()) {
            return new ResponseEntity<>(ProductDto.fromListModel(products),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<ProductDto>> getFilteredByMarkets(
            List<String> markets) {
        List<Product> products = productRepository.findByMarketsNameIn(markets);

        if (!products.isEmpty()) {
            return new ResponseEntity<>(ProductDto.fromListModel(products),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
