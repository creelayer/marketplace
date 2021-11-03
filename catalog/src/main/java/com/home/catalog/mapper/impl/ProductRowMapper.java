package com.home.catalog.mapper.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.catalog.entity.Product;
import com.home.catalog.entity.Tag;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class ProductRowMapper implements RowMapper<Product> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        Set<Product.ShortCase> shortCase = null;
        try {
            shortCase = rs.getString("short_case") == null ? null :
                    mapper.readValue(rs.getString("short_case"), new TypeReference<>() {
                    });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setUrl(rs.getString("url"));
        product.setImage(rs.getString("image"));
        product.setShortCase(shortCase);
        product.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        product.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return product;
    }

    public MapSqlParameterSource map(Product product) throws SQLException {
        MapSqlParameterSource param = new MapSqlParameterSource();
        PGobject shortCase = new PGobject();
        shortCase.setType("json");

        try {
            shortCase.setValue(mapper.writeValueAsString(product.getShortCase()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        param.addValue("id", product.getId());
        param.addValue("name", product.getName());
        param.addValue("url", product.getUrl());
        param.addValue("image", product.getImage());
        param.addValue("images", product.getImages().toArray(String[]::new));
        param.addValue("index", product.getTags().stream().mapToInt(Tag::getId).toArray());
        param.addValue("short_case", shortCase);

        return param;
    }

    public MapSqlParameterSource[] map(List<Product> products) throws SQLException {
        MapSqlParameterSource[] items = new MapSqlParameterSource[products.size()];
        int i = 0;
        for (Product product : products) {
            items[i] = map(product);
            i++;
        }
        return items;
    }
}
