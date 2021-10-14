package com.home.kit.repository;

import com.home.kit.entity.Vocabulary;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocabularyRepository extends PagingAndSortingRepository<Vocabulary, Integer> {
    Vocabulary readById(int id);
}
