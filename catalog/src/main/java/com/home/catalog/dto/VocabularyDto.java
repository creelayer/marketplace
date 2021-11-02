package com.home.catalog.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class VocabularyDto {

    @NotNull(message = "Id can't be blank")
    private Integer id;

    @NotNull
    private String name;
}
