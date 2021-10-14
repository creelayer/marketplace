package com.home.kit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
@Entity
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class Tag extends BaseEntity {

    @Id
    @GeneratedValue
    private int id;

    @Basic(optional = false)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Vocabulary vocabulary;

    @ManyToOne(fetch=FetchType.EAGER)
    private Tag parent;

    @JsonIgnore
    @OneToMany(mappedBy = "parent")
    private List<Tag> children;

}
