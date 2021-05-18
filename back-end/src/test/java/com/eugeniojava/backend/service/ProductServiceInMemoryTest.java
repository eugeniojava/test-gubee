//package com.eugeniojava.backend.service;
//
//import com.eugeniojava.backend.model.Market;
//import com.eugeniojava.backend.model.Product;
//import com.eugeniojava.backend.model.Technology;
//import com.eugeniojava.backend.repository.stub.InMemoryProductRepository;
//import com.eugeniojava.backend.service.impl.ProductServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//import java.util.UUID;
//
//class ProductServiceInMemoryTest {
//
//    private final InMemoryProductRepository repository = new InMemoryProductRepository();
//
//    private ProductService service;
//
//    @BeforeEach
//    void setUp() {
//        service = new ProductServiceImpl(repository);
//    }
//
//    @Test
//    void shouldListAllProducts() {
//        //given
//        var product1 = createMockProduct("Java Consulting", "Consultoria em Java");
//        var product2 = createMockProduct("Java Consulting 2", "Consultoria em Java 2");
//        repository.save(product1);
//        repository.save(product2);
//
//        //when
//        var listOfProducts = service.listAll();
//
//        //then
//        Assertions.assertEquals(listOfProducts, List.of(product1, product2));
//    }
//
//    @Test
//    void shouldListAllByFilterMarket() {
//        //given
//        var product1 = createMockProduct("Java Consulting", "Consultoria em Java");
//        var product2 = createMockProduct("Java Consulting 2", "Consultoria em Java 2");
//        var market = createMarket("Brasil");
//        product1.setMarkets(List.of(market));
//        repository.save(product1);
//        repository.save(product2);
//
//        //when
//        var listOfProducts = service.getFilteredByMarkets(List.of("Brasil"));
//
//        //then
//        Assertions.assertEquals(listOfProducts, List.of(product1));
//    }
//
//    @Test
//    void shouldListAllByFilterTech() {
//        //given
//        var product1 = createMockProduct("Java Consulting", "Consultoria em Java");
//        var product2 = createMockProduct("Java Consulting 2", "Consultoria em Java 2");
//        var technology = createTechnology("Java");
//        product2.setTechnologies(List.of(technology));
//        repository.save(product1);
//        repository.save(product2);
//
//        //when
//        var listOfProducts = service.getFilteredByTechnologies(List.of("Java"));
//
//        //then
//        Assertions.assertEquals(listOfProducts, List.of(product2));
//    }
//
//    @Test
//    void shouldListEmptyWhenNotFoundTech() {
//        //given
//        var product1 = createMockProduct("Java Consulting", "Consultoria em Java");
//        var product2 = createMockProduct("Java Consulting 2", "Consultoria em Java 2");
//        var technology = createTechnology("Java");
//        product2.setTechnologies(List.of(technology));
//        repository.save(product1);
//        repository.save(product2);
//
//        //when
//        var listOfProducts = service.getFilteredByTechnologies(List.of("JavaRenato"));
//
//        //then
//        Assertions.assertTrue(listOfProducts.isEmpty());
//    }
//
//    @Test
//    void shouldListEmptyWhenNotFoundMarket() {
//        //given
//        var product1 = createMockProduct("Java Consulting", "Consultoria em Java");
//        var product2 = createMockProduct("Java Consulting 2", "Consultoria em Java 2");
//        var market = createMarket("Teste");
//        product2.setMarkets(List.of(market));
//        repository.save(product1);
//        repository.save(product2);
//
//        //when
//        var listOfProducts = service.getFilteredByMarkets(List.of("TestandoValor"));
//
//        //then
//        Assertions.assertTrue(listOfProducts.isEmpty());
//    }
//
//    private static Market createMarket(String name) {
//        Market market = new Market();
//        market.setId((long) Math.abs(UUID.randomUUID().toString().hashCode()));
//        market.setName(name);
//
//        return market;
//    }
//
//    private static Technology createTechnology(String name) {
//        Technology technology = new Technology();
//        technology.setId((long) Math.abs(UUID.randomUUID().toString().hashCode()));
//        technology.setName(name);
//
//        return technology;
//    }
//
//    private static Product createMockProduct(String name, String description) {
//        Product product = new Product();
//        product.setId((long) Math.abs(UUID.randomUUID().toString().hashCode()));
//        product.setName(name);
//        product.setDescription(description);
//
//        return product;
//    }
//}
