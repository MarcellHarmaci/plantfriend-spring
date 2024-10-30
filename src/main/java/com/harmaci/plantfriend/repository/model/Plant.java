package com.harmaci.plantfriend.repository.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plant_seq_gen")
    @SequenceGenerator(name = "plant_seq_gen", sequenceName = "plant_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    public Plant() {
    }

    public Plant(String name) {
        this.name = name;
    }

    public Plant(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}


