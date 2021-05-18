package com.eugeniojava.backend.repository;

import com.eugeniojava.backend.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryImpl {

    Optional<Product> findById(long id);

    Product save(Product product);

    Product insert(Product product);

    List<Product> findAll();

    List<Product> findByTechnologiesNameIn(List<String> technologies);

    List<Product> findByMarketsNameIn(List<String> markets);
}
