package com.home.catalog.dto;

import com.home.catalog.entity.Tag;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class CatalogFilter {
    private Tag category;
    private Set<@NotNull Tag> tags = new HashSet<>();

    private int page = 0;
    private int size = 50;
}
