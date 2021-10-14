package com.home.kit.controller;

import com.home.kit.entity.Kit;
import com.home.kit.service.KitService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("v1/kit")
public class KitController {

    private KitService kitService;

    @GetMapping("/")
    public Page<Kit> list(Pageable pageable) {
        return kitService.search(pageable);
    }

}
