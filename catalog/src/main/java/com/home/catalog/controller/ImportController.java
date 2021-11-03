package com.home.catalog.controller;

import com.home.catalog.dto.CategoryDto;
import com.home.catalog.dto.ProductDto;
import com.home.catalog.dto.Response;
import com.home.catalog.service.CategoryService;
import com.home.catalog.service.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> index(@RequestBody List<@Valid ProductDto> data) {
        indexService.index(data);
        return Response.ok();
    }

    @PostMapping("category")
    public ResponseEntity<Object> create(@Valid @RequestBody CategoryDto dto) {
        return Response.ok(categoryService.save(dto));
    }
}
