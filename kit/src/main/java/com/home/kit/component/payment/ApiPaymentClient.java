package com.home.kit.component.payment;

import com.home.kit.component.payment.dto.ExternalPayment;
import com.home.kit.component.payment.dto.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiPaymentClient {

    @Value("${kit.payment.api.baseUrl}")
    private String baseUrl;

    private final RestTemplate client = new RestTemplate();

    public ExternalPayment[] getPayments() {
        Response<ExternalPayment[]> response = client.exchange(baseUrl + "/payment",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Response<ExternalPayment[]>>() {
                }).getBody();
        return response.getContent();
    }
}
