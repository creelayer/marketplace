package com.home.kit.constraint;

import com.home.kit.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
public class DeliveryExistConstraint implements ConstraintValidator {

    private DeliveryRepository deliveryRepository;

    @Override
    public boolean isValid(Object o) {
        return deliveryRepository.existsById((UUID) o);
    }
}
