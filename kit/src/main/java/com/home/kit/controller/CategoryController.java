package com.home.kit.controller;

import com.home.kit.dto.CategoryDto;
import com.home.kit.dto.Response;
import com.home.kit.dto.TagDto;
import com.home.kit.entity.Category;
import com.home.kit.mapper.CategoryMapper;
import com.home.kit.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("v1/category")
public class CategoryController {

    private CategoryService categoryService;
    private CategoryMapper mapper;

    @GetMapping("")
    public Page<Category> list(Pageable pageable) {
        return categoryService.search(pageable);
    }


    @PostMapping("")
    public Response<Category> create(@Validated(TagDto.CreateValidation.class) @RequestBody CategoryDto dto) {
        return new Response<>(categoryService.save(mapper.map(dto)));
    }

    @PutMapping("/{category}")
    public Response<Category> update(@PathVariable Category category, @Validated @RequestBody CategoryDto dto) {
        return new Response<>(categoryService.save(mapper.map(dto, category)));
    }


    @DeleteMapping("/{category}")
    public Response<String> delete(@PathVariable Category category) {
        categoryService.delete(category);
        return new Response<>("deleted");
    }

}
