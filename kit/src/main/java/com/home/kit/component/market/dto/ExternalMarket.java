package com.home.kit.component.market.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class ExternalMarket {
    private UUID uid;
    private String name;
    private LocalDateTime updatedAt;
}
