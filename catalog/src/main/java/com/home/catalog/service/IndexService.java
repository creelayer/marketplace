package com.home.catalog.service;

import com.home.catalog.dto.ProductDto;
import com.home.catalog.mapper.ProductMapper;
import com.home.catalog.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IndexService {

    private final ProductService productService;
    private final TagService tagService;
    private final ProductMapper productMapper;
    private final TagMapper tagMapper;

    public void index(List<ProductDto> data) {
        for (ProductDto product : data) {
            for (ProductDto.TagDto dto : product.getTags()) {
                tagService.upsert(tagMapper.map(dto));
            }
        }
        productService.upsert(productMapper.map(data));
    }
}
