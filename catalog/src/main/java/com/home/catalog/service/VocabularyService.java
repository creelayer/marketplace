package com.home.catalog.service;

import com.home.catalog.entity.Vocabulary;
import com.home.catalog.repository.VocabularyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VocabularyService {

    private final VocabularyRepository vocabularyRepository;

    public Vocabulary upsert(Vocabulary entity) {
        return vocabularyRepository.findById(entity.getId()).orElseGet(()
                -> vocabularyRepository.save(entity));
    }
}
