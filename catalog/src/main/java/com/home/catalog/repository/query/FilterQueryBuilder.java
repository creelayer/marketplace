package com.home.catalog.repository.query;

import com.home.catalog.entity.Tag;
import com.home.catalog.entity.Vocabulary;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.*;

@Setter
@Accessors(chain = true)
public class FilterQueryBuilder extends QueryBuilder {

    private Tag category;
    private List<Vocabulary> vocabularies;
    private Set<Tag> tags;

    public Query createQuery() {
        Query query = new Query(category)
                .setMap(getVocabularyIndexedTags(tags));

        for (Vocabulary vocabulary : vocabularies) {
            query.addVocabulary(vocabulary);
        }

        return query;
    }

    public static class Query {

        private final MapSqlParameterSource params = new MapSqlParameterSource();
        private final StringBuilder query = new StringBuilder();
        private final Tag category;

        @Setter
        private Map<Integer, List<Integer>> map = new HashMap<>();

        public Query(Tag category) {
            this.category = category;
        }

        private void addVocabulary(Vocabulary vocabulary) {

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
            params.addValue("vid" + vocabulary.getId(), vocabulary.getId());

            query.append(" AND pt1_.tag_id = :cid");
            params.addValue("cid", category.getId());

            for (var entry : map.entrySet()) {
                if (entry.getKey() == vocabulary.getId()) {
                    continue;
                }
                query.append(" AND pt").append(entry.getKey()).append("_.tag_id in (:tags").append(entry.getKey()).append(")");
                params.addValue("tags" + entry.getKey(), entry.getValue());
            }

            query.append(" GROUP BY t.id");
        }

        @Override
        public String toString() {
            return query.toString();
        }

        public MapSqlParameterSource params() {
            return params;
        }
    }
}
