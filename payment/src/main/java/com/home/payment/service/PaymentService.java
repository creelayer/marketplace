package com.home.payment.service;

import com.home.payment.entity.Payment;
import com.home.payment.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<Payment> getPayments(){
        return paymentRepository.findAll();
    }
}
