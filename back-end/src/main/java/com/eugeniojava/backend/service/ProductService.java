package com.eugeniojava.backend.service;

import com.eugeniojava.backend.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(long id);

    List<Product> listAll();

    List<Product> getFilteredByTechnologies(
            List<String> technologies);

    List<Product> getFilteredByMarkets(
            List<String> markets);
}
