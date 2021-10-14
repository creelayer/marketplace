package com.home.delivery.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Account extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID uid;

    @ManyToOne
    private Delivery delivery;

}
