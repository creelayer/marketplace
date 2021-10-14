package com.home.kit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class SlaCombination extends BaseEntity  {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @JsonIgnore
    @ManyToOne
    private Sla sla;

    @ManyToOne
    private Delivery delivery;

    @ManyToMany
    private List<Payment> payments;

}
