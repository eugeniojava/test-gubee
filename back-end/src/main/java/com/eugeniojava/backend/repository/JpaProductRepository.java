package com.eugeniojava.backend.repository;

import com.eugeniojava.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaProductRepository extends ProductRepository,
        JpaRepository<Product, Long> {

    @Override
    @Query("SELECT p FROM Product p JOIN p.technologies t WHERE t.name IN " +
            "(:technologies) GROUP BY p")
    List<Product> findByTechnologiesNameIn(List<String> technologies);

    @Override
    @Query("SELECT p FROM Product p JOIN p.markets m WHERE m.name IN " +
            "(:markets) GROUP BY p")
    List<Product> findByMarketsNameIn(List<String> markets);
}
