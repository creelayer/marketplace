package com.home.kit.repository;

import com.home.kit.entity.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends PagingAndSortingRepository<Tag, Integer> {
    Tag readById(int id);
}
