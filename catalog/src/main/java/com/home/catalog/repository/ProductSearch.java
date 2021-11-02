package com.home.catalog.repository;

import com.home.catalog.dto.CatalogFilter;
import com.home.catalog.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductSearch {
    Page<Product> search(CatalogFilter filter, Pageable pageable);
}
