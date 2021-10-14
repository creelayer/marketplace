package com.home.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response<T> {
    private T content;
    public Response(T content){
        this.content = content;
    }
}
