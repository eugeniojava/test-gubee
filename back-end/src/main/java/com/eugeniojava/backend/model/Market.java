package com.eugeniojava.backend.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "market_table")
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_id")
    private Long id;

    @Getter
    @Column(name = "market_name")
    private String name;

    @ManyToMany(mappedBy = "markets")
    private List<Product> products;
}
