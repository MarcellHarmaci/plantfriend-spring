package com.harmaci.plantfriend.service;

import com.harmaci.plantfriend.repository.PlantRepository;
import com.harmaci.plantfriend.repository.WateringRepository;
import com.harmaci.plantfriend.repository.model.Plant;
import com.harmaci.plantfriend.repository.model.Watering;
import com.harmaci.plantfriend.service.util.Util;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WateringService {
    @Autowired
    private WateringRepository repository;
    @Autowired
    private PlantRepository plantRepository;

    public List<Watering> getRecentWaterings(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit, WateringRepository.SORT_BY_DATE.descending());
        return repository.findAllByOrderByDateDesc(pageable);
    }

    public List<Watering> getWateringsByPlantId(long plantId) {
        return repository.findAllByPlantId(plantId);
    }

    public List<LocalDate> getWateringDatesByPlantId(long plantId) {
        return repository.findAllByPlantId(plantId)
                .stream()
                .map(Watering::date)
                .collect(Collectors.toList());
    }

    public Watering createWatering(
            @NotNull Long id,
            @NotNull LocalDate date,
            @NotNull Integer plantHealth,
            @Nullable String comment
    ) throws EntityNotFoundException {
        Plant plantRef = plantRepository.getReferenceById(id);
        return repository.save(new Watering(plantRef, date, plantHealth, comment));
    }

    public Watering updateWatering(Watering watering, Long plantId)
            throws EntityNotFoundException, DataIntegrityViolationException {
        Watering existingWatering = repository
                .findById(watering.id())
                .orElseThrow(Util.getEnfException("watering", watering.id()));

        Plant originalPlant = existingWatering.plant();
        if (!originalPlant.id().equals(plantId)) {
            throw new DataIntegrityViolationException(String.format(
                    "Watering %d belongs to plant %d, but %d provided",
                    watering.id(), originalPlant.id(), plantId
            ));
        }

        return repository.save(watering);
    }

    public boolean deleteWateringById(Long id) {
        if (!plantRepository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
