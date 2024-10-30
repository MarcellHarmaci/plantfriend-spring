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
     * Create a new plant
     *
     * @param plant The plant to be inserted. Must not have an id
     * @return The created plant
     * @throws IllegalArgumentException Thrown when the <code>plant</code> parameter's id is not null
     */
    public Plant createPlant(Plant plant) throws IllegalArgumentException {
        if (plant.getId() != null) {
            throw new IllegalArgumentException("The record must not have an id to insert");
        }
        return repository.save(plant);
    }

    /**
     * Update an existing plant
     *
     * @param plant The updated value
     * @return The updated plant
     * @throws IllegalArgumentException Thrown when the <code>plant</code> parameter's id is null
     */
    public Plant updatePlant(Plant plant) throws IllegalArgumentException {
        if (plant.getId() == null) {
            throw new IllegalArgumentException("The record must have an id to update");
        }
        return repository.save(plant);
    }

    /**
     * Delete a plant by id
     *
     * @param id The id of the plant to be deleted
     * @return Whether the deletion was successful or not
     */
    public boolean deletePlant(Long id) {
        Plant plant = getPlantById(id);
        if (plant == null) return false;

        repository.deleteById(id);
        return true;
    }

}
