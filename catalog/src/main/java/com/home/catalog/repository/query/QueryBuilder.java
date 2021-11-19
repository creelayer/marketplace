package com.home.catalog.repository.query;

import com.home.catalog.entity.Tag;

import java.util.*;

public abstract class QueryBuilder {

    protected Map<Integer, List<Integer>> getVocabularyIndexedTags(Set<Tag> tags) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (Tag tag : tags) {
            if (!map.containsKey(tag.getVocabulary().getId())) {
                map.put(tag.getVocabulary().getId(), new ArrayList<>());
            }
            map.get(tag.getVocabulary().getId()).add(tag.getId());
        }
        return map;
    }

}
