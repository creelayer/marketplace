package com.home.kit.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
@Entity
public class Sla extends BaseEntity  {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToOne
    private Market market;

    @OneToMany(mappedBy = "sla")
    private List<SlaCombination> combinations = new ArrayList<>();
}
