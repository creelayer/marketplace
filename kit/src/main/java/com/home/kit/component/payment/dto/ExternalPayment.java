package com.home.kit.component.payment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class ExternalPayment {
    private UUID uid;
    private String name;
    private boolean active;
    private LocalDateTime updatedAt;
}
