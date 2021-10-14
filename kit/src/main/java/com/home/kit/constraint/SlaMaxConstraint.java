package com.home.kit.constraint;

import com.home.kit.entity.Sla;
import com.home.kit.repository.SlaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SlaMaxConstraint implements ConstraintValidator {

    private static final int MAX_COMBINATION_PER_MARKET = 5;

    private SlaRepository slaRepository;

    @Override
    public boolean isValid(Object o) {
        Sla sla = (Sla) o;
        return sla.getId() != 0 || slaRepository.countByMarket(sla.getMarket()) <= MAX_COMBINATION_PER_MARKET;
    }

    @Override
    public String getMessage() {
        return "Max sla per market";
    }
}
