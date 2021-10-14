package com.home.kit.repository;

import com.home.kit.entity.Kit;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitRepository extends PagingAndSortingRepository<Kit, Integer> {
}
