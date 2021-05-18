package com.eugeniojava.backend.repository;

import com.eugeniojava.backend.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;

    @Override
    public Optional<Product> findById(long id) {
        return this.jpaProductRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return this.jpaProductRepository.save(product);
    }

    @Override
    public Product insert(Product product) {
        return this.jpaProductRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return this.jpaProductRepository.findAll();
    }

    @Override
    public List<Product> findByTechnologiesNameIn(List<String> technologies) {
        return this.jpaProductRepository.findByTechnologiesNameIn(technologies);
    }

    @Override
    public List<Product> findByMarketsNameIn(List<String> markets) {
        return null;
    }
}
