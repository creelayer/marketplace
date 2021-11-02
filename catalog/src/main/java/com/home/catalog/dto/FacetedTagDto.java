package com.home.catalog.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FacetedTagDto {
    private int id;
    private String name;
    private String group;
    private int count;
}
