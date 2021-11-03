package com.home.catalog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.home.catalog.view.BaseView;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.ResponseEntity;

@Setter
@Getter
@Accessors(chain = true)
public class Response<T> {

    @JsonView(BaseView.Public.class)
    private T content;
    private int code;
    private String message;

    public Response() {
    }

    public Response(T content) {
        this.content = content;
    }

    public Response(String message) {
        this.message = message;
    }

    public static <T> ResponseEntity<Object> ok() {
        return ResponseEntity.ok(new Response<>());
    }

    public static <T> ResponseEntity<Object> ok(T content) {
        return ResponseEntity.ok(new Response<>(content));
    }

    public static <T> ResponseEntity<Object> badRequest(T content) {
        return ResponseEntity.badRequest().body(new Response<>(content));
    }

    public static <T> ResponseEntity<Object> badRequest(RuntimeException ex) {
        return ResponseEntity.badRequest().body(new Response<>(ex.getMessage()));
    }
}
