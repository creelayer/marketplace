package com.home.kit.mapper;

import com.home.kit.dto.SlaDto;
import com.home.kit.entity.Sla;
import com.home.kit.repository.MarketRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = MarketRepository.class)
public interface SlaMapper {

    @Mapping(source = "marketUid", target = "market")
    Sla map(SlaDto dto);

    SlaDto map(Sla sla);

    @Mapping(source = "marketUid", target = "market")
    Sla map(SlaDto dto, @MappingTarget Sla sla);

    default Sla map(int id) {
        return new Sla().setId(id);
    }
}
