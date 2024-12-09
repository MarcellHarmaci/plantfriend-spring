package com.harmaci.plantfriend.repository.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "species", cascade = CascadeType.DETACH, orphanRemoval = false)
    private List<Plant> plants = new ArrayList<>();

    public Species() {
    }

    public Species(String name) {
        this.name = name;
    }

    public Species(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}


