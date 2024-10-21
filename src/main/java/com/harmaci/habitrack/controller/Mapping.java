package com.harmaci.habitrack.controller;

import org.openapitools.model.Habit;

public class Mapping {
    public static Habit habit(com.harmaci.habitrack.repository.model.Habit dataModel) {
        return new Habit(
                dataModel.getId(),
                dataModel.getName()
        );
    }
}
