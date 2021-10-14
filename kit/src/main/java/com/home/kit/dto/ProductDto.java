package com.home.kit.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.home.kit.annotation.Constraint;
import com.home.kit.constraint.TagExistConstraint;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
public class ProductDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @JsonAlias("category_ids")
    @Constraint(value = TagExistConstraint.class, message = "Category not exist")
    @NotNull
    private Integer[] categoryIds;

    @Valid
    private List<KitDto> kits;

}
