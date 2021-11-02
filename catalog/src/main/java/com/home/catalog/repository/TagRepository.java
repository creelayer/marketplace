package com.home.catalog.repository;

import com.home.catalog.entity.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer>, FacetedFilter {
    Optional<Tag> findByUrl(String url);
}
