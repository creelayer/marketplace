package com.home.catalog.repository.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@AllArgsConstructor
public class IndexRepositoryImpl {
    private NamedParameterJdbcTemplate jdbc;
}
