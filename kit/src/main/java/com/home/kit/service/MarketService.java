package com.home.kit.service;

import com.home.kit.component.market.ApiMarketClient;
import com.home.kit.component.market.dto.ExternalMarket;
import com.home.kit.entity.Market;
import com.home.kit.repository.MarketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@AllArgsConstructor
@Service
public class MarketService {

    private MarketRepository marketRepository;
    private ApiMarketClient marketClient;

    public Market save(Market market) {
        return marketRepository.save(market);
    }

    public Market importMarket(UUID uuid) {

        ExternalMarket externalMarket = marketClient.getMarket(uuid);

        if (externalMarket == null) {
            throw new EntityNotFoundException("External market not found");
        }

        Market market = marketRepository.findById(uuid).orElseGet(() -> {
            Market entity = new Market();
            entity.setUid(externalMarket.getUid());
            entity.setName(externalMarket.getName());
            return marketRepository.save(entity);
        });

        market.setName(externalMarket.getName());
        return marketRepository.save(market);
    }
}
