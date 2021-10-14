package com.home.kit.component.market.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response<T> {
    private T content;
}
