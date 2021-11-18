package com.home.catalog.repository;

import java.sql.SQLException;
import java.util.Set;

public interface Batch<T> {
    void batchUpsert(Set<T> products) throws SQLException;
}
