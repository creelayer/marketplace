package com.home.delivery.controller;

import com.home.delivery.dto.Response;
import com.home.delivery.entity.Delivery;
import com.home.delivery.mapper.DeliveryMapper;
import com.home.delivery.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("v1/delivery")
public class DeliveryController {

    private DeliveryService deliveryService;
    private DeliveryMapper mapper;

    @GetMapping("")
    public Page<Delivery> list(Pageable pageable) {
        return deliveryService.search(pageable);
    }

    @GetMapping("/{delivery}")
    public Response<Delivery> view(@PathVariable Delivery delivery) {
        return new Response<>(delivery);
    }
}
