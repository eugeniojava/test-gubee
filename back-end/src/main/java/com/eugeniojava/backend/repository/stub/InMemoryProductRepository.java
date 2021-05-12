package com.eugeniojava.backend.repository.stub;

import com.eugeniojava.backend.model.Market;
import com.eugeniojava.backend.model.Product;
import com.eugeniojava.backend.model.Technology;
import com.eugeniojava.backend.repository.ProductRepository;

import java.util.*;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;
import static java.util.stream.Collectors.*;

public class InMemoryProductRepository implements ProductRepository {

    private final Map<Long, Product> inMemoryDb = new HashMap<>();

    @Override
    public Optional<Product> findById(long id) {
        return Optional.ofNullable(this.inMemoryDb.get(id));
    }

    @Override
    public Product save(Product product) {
        requireNonNull(product);
        var id = requireNonNullElse(product.getId(), nextVal());

        inMemoryDb.put(id, product);

        return product;
    }

    @Override
    public Product insert(Product product) {
        requireNonNull(product);
        var id = requireNonNullElse(product.getId(), nextVal());

        if (inMemoryDb.containsKey(id))
            throw new IllegalArgumentException("Use the save method to update an item.");
        inMemoryDb.put(id, product);

        return product;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(this.inMemoryDb.values());
    }

    @Override
    public List<Product> findByTechnologiesNameIn(List<String> listOfTech) {
        var technologies = this.inMemoryDb
                .values()
                .stream()
                .filter(p -> p.getTechnologies() != null && !p.getTechnologies().isEmpty())
                .collect(toMap(
                        Function.identity(),
                        p -> p.getTechnologies().stream().map(Technology::getName).collect(toSet())));

        return technologies
                .entrySet()
                .stream()
                .filter(p -> listOfTech.stream().anyMatch(l -> p.getValue().contains(l)))
                .map(Map.Entry::getKey)
                .collect(toList());
    }

    @Override
    public List<Product> findByMarketsNameIn(List<String> markets) {
        var market = this.inMemoryDb
                .values()
                .stream()
                .filter(p -> p.getMarkets() != null && !p.getMarkets().isEmpty())
                .collect(toMap(
                        Function.identity(), p -> p.getMarkets().stream().map(Market::getName).collect(toSet())));

        return market
                .entrySet()
                .stream()
                .filter(p -> markets.stream().anyMatch(l -> p.getValue().contains(l)))
                .map(Map.Entry::getKey)
                .collect(toList());
    }

    private long nextVal() {
        return this.inMemoryDb
                .values()
                .stream()
                .mapToLong(Product::getId)
                .max()
                .orElse(1);
    }
}
