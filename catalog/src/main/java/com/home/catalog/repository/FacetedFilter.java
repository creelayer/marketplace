package com.home.catalog.repository;

import com.home.catalog.dto.CatalogFilter;
import com.home.catalog.dto.FacetedTagDto;
import com.home.catalog.entity.Vocabulary;

import java.util.List;

public interface FacetedFilter {
    List<FacetedTagDto> filter(CatalogFilter filter, List<Vocabulary> vocabularies);
}
