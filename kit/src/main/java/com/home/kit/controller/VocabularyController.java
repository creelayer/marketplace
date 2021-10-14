package com.home.kit.controller;

import com.home.kit.dto.Response;
import com.home.kit.dto.VocabularyDto;
import com.home.kit.entity.Vocabulary;
import com.home.kit.mapper.VocabularyMapper;
import com.home.kit.service.VocabularyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("v1/vocabulary")
public class VocabularyController {

    private VocabularyService vocabularyService;
    private VocabularyMapper mapper;

    @GetMapping("")
    public Page<Vocabulary> list(Pageable pageable) {
        return vocabularyService.search(pageable);
    }


    @PostMapping("")
    public Response<Vocabulary> create(@Validated @RequestBody VocabularyDto dto) {
        return new Response<>(vocabularyService.save(mapper.map(dto)));
    }

    @PutMapping("/{vocabulary}")
    public Response<Vocabulary> update(@PathVariable Vocabulary vocabulary, @Validated @RequestBody VocabularyDto dto) {
        return new Response<>(vocabularyService.save(mapper.map(dto, vocabulary)));
    }


    @DeleteMapping("/{vocabulary}")
    public Response<String> delete(@PathVariable Vocabulary vocabulary) {
        vocabularyService.delete(vocabulary);
        return new Response<>("deleted");
    }

}
