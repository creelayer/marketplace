package com.home.catalog.repository.query;

import com.home.catalog.entity.Product;
import com.home.catalog.entity.Tag;
import com.home.catalog.mapper.impl.ProductRowMapper;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.*;

@Setter
@Accessors(chain = true)
public class ProductSearchQuery {

    private final NamedParameterJdbcTemplate jdbc;

    private final MapSqlParameterSource params = new MapSqlParameterSource();
    private final StringBuilder query = new StringBuilder("SELECT * FROM product WHERE 1=1");
    private Pageable pageable;

    public ProductSearchQuery(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Long count() {
        return jdbc.queryForObject(query.toString().replace("*", "COUNT(*)"), params, Long.class);
    }

    public List<Product> all() {
        params.addValue("size", pageable.getPageSize())
                .addValue("offset", pageable.getOffset());
        return jdbc.query(this.query.toString() + " ORDER BY id LIMIT :size OFFSET :offset", params, new ProductRowMapper());
    }

    public ProductSearchQuery andCategory(Tag category) {
        if (category == null) {
            return this;
        }
        query.append(" AND index @> :category");
        params.addValue("category", new int[]{category.getId()});
        return this;
    }

    public ProductSearchQuery andTags(Set<Tag> tags) {
        if (tags.isEmpty()) {
            return this;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (Tag tag : tags) {
            if (!map.containsKey(tag.getVocabulary().getId())) {
                map.put(tag.getVocabulary().getId(), new ArrayList<>());
            }
            map.get(tag.getVocabulary().getId()).add(tag.getId());
        }

        for (var entry : map.entrySet()) {
            query.append(" AND index && :tags").append(entry.getKey());
            params.addValue("tags" + entry.getKey(), entry.getValue().toArray(Integer[]::new));
        }
        return this;
    }
}
