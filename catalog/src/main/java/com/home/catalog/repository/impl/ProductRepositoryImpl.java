package com.home.catalog.repository.impl;

import com.home.catalog.dto.CatalogFilter;
import com.home.catalog.entity.Product;
import com.home.catalog.entity.Tag;
import com.home.catalog.mapper.impl.ProductRowMapper;
import com.home.catalog.repository.Batch;
import com.home.catalog.repository.ProductSearch;
import com.home.catalog.repository.query.SearchQueryBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ProductRepositoryImpl implements ProductSearch, Batch<Product> {

    private NamedParameterJdbcTemplate jdbc;

    public Page<Product> search(CatalogFilter filter, Pageable pageable) {

        SearchQueryBuilder queryBuilder = new SearchQueryBuilder()
                .setCategory(filter.getCategory())
                .setTags(filter.getTags())
                .setPageable(pageable);

        SearchQueryBuilder.Query countQuery = queryBuilder.createCountQuery();

        Long count = jdbc.queryForObject(countQuery.toString(), countQuery.params(), Long.class);

        if (count == null || count == 0) {
            return Page.empty();
        }

        SearchQueryBuilder.Query query = queryBuilder.createQuery();
        List<Product> products = jdbc.query("SELECT id, gid, name, url, preview, short_case, price_min, price_max " +
                        "FROM product " +
                        "WHERE gid IN (" + query + ")",
                query.params(),
                new ProductRowMapper()
        );

        return new PageImpl<>(products, pageable, count);

    }

    public void batchUpsert(Set<Product> products) throws SQLException {
        ProductRowMapper mapper = new ProductRowMapper();
        jdbc.batchUpdate(
                "INSERT INTO product (id, gid, name, url, prices, preview, short_case, price_min, price_max, created_at, updated_at) " +
                        "VALUES (:id, :gid, :name, :url, :prices, :preview, :short_case, :price_min, :price_max, now(), now()) " +
                        "ON CONFLICT (id) DO UPDATE SET " +
                        "gid = excluded.gid, " +
                        "name = excluded.name, " +
                        "url = excluded.url, " +
                        "prices = excluded.prices, " +
                        "preview = excluded.preview, " +
                        "short_case = excluded.short_case, " +
                        "price_min = excluded.price_min, " +
                        "price_max = excluded.price_max, " +
                        "updated_at = now();", mapper.map(products));

        this.updateProductTags(products);
        this.updateIndex(products);
    }

    private void updateProductTags(Set<Product> products) {
        Set<Long> ids = products.stream().map(Product::getId).collect(Collectors.toSet());
        SqlParameterSource parameter = new MapSqlParameterSource("ids", ids);
        jdbc.update("DELETE FROM product_tag WHERE product_id IN (:ids) ", parameter);

        List<Long[]> productTags = new ArrayList<>();
        for (Product product : products) {
            for (Tag tag : product.getTags()) {
                productTags.add(new Long[]{product.getId(), (long) tag.getId(), (long) tag.getVocabulary().getId()});
            }
        }

        jdbc.getJdbcTemplate().batchUpdate("INSERT INTO product_tag (product_id, tag_id, vocabulary_id) VALUES (?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, productTags.get(i)[0]);
                        ps.setLong(2, productTags.get(i)[1]);
                        ps.setLong(3, productTags.get(i)[2]);
                    }

                    @Override
                    public int getBatchSize() {
                        return productTags.size();
                    }
                });
    }

    private void updateIndex(Set<Product> products) {
        Set<Long> ids = products.stream().map(Product::getGid).filter(Objects::nonNull).collect(Collectors.toSet());
        SqlParameterSource parameter = new MapSqlParameterSource("ids", ids);
        jdbc.update("insert into search_index (gid, tags)" +
                "        select gid, array_agg(tags order by tags)" +
                "        from(" +
                "               select distinct gid, pt.tag_id as tags" +
                "               from product" +
                "               inner join product_tag pt on product.id = pt.product_id" +
                "               where product.gid IN (:ids)" +
                "        ) t" +
                "        group by gid" +
                "        ON CONFLICT (gid) DO UPDATE SET tags = excluded.tags ", parameter);
    }
}
