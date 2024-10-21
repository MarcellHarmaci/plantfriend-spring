package com.harmaci.habitrack.controller;

import com.harmaci.habitrack.service.HabitService;
import org.openapitools.model.Habit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;

@Controller
public class HabitsApiController extends org.openapitools.api.HabitsApiController {
    private final HabitService service;

    public HabitsApiController(NativeWebRequest request, HabitService service) {
        super(request);
        this.service = service;
    }

    @Override
    public ResponseEntity<List<Habit>> getHabits() {
        return new ResponseEntity<>(
                service.findAll(),
                HttpStatus.OK
        );
    }
}
