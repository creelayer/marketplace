package com.home.catalog.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Category extends BaseEntity {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String url;

    private int weight;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<Group> groups;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Group implements Serializable {
        private String Name;
        private String url;
        private List<Item> items;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Item implements Serializable {
            private String Name;
            private String url;
        }
    }
}
