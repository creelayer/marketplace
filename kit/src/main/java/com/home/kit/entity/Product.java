package com.home.kit.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kit> kits = new ArrayList<>();

    @ManyToMany
    private List<Sla> slas = new ArrayList<>();

    @OneToMany
    private List<Price> prices;
}
