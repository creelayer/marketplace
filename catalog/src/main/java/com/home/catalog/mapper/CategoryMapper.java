package com.home.catalog.mapper;

import com.home.catalog.dto.CategoryDto;
import com.home.catalog.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    Category map(CategoryDto dto);
    @Mapping(target = "id", ignore = true)
    Category map(CategoryDto dto, @MappingTarget Category category);
}
