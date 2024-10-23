package com.harmaci.plantfriend.controller;

import com.harmaci.plantfriend.service.PlantService;
import org.openapitools.model.Plant;
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
    public ResponseEntity<List<Plant>> getPlants() {
        return new ResponseEntity<>(
                service.findAll(),
                HttpStatus.OK
        );
    }
}
