package com.home.kit.repository;

import com.home.kit.entity.Sla;
import com.home.kit.entity.Market;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlaRepository extends PagingAndSortingRepository<Sla, Integer> {
  //  Sla readById(int id);
    Sla findByMarketAndName(Market market, String name);
    int countByMarket(Market market);
}
