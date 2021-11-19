package com.home.catalog.repository.impl;

import com.home.catalog.dto.CatalogFilter;
import com.home.catalog.dto.FacetedTagDto;
import com.home.catalog.entity.*;
import com.home.catalog.mapper.impl.FacetedTagMapper;
import com.home.catalog.repository.FacetedFilter;
import com.home.catalog.repository.query.FilterQueryBuilder;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@AllArgsConstructor
@Repository
public class TagRepositoryImpl implements FacetedFilter {

    private NamedParameterJdbcTemplate jdbc;

    public List<FacetedTagDto> filter(CatalogFilter filter, List<Vocabulary> vocabularies) {

        FilterQueryBuilder.Query query = new FilterQueryBuilder()
                .setVocabularies(vocabularies)
                .setCategory(filter.getCategory())
                .setTags(filter.getTags())
                .createQuery();

        return jdbc.query(query.toString(), query.params(), new FacetedTagMapper());

    }
}
