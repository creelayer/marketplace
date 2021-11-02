package com.home.catalog.service;

import com.home.catalog.dto.TagDto;
import com.home.catalog.entity.Tag;
import com.home.catalog.mapper.TagMapper;
import com.home.catalog.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class TagService {

    private ConcurrentHashMap<Integer, Tag> tagCache = new ConcurrentHashMap<>();

    private final TagRepository tagRepository;
    private final TagMapper mapper;
    private final VocabularyService vocabularyService;

    public Optional<Tag> findByUrl(String url){
        return tagRepository.findByUrl(url);
    }

    public Tag upsert(TagDto dto) {
        return tagCache.computeIfAbsent(
                dto.getId(),
                id -> tagRepository.findById(id).orElseGet(() -> {
                    Tag tag = mapper.map(dto);
                    tag.setVocabulary(vocabularyService.upsert(tag.getVocabulary()));
                    return tagRepository.save(tag);
                }));
    }

}
