package com.home.catalog.service;

import com.home.catalog.dto.ProductDto;
import com.home.catalog.dto.TagDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IndexService {

    private final ProductService productService;
    private final TagService tagService;

    public void index(List<ProductDto> data) {
        for (ProductDto product : data) {
            for (TagDto dto : product.getTags()) {
                tagService.upsert(dto);
            }
        }
        productService.upsert(data);
    }
}
