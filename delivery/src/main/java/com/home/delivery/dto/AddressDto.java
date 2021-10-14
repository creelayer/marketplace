package com.home.delivery.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class AddressDto {
    @NotBlank(message = "Address is mandatory")
    private String address;
}
