package com.home.kit.mapper;

import com.home.kit.dto.SlaCombinationDto;
import com.home.kit.entity.Sla;
import com.home.kit.entity.SlaCombination;
import com.home.kit.entity.Delivery;
import com.home.kit.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SlaCombinationMapper {

    @Mapping(source = "slaId", target = "sla")
    @Mapping(source = "deliveryUid", target = "delivery")
    @Mapping(source = "paymentUid", target = "payments")
    SlaCombination map(SlaCombinationDto dto);

    SlaCombinationDto map(SlaCombination item);

    @Mapping(source = "slaId", target = "sla")
    @Mapping(source = "deliveryUid", target = "delivery")
    @Mapping(source = "paymentUid", target = "payments")
    SlaCombination map(SlaCombinationDto dto, @MappingTarget SlaCombination item);

    default Sla intToSla(int id) {
        return new Sla().setId(id);
    }

    default Payment uuidToPayment(UUID uuid) {
        return new Payment().setUid(uuid);
    }
    default Delivery uuidToDelivery(UUID uid) {
        return new Delivery().setUid(uid);
    }
}
