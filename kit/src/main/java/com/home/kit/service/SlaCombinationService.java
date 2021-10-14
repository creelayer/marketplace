package com.home.kit.service;

import com.home.kit.entity.SlaCombination;
import com.home.kit.repository.SlaCombinationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SlaCombinationService {
    private SlaCombinationRepository slaCombinationRepository;
    public SlaCombination save(SlaCombination item) {
        return slaCombinationRepository.save(item);
    }
}
