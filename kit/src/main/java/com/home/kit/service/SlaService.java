package com.home.kit.service;

import com.home.kit.annotation.Constraint;
import com.home.kit.constraint.SlaMaxConstraint;
import com.home.kit.constraint.SlaUniqConstraint;
import com.home.kit.entity.Sla;
import com.home.kit.repository.SlaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@AllArgsConstructor
@Service
@Validated
public class SlaService {

    private SlaRepository slaRepository;

    public Sla save(
            @Constraint(
                    value = {
                            SlaUniqConstraint.class,
                            SlaMaxConstraint.class
                    },
                    message = "Sla with current params already exist"
            ) Sla sla
    ) {
        return slaRepository.save(sla);
    }
}
