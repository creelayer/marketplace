package com.home.catalog.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @NotNull(message = "GID can't be null")
    public Integer gid;

    @JsonIgnore
    public Integer marketId = 1;

    @NotNull
    private String name;

    @NotNull
    private String url;

    @Valid
    private Price[] prices;

    private List<@Pattern(regexp = "^.*\\.(jpg|jpeg)$", message = "Only jpg type can be load") String> preview;

    @Valid
    private TagDto[] tags;

    @JsonAlias("short_case")
    @Valid
    private ShortCaseDto[] shortCase;

    @Setter
    @Getter
    public static class Price {
        @JsonAlias("entity_id")
        private String entityId;
        private int value;
    }

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


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductDto other = (ProductDto) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

}
