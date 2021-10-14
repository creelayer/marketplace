package com.home.delivery.mapper;

import com.home.delivery.dto.AddressDto;
import com.home.delivery.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {
    Address map(AddressDto dto);
    AddressDto map(Address address);
    Address map(AddressDto dto, @MappingTarget Address address);
}
