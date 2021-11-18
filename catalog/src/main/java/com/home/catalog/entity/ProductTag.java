package com.home.catalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(indexes = {
        @Index(name = "idx_i_product_id", columnList = "product_id"),
        @Index(name = "idx_i_tag_id", columnList = "tag_id"),
})
public class ProductTag {

    @JsonIgnore
    @EmbeddedId
    public TagIndexKey id;

    @ManyToOne
    @MapsId("tagId")
    Tag tag;

    @JsonIgnore
    @ManyToOne
    @MapsId("productId")
    Product product;

    @JsonIgnore
    @Basic
    public int vocabularyId;

    @Embeddable
    private static class TagIndexKey implements Serializable {
        public long productId;
        public int tagId;
    }
}
