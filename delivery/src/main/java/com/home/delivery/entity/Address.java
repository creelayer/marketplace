package com.home.delivery.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Setter
@Getter
@Accessors(chain = true)
@Entity
public class Address extends BaseEntity {

    @Id
    @GeneratedValue
    private int id;

    @Basic(optional = false)
    private String address;
}
