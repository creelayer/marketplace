package com.home.kit.controller;

import com.home.kit.dto.ProductDto;
import com.home.kit.dto.Response;
import com.home.kit.entity.Product;
import com.home.kit.mapper.ProductMapper;
import com.home.kit.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("v1/product")
public class ProductController {

    private ProductService productService;
    private ProductMapper mapper;

    @GetMapping("")
    public Page<Product> list(Pageable pageable) {
        return productService.search(pageable);
    }

    @PostMapping("")
    public Response<Product> create(@Validated @RequestBody ProductDto dto) {
        return new Response<>(productService.save(mapper.map(dto)));
    }

    @PutMapping("/{product}")
    public Response<Product> create(@Validated @RequestBody ProductDto dto, @PathVariable Product product) {
        return new Response<>(productService.save(mapper.map(dto, product)));
    }

    @DeleteMapping("/{product}")
    public Response<String> create(@PathVariable Product product) {
        productService.delete(product);
        return new Response<>("deleted");
    }

}
