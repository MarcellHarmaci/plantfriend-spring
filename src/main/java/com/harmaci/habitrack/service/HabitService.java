package com.harmaci.habitrack.service;

import com.harmaci.habitrack.controller.Mapping;
import com.harmaci.habitrack.repository.HabitRepository;
import org.openapitools.model.Habit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {

    private final HabitRepository repository;

    public HabitService(HabitRepository repository) {
        this.repository = repository;
    }

    public List<Habit> findAll() {
        return repository.findAll().stream().map(Mapping::habit).toList();
    }
}
