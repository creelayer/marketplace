package com.home.catalog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.home.catalog.view.BaseView;
import com.home.catalog.view.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response<T> {

    @JsonView(BaseView.Public.class)
    private T content;
    public Response(T content){
        this.content = content;
    }
}
