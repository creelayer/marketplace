package com.home.kit.service;

import com.home.kit.entity.Kit;
import com.home.kit.repository.KitRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class KitService {

    private KitRepository kitRepository;

    public Page<Kit> search(Pageable pageable) {
        return kitRepository.findAll(pageable);
    }

}
