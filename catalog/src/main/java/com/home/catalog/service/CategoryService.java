package com.home.catalog.service;

import com.home.catalog.dto.CategoryDto;
import com.home.catalog.entity.Category;
import com.home.catalog.mapper.CategoryMapper;
import com.home.catalog.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category save(CategoryDto dto) {
        Category category = mapper.map(dto, categoryRepository.findById(dto.getId()).orElse(new Category()));
        return categoryRepository.save(category);
    }
}
