package com.harmaci.plantfriend.controller;

import com.harmaci.plantfriend.repository.model.Plant;
import com.harmaci.plantfriend.repository.model.Watering;

import javax.annotation.Nonnull;

public class Mapping {

    /**
     * Mappings from domain models to network models
     */
    public static class DomainToNetwork {
        public static org.openapitools.model.Plant plant(Plant domainModel) {
            return new org.openapitools.model.Plant(
                    domainModel.id(),
                    domainModel.name()
            );
        }

        public static org.openapitools.model.Watering watering(Watering domainModel) {
            org.openapitools.model.Watering networkModel = new org.openapitools.model.Watering(
                    domainModel.plant().id(),
                    domainModel.date(),
                    domainModel.comment()
            );
            networkModel.setId(domainModel.id());
            return networkModel;
        }

        public static org.openapitools.model.PlantWatering plantWatering(Watering domainModel) {
            return new org.openapitools.model.PlantWatering(
                    domainModel.id(),
                    domainModel.date(),
                    domainModel.comment()
            );
        }
    }

    /**
     * Mappings from network models to domain models
     */
    public static class NetworkToDomain {
        public static Plant plant(@Nonnull org.openapitools.model.Plant networkModel) {
            return new Plant(
                    networkModel.getId(),
                    networkModel.getName()
            );
        }
    }

}
