package com.home.payment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID uid;

    @Basic
    private String name;

    @Basic
    private boolean active = true;
}
