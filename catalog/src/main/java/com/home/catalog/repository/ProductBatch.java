package com.home.catalog.repository;

import com.home.catalog.dto.ProductDto;
import java.util.List;

public interface ProductBatch {
    void batchUpsert(List<ProductDto> products);
    void updateIndex(List<ProductDto> products);
}
