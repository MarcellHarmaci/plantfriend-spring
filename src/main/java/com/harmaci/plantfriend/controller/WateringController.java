package com.harmaci.plantfriend.controller;

import com.harmaci.plantfriend.service.PlantService;
import com.harmaci.plantfriend.service.WateringService;
import jakarta.persistence.EntityNotFoundException;
import org.openapitools.api.WateringsApiController;
import org.openapitools.model.Watering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public ResponseEntity<List<Watering>> getWateringsByPlant(Long id) {
        if (!plantService.plantExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Watering> wateringsOfPlant = service.getWateringsByPlantId(id)
                .stream()
                .map(Mapping.DomainToNetwork::watering)
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
    public ResponseEntity<Watering> addPlantWatering(Long id, Watering wateringToAdd) {
        if (!plantService.plantExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (wateringToAdd.getDate().isAfter(LocalDate.now())) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            com.harmaci.plantfriend.repository.model.Watering watering = service.createWatering(
                    id,
                    wateringToAdd.getDate(),
                    wateringToAdd.getPlantHealth(),
                    wateringToAdd.getComment().orElse(null)
            );
            return new ResponseEntity<>(
                    Mapping.DomainToNetwork.watering(watering),
                    HttpStatus.OK
            );
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity<Watering> updateWateringById(Long id, Watering updatedWatering) {
        if (!id.equals(updatedWatering.getId())) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            com.harmaci.plantfriend.repository.model.Watering wateringWithoutPlant =
                    Mapping.NetworkToDomain.watering(updatedWatering);
            Watering resultingWatering = Mapping.DomainToNetwork.watering(
                    service.updateWatering(wateringWithoutPlant, updatedWatering.getPlantId())
            );
            return new ResponseEntity<>(resultingWatering, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity<Void> deleteWateringById(Long id) {
        boolean isDeleted = service.deleteWateringById(id);
        return new ResponseEntity<>(isDeleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
