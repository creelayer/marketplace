package com.home.kit.mapper;

import com.home.kit.dto.ProductDto;
import com.home.kit.entity.Category;
import com.home.kit.entity.Kit;
import com.home.kit.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    @Mapping(source = "categoryIds", target = "categories")
    Product map(ProductDto dto);

    @Mapping(source = "categoryIds", target = "categories")
    Product map(ProductDto dto, @MappingTarget Product product);

    ProductDto map(Product product);

    default Category intToCategory(int id) {
        return (Category) new Category().setId(id);
    }

    @AfterMapping
    default void addProductToKit(@MappingTarget Product product) {
        for (Kit kit : product.getKits()) {
            kit.setProduct(product);
        }
    }
}
