package com.home.catalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
//@Table(indexes = {
//        @Index(name = "idx_i_product_id", columnList = "productId"),
//        @Index(name = "idx_i_tag_id", columnList = "tagId"),
//        @Index(name = "idx_i_vocabulary_id", columnList = "vocabularyId")
//})
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
        public int productId;
        public int tagId;
    }
}
