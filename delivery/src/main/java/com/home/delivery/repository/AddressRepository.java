package com.home.delivery.repository;

import com.home.delivery.entity.Address;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address, Integer> {
}
