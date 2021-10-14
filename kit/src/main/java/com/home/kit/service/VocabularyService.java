package com.home.kit.service;

import com.home.kit.entity.Vocabulary;
import com.home.kit.repository.VocabularyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VocabularyService  {


    @Value("${kit.category.default.name}")
    private String defaultCategoryName;

    private final VocabularyRepository vocabularyRepository;

    public Page<Vocabulary> search(Pageable pageable) {
        return vocabularyRepository.findAll(pageable);
    }

    public void createDefault() {
        vocabularyRepository.findById(Vocabulary.CATEGORY_ID).orElseGet(() -> {
            Vocabulary vocabulary = new Vocabulary();
            vocabulary.setName(defaultCategoryName);
            vocabulary.setSystem(true);
            return vocabularyRepository.save(vocabulary);
        });
    }

    public Vocabulary save(Vocabulary vocabulary) {
        return vocabularyRepository.save(vocabulary);
    }

    public void delete(Vocabulary vocabulary) {
        vocabularyRepository.delete(vocabulary);
    }
}
