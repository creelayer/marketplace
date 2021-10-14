package com.home.kit.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Setter
@Getter
@Entity
public class Price extends BaseEntity {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private Market market;

}
