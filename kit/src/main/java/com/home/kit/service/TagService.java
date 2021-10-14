package com.home.kit.service;

import com.home.kit.entity.Tag;
import com.home.kit.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class TagService {

    private TagRepository tagRepository;

    public Page<Tag> search(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    public Tag save(Tag tag) {
        if (tag.getVocabulary().isSystem()) {
            throw new IllegalStateException("Can\'t add tag in category vocabulary");
        }
        return tagRepository.save(tag);
    }

    public void delete(Tag tag) {
        if (!tag.getChildren().isEmpty()) {
            throw new IllegalStateException("Cant delete tag with children");
        }
        tagRepository.delete(tag);
    }
}
