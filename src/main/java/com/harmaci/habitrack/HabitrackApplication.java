package com.harmaci.habitrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HabitrackApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(HabitrackApplication.class);
        app.addListeners(new HabitrackApplicationListener());
        app.run(args);
    }

}
