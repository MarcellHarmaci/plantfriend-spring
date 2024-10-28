package com.harmaci.plantfriend.service;

import com.harmaci.plantfriend.repository.PlantRepository;
import com.harmaci.plantfriend.repository.model.Plant;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;

@Service
public class PlantService {

    private final PlantRepository repository;

    public PlantService(PlantRepository repository) {
        this.repository = repository;
    }

    public List<Plant> findAll() {
        return repository.findAll();
    }

    public @Nullable Plant getPlantById(Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    /**
     * Save a plant. If a record by <code>plant.id</code> already exists, then updates it, otherwise inserts a new record.
     * @param plant A plant object, whose <code>id</code> is ignored if it doesn't exist in the database.
     * @return The new DB state of the plant
     */
    public Plant savePlant(Plant plant) {
        return repository.save(plant);
    }
}
