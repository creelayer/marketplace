package com.home.kit.controller;

import com.home.kit.component.delivery.ApiDeliveryClient;
import com.home.kit.component.delivery.dto.ExternalDelivery;
import com.home.kit.dto.SlaCombinationDto;
import com.home.kit.dto.Response;
import com.home.kit.entity.SlaCombination;
import com.home.kit.mapper.SlaCombinationMapper;
import com.home.kit.service.SlaCombinationService;
import lombok.AllArgsConstructor;

import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("v1/sla/combination")
public class SlaCombinationController {

    private SlaCombinationService slaCombinationService;
    private SlaCombinationMapper mapper;
    private ApiDeliveryClient apiDeliveryClient;

    private Validator validator;

    @PostMapping("")
    public Response<SlaCombination> create(@Validated @RequestBody SlaCombinationDto dto) {
        return new Response<>(slaCombinationService.save(mapper.map(dto)));
    }

    @PutMapping("/{combination}")
    public Response<SlaCombination> update(@PathVariable SlaCombination combination, @Validated @RequestBody SlaCombinationDto dto) {
        return new Response<>(slaCombinationService.save(mapper.map(dto, combination)));
    }


    @GetMapping("/test")
    public ExternalDelivery[] list() {
        return apiDeliveryClient.getDeliveries();
    }

}
