package com.home.catalog.service;

import com.home.catalog.dto.CatalogFilter;
import com.home.catalog.dto.FacetedTagDto;
import com.home.catalog.entity.Vocabulary;
import com.home.catalog.repository.impl.TagRepositoryImpl;
import com.home.catalog.repository.VocabularyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FilterService {

    private static final int VOCABULARY_CATEGORY_ID = 1;

    private TagRepositoryImpl catalogRepository;
    private VocabularyRepository vocabularyRepository;

    public List<FacetedTagDto> filter(CatalogFilter filter) {
        List<Vocabulary> vocabularies = vocabularyRepository.findAll();
        return catalogRepository.filter(filter, vocabularies
                .stream()
                .filter(v -> v.getId() != VOCABULARY_CATEGORY_ID).
                collect(Collectors.toList())
        );
    }
}
