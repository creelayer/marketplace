package com.home.delivery.repository;

import com.home.delivery.entity.Delivery;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeliveryRepository extends PagingAndSortingRepository<Delivery, UUID> {
    Delivery readByUid(String uid);
}
