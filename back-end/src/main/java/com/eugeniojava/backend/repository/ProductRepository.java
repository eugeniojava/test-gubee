package com.eugeniojava.backend.repository;

import com.eugeniojava.backend.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    List<Product> findByTechnologiesNameIn(List<String> technologies);

    List<Product> findByMarketsNameIn(List<String> markets);
}
