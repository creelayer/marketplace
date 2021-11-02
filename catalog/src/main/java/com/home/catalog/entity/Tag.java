package com.home.catalog.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.home.catalog.view.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class Tag extends BaseEntity {

    @JsonView(Category.Public.class)
    @Id
    private int id;

    @JsonView(Category.Public.class)
    @Basic
    private String name;

    @JsonView(Category.Public.class)
    @Basic
    private String url;

    @JsonView(Category.Public.class)
    @Basic
    private int weight;

    @ManyToOne
    private Tag parent;

    @ManyToOne
    private Vocabulary vocabulary;

    @Basic(optional = false)
    private String vocabularyName;
}
