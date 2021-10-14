package com.home.delivery.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class DeliveryDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
}
