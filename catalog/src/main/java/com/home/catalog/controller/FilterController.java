package com.home.catalog.controller;

import com.home.catalog.dto.CatalogFilter;
import com.home.catalog.dto.Response;
import com.home.catalog.dto.FacetedTagDto;
import com.home.catalog.exception.ResourceNotFoundException;
import com.home.catalog.service.FilterService;
import com.home.catalog.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("v1/filter")
public class FilterController {

    private FilterService filterService;
    private TagService tagService;

    @GetMapping("{url}")
    public Response<List<FacetedTagDto>> filter(
            @PathVariable String url,
            @Valid @ModelAttribute CatalogFilter filter
    ) {
        filter.setCategory(tagService.findByUrl(url).orElseThrow(ResourceNotFoundException::new));
        return new Response<>(filterService.filter(filter));
    }
}
