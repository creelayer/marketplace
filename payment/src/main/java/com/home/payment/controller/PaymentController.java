package com.home.payment.controller;

import com.home.payment.dto.Response;
import com.home.payment.entity.Payment;
import com.home.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("v1/payment")
public class PaymentController {

    private PaymentService paymentService;

    @GetMapping("")
    public Response<List<Payment>> list() {
        return new Response<>(paymentService.getPayments());
    }

}
