package com.home.kit.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.home.kit.annotation.Constraint;
import com.home.kit.constraint.TagExistConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class KitDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    private Integer price;

    @JsonAlias("old_price")
    private Integer oldPrice;

    @JsonAlias("tag_ids")
    @Constraint(skipOnEmpty = true, value = TagExistConstraint.class,  message = "Tag doesn't exist")
    private Integer[] tagIds;

}
