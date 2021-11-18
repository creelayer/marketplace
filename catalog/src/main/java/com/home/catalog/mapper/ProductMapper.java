package com.home.catalog.mapper;

import com.home.catalog.dto.ProductDto;
import com.home.catalog.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Arrays;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {TagMapper.class})
public interface ProductMapper {

    @Mapping(target = "id", expression = "java(dto.getMarketId() * 10000000000l + dto.getId())")
    @Mapping(target = "gid", expression = "java(dto.getMarketId() * 10000000000l + dto.getGid())")
    @Mapping(target = "priceMin", source = "prices", qualifiedByName = "priceMin")
    @Mapping(target = "priceMax", source = "prices", qualifiedByName = "priceMax")
    Product map(ProductDto dto);

    Set<Product> map(Set<ProductDto> dto);
    Product.ShortCase map(ProductDto.ShortCaseDto dto);

    @Named("priceMin")
    default Integer priceMin(ProductDto.Price[] prices) {
        if (prices == null || prices.length == 0) {
            return null;
        }
        return Arrays.stream(prices).mapToInt(ProductDto.Price::getValue).min().orElse(0);
    }

    @Named("priceMax")
    default Integer priceMax(ProductDto.Price[] prices) {
        if (prices == null || prices.length == 0) {
            return null;
        }
        return Arrays.stream(prices).mapToInt(ProductDto.Price::getValue).max().orElse(0);
    }
}
