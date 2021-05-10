package com.eugeniojava.backend.service;

import com.eugeniojava.backend.model.Market;
import com.eugeniojava.backend.model.Product;
import com.eugeniojava.backend.model.Technology;
import com.eugeniojava.backend.repository.stub.InMemoryProductRepository;
import com.eugeniojava.backend.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

public class ProductServiceTest {

    private final InMemoryProductRepository inMemoryProductRepository = new InMemoryProductRepository();

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(inMemoryProductRepository);
    }

    @Test
    public void shouldListAllProducts() {
        //given
        var product = createMockProduct("JavaConsulting", "Consultoria em java");
        var product2 = createMockProduct("JavaConsulting2", "Consultoria em java2");
        inMemoryProductRepository.save(product);
        inMemoryProductRepository.save(product2);
        //when
        var listOfProducts = productService.listAll();
        //then
        Assertions.assertEquals(listOfProducts, List.of(product, product2));
    }

    @Test
    public void shouldListAllByFilterMarket() {
        //given
        var product = createMockProduct("JavaConsulting", "Consultoria em java");
        var product2 = createMockProduct("JavaConsulting2", "Consultoria em java2");
        var brasil = createMarket("Brasil");
        //somente o produto um tem Brasil
        product.setMarkets(List.of(brasil));
        inMemoryProductRepository.save(product);
        inMemoryProductRepository.save(product2);
        //when
        var listOfProducts = productService.getFilteredByMarkets(List.of("Brasil"));
        //then
        Assertions.assertEquals(listOfProducts, List.of(product));
    }

    @Test
    public void shouldListAllByFilterTech() {
        //given
        var product = createMockProduct("JavaConsulting", "Consultoria em java");
        var product2 = createMockProduct("JavaConsulting2", "Consultoria em java2");
        var java = createTechonology("Java");
        //somente o produto 2 tem java
        product2.setTechnologies(List.of(java));
        inMemoryProductRepository.save(product);
        inMemoryProductRepository.save(product2);
        //when
        var listOfProducts = productService.getFilteredByTechnologies(List.of("Java"));
        //then
        Assertions.assertEquals(listOfProducts, List.of(product2));
    }

    @Test
    public void shouldListEmptyWhenNotFoundTech() {
        //given
        var product = createMockProduct("JavaConsulting", "Consultoria em java");
        var product2 = createMockProduct("JavaConsulting2", "Consultoria em java2");
        var java = createTechonology("Java");
        //somente o produto 2 tem java
        product2.setTechnologies(List.of(java));
        inMemoryProductRepository.save(product);
        inMemoryProductRepository.save(product2);
        //when
        var listOfProducts = productService.getFilteredByTechnologies(List.of("JavaRenato"));
        //then
        Assertions.assertTrue(listOfProducts.isEmpty());
    }

    @Test
    public void shouldListEmptyWhenNotFoundMarket() {
        //given
        var product = createMockProduct("JavaConsulting", "Consultoria em java");
        var product2 = createMockProduct("JavaConsulting2", "Consultoria em java2");
        var teste = createMarket("Teste");
        //somente o produto 2 tem java
        product2.setMarkets(List.of(teste));
        inMemoryProductRepository.save(product);
        inMemoryProductRepository.save(product2);
        //when
        var listOfProducts = productService.getFilteredByMarkets(List.of("TestandoValor"));
        //then
        Assertions.assertTrue(listOfProducts.isEmpty());
    }



    private static Market createMarket(String name) {
        Market market = new Market();
        market.setId((long) Math.abs(UUID.randomUUID().toString().hashCode()));
        market.setName(name);
        return market;
    }

    private static Technology createTechonology(String name) {
        Technology technology = new Technology();
        technology.setId((long) Math.abs(UUID.randomUUID().toString().hashCode()));
        technology.setName(name);
        return technology;
    }

    private static Product createMockProduct(String name,
                                             String desc) {
        Product product1 = new Product();
        product1.setId((long) Math.abs(UUID.randomUUID().toString().hashCode()));
        product1.setName(name);
        product1.setDescription(desc);
        return product1;
    }

}
