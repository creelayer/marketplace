package com.home.kit.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Vocabulary extends BaseEntity {

    public final static int CATEGORY_ID = 1;

    @Id
    @GeneratedValue
    private int id;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private boolean system = false;

}
