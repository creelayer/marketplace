package com.home.kit.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.home.kit.annotation.Constraint;
import com.home.kit.constraint.TagExistConstraint;
import com.home.kit.constraint.VocabularyExistConstraint;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class CategoryDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @JsonAlias("vocabulary_id")
    @Constraint(value = VocabularyExistConstraint.class, message = "Vocabulary not exist")
    @NotNull
    @Min(value = 1)
    private Integer vocabularyId;

    @JsonAlias("parent_id")
    @Constraint(skipOnEmpty = true, value = TagExistConstraint.class, message = "Parent category doesn't exist")
    @Min(value = 1)
    private Integer parentId;

}
