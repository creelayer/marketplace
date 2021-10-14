package com.home.kit.repository;

import com.home.kit.entity.Payment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, UUID> {
}
