package com.eugeniojava.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "technology_table")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technology_id")
    private Long id;

    @Getter
    @Column(name = "technology_name")
    private String name;

    @ManyToMany(mappedBy = "technologies")
    private List<Product> products;
}
