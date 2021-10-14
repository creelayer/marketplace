package com.home.delivery.controller;

import com.home.delivery.dto.Response;
import com.home.delivery.entity.Address;
import com.home.delivery.mapper.AddressMapper;
import com.home.delivery.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("v1/address")
public class AddressController {

    private AddressService addressService;
    private AddressMapper mapper;

    @GetMapping("/{address}")
    public Response<Address> view(@PathVariable Address address) {
        return new Response<>(address);
    }
}
