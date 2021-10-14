package com.home.kit.service;

import com.home.kit.component.delivery.ApiDeliveryClient;
import com.home.kit.component.delivery.dto.ExternalDelivery;
import com.home.kit.entity.Delivery;
import com.home.kit.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeliveryService {

    private ApiDeliveryClient deliveryClient;

    private DeliveryRepository deliveryRepository;

    public Page<Delivery> search(Pageable pageable) {
        return deliveryRepository.findAll(pageable);
    }

    public void importDefault() {
        for (ExternalDelivery externalDelivery : deliveryClient.getDeliveries()) {
            Delivery delivery = deliveryRepository.findById(externalDelivery.getUid()).orElseGet(() -> {
                Delivery entity = new Delivery();
                entity.setUid(externalDelivery.getUid());
                entity.setName(externalDelivery.getName());
                entity.setUpdatedAt(externalDelivery.getUpdatedAt());
                return deliveryRepository.save(entity);
            });
            if(!delivery.getUpdatedAt().equals(externalDelivery.getUpdatedAt())){
                delivery.setName(externalDelivery.getName());
                delivery.setUpdatedAt(externalDelivery.getUpdatedAt());
                deliveryRepository.save(delivery);
            }
        }
    }
}
