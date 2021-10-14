package com.home.kit.constraint;

import com.home.kit.repository.MarketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;


@AllArgsConstructor
@Component
public class MarketExistConstraint implements ConstraintValidator {

    private MarketRepository marketRepository;

    @Override
    public boolean isValid(Object o) {
        return marketRepository.existsById((UUID) o);
    }
}
