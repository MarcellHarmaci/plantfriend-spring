package com.harmaci.plantfriend.controller;

import com.harmaci.plantfriend.repository.model.Plant;
import com.harmaci.plantfriend.service.Mapping;
import com.harmaci.plantfriend.service.PlantService;
import org.openapitools.model.PlantData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

@Controller
public class PlantApiController extends org.openapitools.api.PlantApiController {
    private final PlantService service;

    public PlantApiController(NativeWebRequest request, PlantService service) {
        super(request);
        this.service = service;
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
            PlantData plantData
    ) {
        Plant oldPlant = service.getPlantById(id);
        if (oldPlant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Plant result = service.savePlant(new Plant(id, plantData));
        return new ResponseEntity<>(Mapping.DomainToNetwork.plant(result), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<org.openapitools.model.Plant> addPlant(PlantData plantData) {
        Plant result = service.savePlant(new Plant(plantData));
        return new ResponseEntity<>(Mapping.DomainToNetwork.plant(result), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deletePlantById(Integer id) {
        // TODO implement method
        return super.deletePlantById(id);
    }

}
