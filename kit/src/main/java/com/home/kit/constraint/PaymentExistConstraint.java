package com.home.kit.constraint;

import com.home.kit.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@AllArgsConstructor
@Component
public class PaymentExistConstraint implements ConstraintValidator {

    private PaymentRepository paymentRepository;

    @Override
    public boolean isValid(Object o) {
        if (o.getClass().isArray()) {
            return Arrays.stream((UUID[]) o).allMatch(uid -> paymentRepository.existsById(uid));
        }
        return paymentRepository.existsById((UUID) o);
    }
}
