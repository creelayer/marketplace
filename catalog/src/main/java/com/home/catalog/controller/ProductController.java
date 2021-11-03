package com.home.catalog.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.home.catalog.dto.CatalogFilter;
import com.home.catalog.dto.Response;
import com.home.catalog.entity.Product;
import com.home.catalog.exception.ResourceNotFoundException;
import com.home.catalog.service.ProductService;
import com.home.catalog.service.TagService;
import com.home.catalog.view.ProductView;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("v1/product")
public class ProductController {

    private ProductService productService;
    private TagService tagService;

    @JsonView(ProductView.Public.class)
    @GetMapping("search/{url}")
    public Page<Product> search(@PathVariable String url, @Valid @ModelAttribute CatalogFilter filter) {
        filter.setCategory(tagService.findByUrl(url).orElseThrow(ResourceNotFoundException::new));
        return productService.search(filter, PageRequest.of(filter.getPage(), filter.getSize()));
    }

    @GetMapping("/{url}")
    public Response<Product> view(@PathVariable String url) {
        Product product = productService.findByUrl(url).orElseThrow(ResourceNotFoundException::new);
        return new Response<>(product);
    }

}
