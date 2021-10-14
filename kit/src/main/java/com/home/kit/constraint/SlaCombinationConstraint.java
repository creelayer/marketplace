package com.home.kit.constraint;

import com.home.kit.repository.SlaCombinationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SlaCombinationConstraint implements ConstraintValidator {

    private SlaCombinationRepository slaCombinationRepository;

    @Override
    public boolean isValid(Object o) {
        return false;
    }

//    @Override
//    public boolean existsBy(Object value, String fieldName) {
//        if (fieldName.equals("cdp")) {
//            SlaCombinationDto dto = (SlaCombinationDto) value;
//
//            if (dto.getSlaId() == null) {
//                return false;
//            }
//
//            if (dto.getDeliveryUid() == null) {
//                return false;
//            }
//
//            if (dto.getPaymentUid() == null) {
//                return false;
//            }
//
//            if (slaCombinationRepository.existsBySlaIdAndName(dto.getSlaId(), dto.getName())) {
//                return false;
//            }
//
//            return slaCombinationRepository.existsBySlaIdAndDeliveryUid(dto.getSlaId(), dto.getDeliveryUid());
//        }
//        throw new IllegalArgumentException("Illegal filed");
//    }
}
