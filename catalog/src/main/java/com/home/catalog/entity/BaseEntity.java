package com.home.catalog.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Setter
    @Getter
    @CreationTimestamp
    @Basic(optional = false)
    protected LocalDateTime createdAt;

    @Getter
    @Setter
    @UpdateTimestamp
    @Basic(optional = false)
    protected LocalDateTime updatedAt;
}
