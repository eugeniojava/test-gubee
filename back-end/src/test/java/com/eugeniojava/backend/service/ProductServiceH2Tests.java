package com.eugeniojava.backend.service;

import com.eugeniojava.backend.model.Market;
import com.eugeniojava.backend.model.Product;
import com.eugeniojava.backend.model.Technology;
import com.eugeniojava.backend.repository.JpaProductRepository;
import com.eugeniojava.backend.repository.MarketRepository;
import com.eugeniojava.backend.repository.TechnologyRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceH2Tests {

    @Autowired
    private JpaProductRepository productRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void shouldFindById() {
        //given
        var product1 = createProduct(1L, "Product 1", "Description 1");
        productRepository.save(product1);

        //when
        var product = productService.findById(1L).orElse(null);

        //then
        Assertions.assertEquals(product1, product);
    }

    @Test
    public void shouldListAllProducts() {
        //given
        var product1 = createProduct(1L, "Product 1", "Description 1");
        var product2 = createProduct(2L, "Product 2", "Description 2");
        productRepository.save(product1);
        productRepository.save(product2);

        //when
        var listOfProducts = productService.listAll();

        //then
        Assertions.assertEquals(List.of(product1, product2), listOfProducts);
    }

    @Test
    public void shouldListAllFilteredByTechnologies() {
        //given
        var product1 = createProduct(1L, "Product 1", "Description 1");
        var product2 = createProduct(2L, "Product 2", "Description 2");
        var technology = createTechnology(1L, "Java", List.of(product2));
        productRepository.save(product1);
        productRepository.save(product2);
        technologyRepository.save(technology);
        product2.setTechnologies(List.of(technology));
        productRepository.save(product2);

        //when
        var listOfProducts = productService.getFilteredByTechnologies(List.of("Java"));

        //then
        Assertions.assertEquals(listOfProducts, List.of(product2));
    }

    @Test
    public void shouldListAllFilteredByMarkets() {
        //given
        var product1 = createProduct(1L, "Product 1", "Description 1");
        var product2 = createProduct(2L, "Product 2", "Description 2");
        var market = createMarket(1L, "Brazil", List.of(product2));
        productRepository.save(product1);
        productRepository.save(product2);
        marketRepository.save(market);
        product1.setMarkets(List.of(market));
        productRepository.save(product1);

        //when
        var listOfProducts = productService.getFilteredByMarkets(List.of("Brazil"));

        //then
        Assertions.assertEquals(listOfProducts, List.of(product1));
    }

    @Test
    public void shouldListEmptyWhenTechnologyNotFound() {
        //given
        var product1 = createProduct(1L, "Product 1", "Description 1");
        var product2 = createProduct(2L, "Product 2", "Description 2");
        var technology = createTechnology(1L, "Java", List.of(product2));
        productRepository.save(product1);
        productRepository.save(product2);
        technologyRepository.save(technology);
        product2.setTechnologies(List.of(technology));
        productRepository.save(product2);

        //when
        var listOfProducts = productService.getFilteredByTechnologies(List.of("Kotlin"));

        //then
        Assertions.assertTrue(listOfProducts.isEmpty());
    }

    @Test
    public void shouldListEmptyWhenMarketNotFound() {
        //given
        var product1 = createProduct(1L, "Product 1", "Description 1");
        var product2 = createProduct(2L, "Product 2", "Description 2");
        var market = createMarket(1L, "Brazil", List.of(product2));
        productRepository.save(product1);
        productRepository.save(product2);
        marketRepository.save(market);
        product1.setMarkets(List.of(market));
        productRepository.save(product1);

        //when
        var listOfProducts = productService.getFilteredByMarkets(List.of("Argentina"));

        //then
        Assertions.assertTrue(listOfProducts.isEmpty());
    }

    private static Market createMarket(Long id, String name, List<Product> products) {
        Market market = new Market();

        market.setId(id);
        market.setName(name);
        market.setProducts(products);

        return market;
    }

    private static Technology createTechnology(Long id, String name, List<Product> products) {
        Technology technology = new Technology();

        technology.setId(id);
        technology.setName(name);
        technology.setProducts(products);

        return technology;
    }

    private static Product createProduct(Long id, String name, String description) {
        Product product = new Product();

        product.setId(id);
        product.setName(name);
        product.setDescription(description);

        return product;
    }
}
