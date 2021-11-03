package com.home.catalog.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Setter
@Getter
public class ProductDto {

    @NotNull(message = "Id can't be null")
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String url;

    @Pattern(regexp = "^.*\\.(jpg)$", message = "Only jpg type can be load")
    private String image;

    private List<@Pattern(regexp = "^.*\\.(jpg)$", message = "Only jpg type can be load") String> images;

    @Valid
    private TagDto[] tags;

    @JsonAlias("short_case")
    @Valid
    private ShortCaseDto[] shortCase;

    @Setter
    @Getter
    public static class ShortCaseDto {

        @NotNull(message = "Label can't be empty")
        private String label;

        @NotNull(message = "Value can't be empty")
        private String value;

        private Integer icon;
    }

    @Setter
    @Getter
    public static class TagDto {

        @NotNull(message = "Id can't be blank")
        private Integer id;

        @NotNull
        private String name;

        @NotNull
        private String url;

        @NotNull
        private VocabularyDto vocabulary;

        @Setter
        @Getter
        public static class VocabularyDto {

            @NotNull(message = "Id can't be blank")
            private Integer id;

            @NotNull
            private String name;
        }

    }
}
