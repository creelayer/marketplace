package com.home.catalog.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class TagDto {

    @NotNull(message = "Id can't be blank")
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String url;

    @NotNull
    private VocabularyDto vocabulary;

}
