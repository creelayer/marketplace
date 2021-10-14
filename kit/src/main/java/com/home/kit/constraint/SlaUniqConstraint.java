package com.home.kit.constraint;

import com.home.kit.entity.Sla;
import com.home.kit.repository.SlaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SlaUniqConstraint implements ConstraintValidator {

    private SlaRepository slaRepository;

    @Override
    public boolean isValid(Object o) {

        Sla sla = (Sla) o;
        Sla exist = slaRepository.findByMarketAndName(sla.getMarket(), sla.getName());

        if (exist == null || exist.getId() == sla.getId()) {
            return true;
        }

        return false;
    }
}
