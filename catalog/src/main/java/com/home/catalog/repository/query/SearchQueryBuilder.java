package com.home.catalog.repository.query;

import com.home.catalog.entity.Tag;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.*;

@Setter
@Accessors(chain = true)
public class SearchQueryBuilder extends QueryBuilder {

    private String table = "search_index";

    private String select = "gid";
    private Tag category;
    private Set<Tag> tags;
    private Pageable pageable;

    public Query createQuery() {
        return new Query()
                .select(select)
                .table(table)
                .category(category)
                .map(getVocabularyIndexedTags(tags))
                .pageable(pageable);
    }

    public Query createCountQuery() {
        return new Query()
                .select("count(*)")
                .table(table)
                .category(category)
                .map(getVocabularyIndexedTags(tags));
    }

    public static class Query {

        private final MapSqlParameterSource params = new MapSqlParameterSource();
        private final StringBuilder query = new StringBuilder();

        private Query select(String select) {
            query.append("SELECT ").append(select).append(" ");
            return this;
        }

        private Query table(String table) {
            query.append(" FROM ").append(table);
            return this;
        }

        private Query category(Tag category) {
            if (category == null) {
                return this;
            }
            query.append(" WHERE tags @> :category");
            params.addValue("category", new int[]{category.getId()});
            return this;
        }

        private Query map(Map<Integer, List<Integer>> map) {
            if (map.isEmpty()) {
                return this;
            }
            for (var entry : map.entrySet()) {
                query.append(" AND tags && :tags").append(entry.getKey());
                params.addValue("tags" + entry.getKey(), entry.getValue().toArray(Integer[]::new));
            }
            return this;
        }

        private Query pageable(Pageable pageable) {
            query.append(" LIMIT :size OFFSET :offset");
            params.addValue("size", pageable.getPageSize())
                    .addValue("offset", pageable.getOffset());

            return this;
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
