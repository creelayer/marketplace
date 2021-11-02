package com.home.catalog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Vocabulary extends BaseEntity {

    public final static int CATEGORY_ID = 1;

    @Id
    private int id;

    @Basic
    private String name;
}
