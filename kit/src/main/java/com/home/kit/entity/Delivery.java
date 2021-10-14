package com.home.kit.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Setter
@Getter
@Accessors(chain = true)
@Entity
public class Delivery extends BaseEntity  {

    @Id
    private UUID uid;

    @Basic(optional = false)
    private String name;
}
