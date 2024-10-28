package com.harmaci.plantfriend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"org.openapitools.configuration"})
public class PlantFriendApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PlantFriendApplication.class);
        app.addListeners(new PrintEndpointListener());
        app.run(args);
    }

}
