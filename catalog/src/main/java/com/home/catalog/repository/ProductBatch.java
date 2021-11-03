package com.home.catalog.repository;

import com.home.catalog.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductBatch {
    void batchUpsert(List<Product> products) throws SQLException;
    void updateIndex(List<Product> products) throws SQLException;
}
