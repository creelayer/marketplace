package com.home.kit.component.delivery;

import com.home.kit.component.delivery.dto.ExternalDelivery;
import com.home.kit.component.delivery.dto.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiDeliveryClient {

    @Value("${kit.delivery.api.baseUrl}")
    private String baseUrl;

    private final RestTemplate client = new RestTemplate();

    public ExternalDelivery[] getDeliveries() {
        Response<ExternalDelivery[]> response = client.exchange(baseUrl + "/delivery",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Response<ExternalDelivery[]>>() {
                }).getBody();
        return response.getContent();
    }
}
