package com.home.delivery.config;

import com.home.delivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class InitConfig {

    @Autowired
    private DeliveryService deliveryService;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
    }

}
