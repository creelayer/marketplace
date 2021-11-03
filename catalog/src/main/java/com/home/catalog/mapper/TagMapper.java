package com.home.catalog.mapper;

import com.home.catalog.dto.ProductDto;
import com.home.catalog.entity.Tag;
import com.home.catalog.entity.Vocabulary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    @Mapping( target = "id", source = "id")
    @Mapping(target = "vocabularyName", source = "vocabulary.name")
    Tag map(ProductDto.TagDto dto);

    @Mapping( target = "id", source = "id")
    @Mapping(target = "vocabularyName", source = "vocabulary.name")
    Tag map(ProductDto.TagDto dto, @MappingTarget Tag tag);

    Vocabulary map(ProductDto.TagDto.VocabularyDto dto);
    Vocabulary map(ProductDto.TagDto.VocabularyDto dto, @MappingTarget Vocabulary vocabulary);

}
