package com.home.catalog.repository;

import com.home.catalog.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>, ProductBatch, ProductSearch {
    Optional<Product> findByUrl(String url);
}
