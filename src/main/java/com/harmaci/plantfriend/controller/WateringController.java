package com.harmaci.plantfriend.controller;

import com.harmaci.plantfriend.service.PlantService;
import com.harmaci.plantfriend.service.WateringService;
import org.openapitools.api.WateringsApiController;
import org.openapitools.model.AddPlantWateringRequest;
import org.openapitools.model.PlantWatering;
import org.openapitools.model.Watering;
import org.openapitools.model.WateringUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import java.time.LocalDate;
import java.util.List;

@Controller
public class WateringController extends WateringsApiController {
    @Autowired
    private WateringService service;
    @Autowired
    private PlantService plantService;

    public WateringController(NativeWebRequest request) {
        super(request);
    }

    @Override
    public ResponseEntity<List<Watering>> getRecentWaterings(Integer offset, Integer limit) {
        List<Watering> recentWaterings = service.getRecentWaterings(offset, limit)
                .stream()
                .map(Mapping.DomainToNetwork::watering)
                .toList();
        return new ResponseEntity<>(recentWaterings, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PlantWatering>> getWateringsByPlant(Long id) {
        if (!plantService.plantExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<PlantWatering> wateringsOfPlant = service.getWateringsByPlantId(id)
                .stream()
                .map(Mapping.DomainToNetwork::plantWatering)
                .toList();
        return new ResponseEntity<>(wateringsOfPlant, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LocalDate>> getWateringDatesByPlant(Long id) {
        if (!plantService.plantExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<LocalDate> wateringDates = service.getWateringDatesByPlantId(id);
        return new ResponseEntity<>(wateringDates, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlantWatering> addPlantWatering(Long id, AddPlantWateringRequest addPlantWateringRequest) {
        // TODO implement endpoint
        return super.addPlantWatering(id, addPlantWateringRequest);
    }

    @Override
    public ResponseEntity<Watering> updateWateringById(Long id, WateringUpdate wateringUpdate) {
        // TODO implement endpoint
        return super.updateWateringById(id, wateringUpdate);
    }

    @Override
    public ResponseEntity<Void> deleteWateringById(Long id) {
        // TODO implement endpoint
        return super.deleteWateringById(id);
    }
}
