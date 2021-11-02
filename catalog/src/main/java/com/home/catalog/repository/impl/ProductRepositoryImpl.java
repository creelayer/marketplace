package com.home.catalog.repository.impl;

import com.home.catalog.dto.CatalogFilter;
import com.home.catalog.dto.ProductDto;
import com.home.catalog.dto.TagDto;
import com.home.catalog.entity.Product;
import com.home.catalog.repository.ProductBatch;
import com.home.catalog.repository.query.ProductSearchQuery;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@AllArgsConstructor
public class ProductRepositoryImpl implements ProductBatch {

    private NamedParameterJdbcTemplate jdbc;

    public Page<Product> search(CatalogFilter filter, Pageable pageable) {
        ProductSearchQuery query = new ProductSearchQuery(jdbc)
                .setPageable(pageable)
                .andCategory(filter.getCategory())
                .andTags(filter.getTags());

        return new PageImpl<>(query.all(), pageable, query.count());
    }

    public void batchUpsert(List<ProductDto> products) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(products);
        jdbc.batchUpdate(
                "INSERT INTO product (id, name, url, index, created_at, updated_at) VALUES (:id, :name, :url, :tagIds, now(), now()) " +
                        "ON CONFLICT (id) DO UPDATE SET " +
                        "name = excluded.name, \n" +
                        "url = excluded.url, \n" +
                        "index = excluded.index, \n" +
                        "updated_at = now();", batch);
    }

    public void updateIndex(List<ProductDto> products) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(products);
        jdbc.batchUpdate("DELETE FROM product_tag WHERE product_id = :id ", batch);

        List<Integer[]> productTags = new ArrayList<>();
        for (ProductDto product : products) {
            for (TagDto tag : product.getTags()) {
                productTags.add(new Integer[]{product.getId(), tag.getId(), tag.getVocabulary().getId()});
            }
        }

        jdbc.getJdbcTemplate().batchUpdate("INSERT INTO product_tag (product_id, tag_id, vocabulary_id) VALUES (?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, productTags.get(i)[0]);
                        ps.setInt(2, productTags.get(i)[1]);
                        ps.setInt(3, productTags.get(i)[2]);
                    }

                    @Override
                    public int getBatchSize() {
                        return productTags.size();
                    }
                });
    }
}
