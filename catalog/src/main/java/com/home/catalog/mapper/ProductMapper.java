package com.home.catalog.mapper;

import com.home.catalog.dto.ProductDto;
import com.home.catalog.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    Product map(ProductDto dto);
    List<Product> map(List<ProductDto> dto);

    Product.ShortCase map(ProductDto.ShortCaseDto dto);
}
