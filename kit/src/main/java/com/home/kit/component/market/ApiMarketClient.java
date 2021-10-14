package com.home.kit.component.market;

import com.home.kit.component.market.dto.ExternalMarket;
import com.home.kit.component.market.dto.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class ApiMarketClient {

    @Value("${kit.market.api.baseUrl}")
    private String baseUrl;

    private final RestTemplate client = new RestTemplate();

    public ExternalMarket getMarket(UUID uuid) {
        Response<ExternalMarket> response = client.exchange(baseUrl + "/market/" + uuid.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Response<ExternalMarket>>() {
                }).getBody();
        return response.getContent();
    }
}
