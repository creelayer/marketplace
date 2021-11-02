package com.home.catalog.mapper.impl;

import com.home.catalog.dto.FacetedTagDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacetedTagMapper implements RowMapper<FacetedTagDto> {
    @Override
    public FacetedTagDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        FacetedTagDto tag = new FacetedTagDto();
        tag.setId(rs.getInt("id"));
        tag.setName(rs.getString("name"));
        tag.setGroup(rs.getString("vocabulary_name"));
        tag.setCount(rs.getInt("count"));
        return tag;
    }
}
