package com.home.kit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Kit extends BaseEntity {

    @Id
    @GeneratedValue
    private int id;

    @Basic(optional = false)
    private String name;

    private Integer price;

    private Integer oldPrice;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Product product;

    @ManyToMany
    private List<Tag> tags = new ArrayList<>();

}
