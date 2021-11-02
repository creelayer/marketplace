package com.home.catalog.repository;

import com.home.catalog.entity.Vocabulary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VocabularyRepository extends CrudRepository<Vocabulary, Integer> {
    List<Vocabulary> findAll();
}
