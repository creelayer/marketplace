package com.home.kit.constraint;

import com.home.kit.repository.SlaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SlaExistConstraint implements  ConstraintValidator {

    private SlaRepository slaRepository;

    @Override
    public boolean isValid(Object o) {
        return slaRepository.existsById((Integer) o);
    }


}
