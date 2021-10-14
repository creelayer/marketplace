package com.home.kit.mapper;

import com.home.kit.dto.VocabularyDto;
import com.home.kit.entity.Vocabulary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VocabularyMapper {
    Vocabulary map(VocabularyDto dto);
    VocabularyDto map(Vocabulary vocabulary);
    Vocabulary map(VocabularyDto dto, @MappingTarget Vocabulary vocabulary);
}
