package com.home.catalog.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Setter
@Getter
public class CategoryDto {

    @NotNull(message = "Category id can't be null")
    private Integer id;

    @NotNull(message = "Category name can't be null")
    private String name;

    @NotNull(message = "Category url can't be null")
    private String url;

    @Valid
    private List<Group> groups;

    @Data
    public static class Group implements Serializable {
        @NotNull(message = "Group name can't be null")
        private String Name;
        @NotNull(message = "Group url can't be null")
        private String url;

        @Valid
        private List<Item> items;

        @Data
        public static class Item implements Serializable {
            @NotNull(message = "Item name can't be null")
            private String Name;
            @NotNull(message = "Item url can't be null")
            private String url;
        }
    }

}
