package com.home.kit.service;

import com.home.kit.component.payment.ApiPaymentClient;
import com.home.kit.component.payment.dto.ExternalPayment;
import com.home.kit.entity.Payment;
import com.home.kit.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PaymentService {

    private ApiPaymentClient paymentClient;

    private PaymentRepository paymentRepository;

    public Page<Payment> search(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void importDefault() {
        for (ExternalPayment externalPayment: paymentClient.getPayments()) {
            Payment payment = paymentRepository.findById(externalPayment.getUid()).orElseGet(() -> {
                Payment entity = new Payment();
                entity.setUid(externalPayment.getUid());
                entity.setName(externalPayment.getName());
                return paymentRepository.save(entity);
            });
            payment.setName(externalPayment.getName());
            paymentRepository.save(payment);
        }
    }

}
