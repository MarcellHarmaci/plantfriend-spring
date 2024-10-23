package com.harmaci.plantfriend.service;

import com.harmaci.plantfriend.repository.model.Plant;
//import org.openapitools.model.Plant;


public class Mapping {

    /**
     * Mappings from domain model to network model
     */
    public static class DomainToNetwork {
        public static org.openapitools.model.Plant plant(Plant dataModel) {
            return new org.openapitools.model.Plant(
                    dataModel.getId(),
                    dataModel.getName()
            );
        }
    }

    /**
     * Mappings from network model to domain model
     */
    public static class NetworkToDomain {
        public static Plant plant(org.openapitools.model.Plant domainModel) {
            return new Plant(
                    domainModel.getId(),
                    domainModel.getName()
            );
        }
    }

}
