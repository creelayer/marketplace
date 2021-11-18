package com.home.catalog.controller;

import com.home.catalog.dto.CategoryDto;
import com.home.catalog.dto.ProductDto;
import com.home.catalog.dto.Response;
import com.home.catalog.service.CategoryService;
import com.home.catalog.service.ImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/import")
public class ImportController {

    private final ImportService importService;
    private final CategoryService categoryService;

    @PostMapping("product")
    public ResponseEntity<Object> index(@RequestBody Set<@Valid ProductDto> data) {
        importService.upsert(data);
        return Response.ok();
    }

    @PostMapping("category")
    public ResponseEntity<Object> create(@Valid @RequestBody CategoryDto dto) {
        return Response.ok(categoryService.save(dto));
    }
}
