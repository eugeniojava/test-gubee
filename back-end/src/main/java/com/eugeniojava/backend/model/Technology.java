package com.eugeniojava.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "technology_table")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technology_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Getter
    @Column(name = "technology_name")
    private String name;

    @ManyToMany(mappedBy = "technologies")
    private List<Product> products;
}
