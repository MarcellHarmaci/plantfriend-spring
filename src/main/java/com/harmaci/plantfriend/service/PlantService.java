package com.harmaci.plantfriend.service;

import com.harmaci.plantfriend.repository.PlantRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {

    private final PlantRepository repository;

    public PlantService(PlantRepository repository) {
        this.repository = repository;
    }

    public List<org.openapitools.model.Plant> findAll() {
        return repository.findAll().stream().map(Mapping.DomainToNetwork::plant).toList();
    }
}
