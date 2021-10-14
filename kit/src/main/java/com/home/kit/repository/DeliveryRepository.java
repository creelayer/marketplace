package com.home.kit.repository;

import com.home.kit.entity.Delivery;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeliveryRepository extends PagingAndSortingRepository<Delivery, UUID> {
}
