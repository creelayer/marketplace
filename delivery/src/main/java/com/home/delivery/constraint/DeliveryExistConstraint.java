package com.home.delivery.constraint;


import com.home.delivery.entity.Delivery;
import com.home.delivery.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DeliveryExistConstraint implements ConstraintValidator  {

    private DeliveryRepository deliveryRepository;

    @Override
    public boolean isValid(Object o) {
        Delivery delivery = (Delivery) o;
        return deliveryRepository.existsById(delivery.getUid());
    }
}
