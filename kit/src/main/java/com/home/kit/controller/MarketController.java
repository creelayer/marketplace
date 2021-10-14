package com.home.kit.controller;

import com.home.kit.dto.Response;
import com.home.kit.entity.Market;
import com.home.kit.service.MarketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("v1/market")
public class MarketController {

    private MarketService marketService;

    @PostMapping("/import/{uuid}")
    public Response<Market> importMarket(@PathVariable UUID uuid) {
        return new Response<>(marketService.importMarket(uuid));
    }
}
