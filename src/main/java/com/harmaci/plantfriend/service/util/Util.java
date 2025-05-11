package com.harmaci.plantfriend.service.util;

import jakarta.persistence.EntityNotFoundException;

import java.util.function.Supplier;

public class Util {
    public static Supplier<EntityNotFoundException> getEnfException(String entity, Long id) {
        return () -> new EntityNotFoundException("No " + entity + " found by id: " + id);
    };
}
