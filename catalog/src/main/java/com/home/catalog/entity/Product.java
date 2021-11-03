package com.home.catalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.home.catalog.view.ProductView;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

import com.vladmihalcea.hibernate.type.array.IntArrayType;

@Setter
@Getter
@Entity
@TypeDefs({
        @TypeDef(
                name = "int-array",
                typeClass = IntArrayType.class
        ),
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        ),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
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

    @Basic
    public String image;

    @JsonView(ProductView.View.class)
    @Type(type = "string-array")
    @Column(columnDefinition = "varchar[]")
    public Set<String> images;

    @JsonIgnore
    @Type(type = "int-array")
    @Column(columnDefinition = "int[]")
    private Integer[] index;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Set<ShortCase> shortCase;

    @JsonView(ProductView.View.class)
    @OneToMany(targetEntity = Tag.class, mappedBy = "id")
    Set<Tag> tags;

    @Setter
    @Getter
    public static class ShortCase implements Serializable {
        private String label;
        private String value;
        private Integer icon;
    }
}
