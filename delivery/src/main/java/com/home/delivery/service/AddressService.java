package com.home.delivery.service;

import com.home.delivery.entity.Address;
import com.home.delivery.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public Page<Address> search(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
