package com.home.catalog.service;

import com.home.catalog.dto.CatalogFilter;
import com.home.catalog.entity.Product;
import com.home.catalog.mapper.ProductMapper;
import com.home.catalog.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public Page<Product> search(CatalogFilter filter, Pageable pageable) {
        return productRepository.search(filter, pageable);
    }

    public void upsert(List<Product> data) {
        try {
            productRepository.batchUpsert(data);
            productRepository.updateIndex(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Product> findByUrl(String url){
        return productRepository.findByUrl(url);
    }
}
