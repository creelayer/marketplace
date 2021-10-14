package com.home.payment.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Merchant extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID uid;

    @ManyToOne
    private Payment payment;
}
