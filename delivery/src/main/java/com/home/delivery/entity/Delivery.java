package com.home.delivery.entity;

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
public class Delivery extends BaseEntity {

    public enum TYPE {
        COURIER, PICKUP
    }

    @Id
    private UUID uid = UUID.randomUUID();

    @Basic(optional = false)
    private String name;

    @Basic
    private boolean active = true;
}
