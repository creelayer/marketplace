package com.home.catalog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/catalog")
public class CatalogController {

    @GetMapping("")
    public Mono<String> index() {
        return Mono.just("Helloo");
    }

}
