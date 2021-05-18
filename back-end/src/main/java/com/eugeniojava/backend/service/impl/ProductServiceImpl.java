package com.eugeniojava.backend.service.impl;

import com.eugeniojava.backend.model.Product;
import com.eugeniojava.backend.repository.JpaProductRepository;
import com.eugeniojava.backend.repository.ProductRepository;
import com.eugeniojava.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//TODO servico anemico
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getFilteredByTechnologies(List<String> technologies) {
        return productRepository.findByTechnologiesNameIn(technologies);
    }

    @Override
    public List<Product> getFilteredByMarkets(List<String> markets) {
        return productRepository.findByMarketsNameIn(markets);
    }
}
