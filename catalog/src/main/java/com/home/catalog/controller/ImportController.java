package com.home.catalog.controller;

import com.home.catalog.dto.CategoryDto;
import com.home.catalog.dto.ProductDto;
import com.home.catalog.dto.Response;
import com.home.catalog.entity.Category;
import com.home.catalog.service.CategoryService;
import com.home.catalog.service.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/import")
public class ImportController {

    private final IndexService indexService;
    private final CategoryService categoryService;

    @PostMapping("product")
    public String index(@RequestBody List<@Valid ProductDto> data) {
        indexService.index(data);
        return "Success";
    }

    @PostMapping("category")
    public Response<Category> create(@Valid @RequestBody CategoryDto dto) {
        return new Response<>(categoryService.save(dto));
    }
}
