package com.home.catalog.repository.impl;

import com.home.catalog.dto.CatalogFilter;
import com.home.catalog.dto.FacetedTagDto;
import com.home.catalog.entity.*;
import com.home.catalog.mapper.impl.FacetedTagMapper;
import com.home.catalog.repository.FacetedFilter;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Repository
public class TagRepositoryImpl implements FacetedFilter {

    private NamedParameterJdbcTemplate jdbc;

    public List<FacetedTagDto> filter(CatalogFilter filter, List<Vocabulary> vocabularies) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (Tag tag : filter.getTags()) {
            if (!map.containsKey(tag.getVocabulary().getId())) {
                map.put(tag.getVocabulary().getId(), new ArrayList<>());
            }
            map.get(tag.getVocabulary().getId()).add(tag.getId());
        }

        StringBuilder query = new StringBuilder();
        for (Vocabulary vocabulary : vocabularies) {

            if (query.length() > 0) {
                query.append(" UNION ");
            }

            query.append(" SELECT t.id, t.name, t.vocabulary_name, COUNT(t.id) count FROM tag t");
            query.append(" INNER JOIN product_tag pt0_ ON pt0_.tag_id = t.id");
            query.append(" INNER JOIN product_tag pt1_ ON pt1_.product_id = pt0_.product_id");


            for (var entry : map.entrySet()) {
                if (entry.getKey() == vocabulary.getId()) {
                    continue;
                }
                query.append(" INNER JOIN product_tag pt")
                        .append(entry.getKey())
                        .append("_ ON pt")
                        .append(entry.getKey())
                        .append("_.product_id = pt0_.product_id");
            }


            query.append(" WHERE pt0_.vocabulary_id = :vid").append(vocabulary.getId());
            query.append(" AND pt1_.tag_id = :cid");


            for (var entry : map.entrySet()) {
                if (entry.getKey() == vocabulary.getId()) {
                    continue;
                }
                query.append(" AND pt").append(entry.getKey()).append("_.tag_id in (:tags").append(entry.getKey()).append(")");
                parameters.addValue("tags" + entry.getKey(), entry.getValue());
            }


            query.append(" GROUP BY t.id");

            parameters
                    .addValue("cid", filter.getCategory().getId())
                    .addValue("vid" + vocabulary.getId(), vocabulary.getId());


        }

        System.out.println(query.toString());

        return jdbc.query(query.toString(), parameters, new FacetedTagMapper());
    }
}
