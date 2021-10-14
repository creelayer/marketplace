package com.home.kit.controller;

import com.home.kit.dto.Response;
import com.home.kit.dto.TagDto;
import com.home.kit.entity.Tag;
import com.home.kit.mapper.TagMapper;
import com.home.kit.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("v1/tag")
public class TagController {

    private TagService tagService;
    private TagMapper mapper;

    @GetMapping("")
    public Page<Tag> list(Pageable pageable) {
        return tagService.search(pageable);
    }


    @PostMapping("")
    public Response<Tag> create(@Validated(TagDto.CreateValidation.class) @RequestBody TagDto dto) {
        return new Response<>(tagService.save(mapper.map(dto)));
    }

    @PutMapping("/{tag}")
    public Response<Tag> update(@PathVariable Tag tag, @Validated @RequestBody TagDto dto) {
        return new Response<>(tagService.save(mapper.map(dto, tag)));
    }


    @DeleteMapping("/{tag}")
    public Response<String> delete(@PathVariable Tag tag) {
        tagService.delete(tag);
        return new Response<>("deleted");
    }

}
