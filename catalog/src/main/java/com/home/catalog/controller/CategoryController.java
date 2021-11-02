package com.home.catalog.controller;

import com.home.catalog.dto.Response;
import com.home.catalog.entity.Category;
import com.home.catalog.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("v1/category")
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("")
    public Response<List<Category>> index() {
        return new Response<>(categoryService.findAll());
    }
}
