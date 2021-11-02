package com.home.catalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import com.vladmihalcea.hibernate.type.array.IntArrayType;

@Setter
@Getter
@Entity
@TypeDefs({
        @TypeDef(
                name = "int-array",
                typeClass = IntArrayType.class
        )
})
@Table(indexes = {
        @Index(name = "idx_p_u", columnList = "url", unique = true),
})
public class Product extends BaseEntity {

    @Id
    public int id;

    @Basic
    public String name;

    @Basic
    public String url;

    @JsonIgnore
    @Type(type = "int-array")
    @Column(columnDefinition = "int[]")
    private Integer[] index;

    @OneToMany(targetEntity = Tag.class, mappedBy = "id")
    Set<Tag> tags;
}
