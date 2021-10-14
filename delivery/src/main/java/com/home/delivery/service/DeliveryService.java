package com.home.delivery.service;

import com.home.delivery.entity.Delivery;
import com.home.delivery.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public Page<Delivery> search(Pageable pageable) {
        return deliveryRepository.findAll(pageable);
    }

    public Delivery save(Delivery delivery){
        return deliveryRepository.save(delivery);
    }

}
