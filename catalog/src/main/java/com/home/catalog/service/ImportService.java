package com.home.catalog.service;

import com.home.catalog.dto.ProductDto;
import com.home.catalog.entity.Product;
import com.home.catalog.entity.Tag;
import com.home.catalog.mapper.ProductMapper;
import com.home.catalog.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
@Service
public class ImportService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final TagService tagService;
    private final ConcurrentHashMap<Integer, Tag> tagCache = new ConcurrentHashMap<>();

    public void upsert(Set<ProductDto> data) {
        try {
            Set<Product> products = productMapper.map(data);
            for (Product product : products) {
                product.getTags().forEach(tag -> tagCache.computeIfAbsent(tag.getId(), id -> tagService.upsert(tag)));
            }
            productRepository.batchUpsert(products);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
