package com.eugeniojava.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product_table")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "market_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "market_id"))
    private List<Market> markets;

    @ManyToMany
    @JoinTable(
            name = "technology_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private List<Technology> technologies;
}
