package com.home.catalog.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.home.catalog.view.ProductView;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@TypeDefs({
        @TypeDef(
                name = "string-array",
                typeClass = ListArrayType.class
        ),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Table(indexes = {
//        @Index(name = "idx_p_g", columnList = "gid"),
//        @Index(name = "idx_e", columnList = "egId, marketId, eId", unique = true),
})
public class Product extends BaseEntity {

    @Id
    public long id;

    @Basic
    public Long gid;

    @Column(unique = true)
    public String name;

    @Basic
    public String url;

    @Type(type = "string-array")
    @Column(columnDefinition = "varchar[]")
    public List<String> preview;

    @JsonView(ProductView.View.class)
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Set<Price> prices;

    @Basic
    private Integer priceMin;

    @Basic
    private Integer priceMax;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<ShortCase> shortCase;

    @JsonView(ProductView.View.class)
    @OneToMany(targetEntity = Tag.class, mappedBy = "id")
    Set<Tag> tags;

    @Setter
    @Getter
    public static class Price implements Serializable {
        private String entityId;
        private int value;
    }

    @Setter
    @Getter
    public static class ShortCase implements Serializable {
        private String label;
        private String value;
        private Integer icon;
    }
}
