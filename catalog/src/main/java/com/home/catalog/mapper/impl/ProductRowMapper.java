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
import java.util.*;

public class ProductRowMapper implements RowMapper<Product> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();

        try {
            List<Product.ShortCase> shortCase = rs.getString("short_case") == null ? null :
                    mapper.readValue(rs.getString("short_case"), new TypeReference<>() {
                    });
            product.setShortCase(shortCase);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<String> images = new ArrayList<>();
        Collections.addAll(images, (String[]) rs.getArray("preview").getArray());
        product.setPreview(images);

        product.setId(rs.getLong("id"));
        product.setGid(rs.getLong("gid"));
        product.setName(rs.getString("name"));
        product.setUrl(rs.getString("url"));
        product.setPriceMin(rs.getInt("price_min"));
        product.setPriceMax(rs.getInt("price_max"));
        
        return product;
    }

    public MapSqlParameterSource map(Product product) throws SQLException {
        MapSqlParameterSource param = new MapSqlParameterSource();
        PGobject prices = new PGobject();
        prices.setType("json");
        PGobject shortCase = new PGobject();
        shortCase.setType("json");

        try {
            prices.setValue(mapper.writeValueAsString(product.getPrices()));
            shortCase.setValue(mapper.writeValueAsString(product.getShortCase()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        param.addValue("id", product.getId());
        param.addValue("gid", product.getGid());
        param.addValue("name", product.getName());
        param.addValue("url", product.getUrl());
        param.addValue("prices", prices);
        param.addValue("price_min", product.getPriceMin());
        param.addValue("price_max", product.getPriceMax());
        param.addValue("preview", product.getPreview().toArray(String[]::new));
        param.addValue("index", product.getTags().stream().mapToInt(Tag::getId).toArray());
        param.addValue("short_case", shortCase);

        return param;
    }

    public MapSqlParameterSource[] map(Set<Product> products) throws SQLException {
        MapSqlParameterSource[] items = new MapSqlParameterSource[products.size()];
        int i = 0;
        for (Product product : products) {
            items[i] = map(product);
            i++;
        }
        return items;
    }
}
