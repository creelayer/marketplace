package com.home.kit.mapper;

import com.home.kit.dto.CategoryDto;
import com.home.kit.entity.Category;
import com.home.kit.repository.CategoryRepository;
import com.home.kit.repository.VocabularyRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {VocabularyRepository.class, CategoryRepository.class})
public interface CategoryMapper {

    @Mapping(source = "vocabularyId", target = "vocabulary")
    @Mapping(source = "parentId", target = "parent")
    Category map(CategoryDto dto);

    CategoryDto map(Category category);

    @Mapping(source = "parentId", target = "parent")
    Category map(CategoryDto dto, @MappingTarget Category category);

}
