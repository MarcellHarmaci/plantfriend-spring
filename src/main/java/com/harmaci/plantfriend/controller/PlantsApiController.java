package com.harmaci.plantfriend.controller;

import com.harmaci.plantfriend.repository.model.Plant;
import com.harmaci.plantfriend.service.Mapping;
import com.harmaci.plantfriend.service.PlantService;
import org.openapitools.model.PlantData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;

@Controller
public class PlantsApiController extends org.openapitools.api.PlantsApiController {
    private final PlantService service;

    public PlantsApiController(NativeWebRequest request, PlantService service) {
        super(request);
        this.service = service;
    }

    @Override
    public ResponseEntity<List<org.openapitools.model.Plant>> getPlants() {
        return new ResponseEntity<>(
                service.findAll().stream().map(Mapping.DomainToNetwork::plant).toList(),
                HttpStatus.OK
        );
    }


    @Override
    public ResponseEntity<org.openapitools.model.Plant> getPlantById(Long id) {
        Plant plant = service.getPlantById(id);
        return plant != null
                ? new ResponseEntity<>(Mapping.DomainToNetwork.plant(plant), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<org.openapitools.model.Plant> updatePlantById(
            Long id,
            org.openapitools.model.Plant plant
    ) {
        // validation
        if (!plant.getId().equals(id) || plant.getName().isBlank()) {
            return new ResponseEntity<>(
                    plant,
                    HttpStatus.UNPROCESSABLE_ENTITY
            );
        }

        if (!service.plantExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            Plant result = service.updatePlant(Mapping.NetworkToDomain.plant(plant));
            return new ResponseEntity<>(Mapping.DomainToNetwork.plant(result), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<org.openapitools.model.Plant> addPlant(PlantData plantData) {
        // validation
        if (plantData.getName().isBlank()) {
            Plant inValidData = new Plant(plantData.getName());
            return new ResponseEntity<>(
                    Mapping.DomainToNetwork.plant(inValidData),
                    HttpStatus.UNPROCESSABLE_ENTITY
            );
        }

        try {
            Plant result = service.createPlant(new Plant(plantData.getName()));
            return new ResponseEntity<>(Mapping.DomainToNetwork.plant(result), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> deletePlantById(Integer id) {
        boolean isDeletionSuccessful = service.deletePlant(Long.valueOf(id));
        return isDeletionSuccessful ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
