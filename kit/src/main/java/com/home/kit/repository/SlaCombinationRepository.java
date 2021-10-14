package com.home.kit.repository;

import com.home.kit.entity.SlaCombination;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SlaCombinationRepository extends PagingAndSortingRepository<SlaCombination, Integer> {
    boolean existsBySlaIdAndName(int combinationId, String name);
    boolean existsBySlaIdAndDeliveryUid(int combinationId, UUID deliveryUid);
}
