package com.home.catalog.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Setter
@Getter
public class ProductDto {

    @NotNull(message = "Id can't be null")
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String url;

    @Valid
    private TagDto[] tags;

    public int[] getTagIds() {
        return Arrays.stream(tags).mapToInt(TagDto::getId).toArray();
    }
}
