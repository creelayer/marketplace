package com.home.catalog.service;

import com.home.catalog.entity.Tag;
import com.home.catalog.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class TagService {

    private final ConcurrentHashMap<Integer, Tag> tagCache = new ConcurrentHashMap<>();

    private final TagRepository tagRepository;
    private final VocabularyService vocabularyService;

    public Optional<Tag> findByUrl(String url){
        return tagRepository.findByUrl(url);
    }

    public Tag upsert(Tag tag) {
        return tagCache.computeIfAbsent(
                tag.getId(),
                id -> tagRepository.findById(id).orElseGet(() -> {
                    tag.setVocabulary(vocabularyService.upsert(tag.getVocabulary()));
                    return tagRepository.save(tag);
                }));
    }

}
