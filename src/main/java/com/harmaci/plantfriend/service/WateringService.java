package com.harmaci.plantfriend.service;

import com.harmaci.plantfriend.repository.WateringRepository;
import com.harmaci.plantfriend.repository.model.Watering;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WateringService {
    private final WateringRepository repository;

    public WateringService(WateringRepository repository) {
        this.repository = repository;
    }

    public List<Watering> getRecentWaterings(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit, WateringRepository.SORT_BY_DATE.descending());
        return repository.findAllByOrderByDateDesc(pageable);
    }

}
