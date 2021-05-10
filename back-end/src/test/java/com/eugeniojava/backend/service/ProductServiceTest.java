package com.eugeniojava.backend.service;

import com.eugeniojava.backend.model.Product;
import com.eugeniojava.backend.repository.stub.InMemoryProductRepository;
import com.eugeniojava.backend.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class ProductServiceTest {

    private InMemoryProductRepository inMemoryProductRepository =
            Mockito.mock(InMemoryProductRepository.class);

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(inMemoryProductRepository);
    }

    @Test
    public void shouldListAllProducts() {

    }
}
