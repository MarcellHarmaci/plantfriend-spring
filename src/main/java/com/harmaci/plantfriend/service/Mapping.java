package com.harmaci.plantfriend.service;

import com.harmaci.plantfriend.repository.model.Plant;

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
