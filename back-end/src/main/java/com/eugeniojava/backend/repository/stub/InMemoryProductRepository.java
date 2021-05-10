package com.eugeniojava.backend.repository.stub;

import com.eugeniojava.backend.model.Market;
import com.eugeniojava.backend.model.Product;
import com.eugeniojava.backend.model.Technology;
import com.eugeniojava.backend.repository.ProductRepository;

import java.util.Arrays;
import java.util.List;

public class InMemoryProductRepository implements ProductRepository {

    private static List<Market> getMarkets() {
        Market market1 = new Market();
        market1.setId(1L);
        market1.setName("Market 1");

        Market market2 = new Market();
        market2.setId(2L);
        market2.setName("Market 2");

        Market market3 = new Market();
        market3.setId(3L);
        market3.setName("Market 3");

        return Arrays.asList(market1, market2, market3);
    }

    private static List<Technology> getTechnologies() {
        Technology technology1 = new Technology();
        technology1.setId(1L);
        technology1.setName("Technology 1");

        Technology technology2 = new Technology();
        technology2.setId(2L);
        technology2.setName("Technology 2");

        Technology technology3 = new Technology();
        technology3.setId(3L);
        technology3.setName("Technology 3");

        return Arrays.asList(technology1, technology2, technology3);
    }

    private static List<Product> getProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setDescription("Description 1");
        product1.setMarkets(getMarkets());
        product1.setTechnologies(getTechnologies());

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setDescription("Description 2");
        product2.setMarkets(getMarkets());
        product2.setTechnologies(getTechnologies());

        Product product3 = new Product();
        product3.setId(3L);
        product3.setName("Product 3");
        product3.setDescription("Description 3");
        product3.setMarkets(getMarkets());
        product3.setTechnologies(getTechnologies());

        return Arrays.asList(product1, product2, product3);
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = getProducts();

        products.forEach(p -> {
            p.getMarkets().forEach(m -> {
                m.setProducts(products);
            });
            p.getTechnologies().forEach(t -> {
                t.setProducts(products);
            });
        });

        return products;
    }

    @Override
    public List<Product> findByTechnologiesNameIn(List<String> technologies) {
        return null;
    }

    @Override
    public List<Product> findByMarketsNameIn(List<String> markets) {
        return null;
    }
}
