package com.home.catalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Setter
@Getter
@Entity
@TypeDefs({
        @TypeDef(
                name = "int-array",
                typeClass = IntArrayType.class
        )
})
public class SearchIndex {

    @Id
    public Long gid;

    @JsonIgnore
    @Type(type = "int-array")
    @Column(columnDefinition = "int[]")
    private Integer[] tags;
}
