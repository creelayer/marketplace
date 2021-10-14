package com.home.kit.config;

import com.home.kit.service.DeliveryService;
import com.home.kit.service.PaymentService;
import com.home.kit.service.VocabularyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
@Configuration
public class InitConfig {

    private final VocabularyService vocabularyService;

    private final DeliveryService deliveryService;
    private final PaymentService paymentService;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        vocabularyService.createDefault();
        deliveryService.importDefault();
        paymentService.importDefault();
    }
}
