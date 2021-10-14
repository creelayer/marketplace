package com.home.kit.repository;

import com.home.kit.entity.Market;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MarketRepository extends PagingAndSortingRepository<Market, UUID> {
    Market readByUid(UUID uid);
}
