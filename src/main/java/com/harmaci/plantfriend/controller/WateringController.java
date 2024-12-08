package com.harmaci.plantfriend.controller;

import org.openapitools.api.WateringsApiController;
import org.openapitools.model.AddPlantWateringRequest;
import org.openapitools.model.PlantWatering;
import org.openapitools.model.Watering;
import org.openapitools.model.WateringUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.time.LocalDate;
import java.util.List;

public class WateringController extends WateringsApiController {
    public WateringController(NativeWebRequest request) {
        super(request);
    }

    @Override
    public ResponseEntity<List<Watering>> getRecentWaterings(Long offset, Integer limit) {
        // TODO implement endpoint
        return super.getRecentWaterings(offset, limit);
    }

    @Override
    public ResponseEntity<List<PlantWatering>> getWateringsByPlant(Long id) {
        // TODO implement endpoint
        return super.getWateringsByPlant(id);
    }

    @Override
    public ResponseEntity<List<LocalDate>> getWateringDatesByPlant(Long id) {
        // TODO implement endpoint
        return super.getWateringDatesByPlant(id);
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
