package com.home.kit.controller;

import com.home.kit.dto.SlaDto;
import com.home.kit.dto.Response;
import com.home.kit.entity.Sla;
import com.home.kit.mapper.SlaMapper;
import com.home.kit.service.SlaService;
import lombok.AllArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("v1/sla")
public class SlaController {

    private SlaService slaService;
    private SlaMapper mapper;

    @GetMapping("{sla}")
    public Response<Sla> view(@PathVariable Sla sla) {
        return new Response<>(sla);
    }

    @PostMapping("")
    public Response<Sla> create(@Validated @RequestBody SlaDto dto) {
        return new Response<>(slaService.save(mapper.map(dto)));
    }

    @PutMapping("/{sla}")
    public Response<Sla> update(@PathVariable Sla sla, @Validated @RequestBody SlaDto dto) {
        return new Response<>(slaService.save(mapper.map(dto, sla)));
    }

}
