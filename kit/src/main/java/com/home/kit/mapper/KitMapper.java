package com.home.kit.mapper;

import com.home.kit.dto.KitDto;
import com.home.kit.entity.Kit;
import com.home.kit.entity.Tag;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface KitMapper {

    @Mapping(source = "tagIds", target = "tags")
    Kit map(KitDto dto);

    default Tag map(int id) {
        return new Tag().setId(id);
    }
}
