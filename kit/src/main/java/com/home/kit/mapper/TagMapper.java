package com.home.kit.mapper;

import com.home.kit.dto.TagDto;
import com.home.kit.entity.Tag;
import com.home.kit.repository.TagRepository;
import com.home.kit.repository.VocabularyRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {VocabularyRepository.class, TagRepository.class})
public interface TagMapper {

    @Mapping(source = "vocabularyId", target = "vocabulary")
    @Mapping(source = "parentId", target = "parent")
    Tag map(TagDto dto);

    TagDto map(Tag tag);

    @Mapping(source = "parentId", target = "parent")
    Tag map(TagDto dto, @MappingTarget Tag tag);
}
