package com.harmaci.plantfriend.repository.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Accessors(fluent = true)
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plant_seq_gen")
//    @SequenceGenerator(name = "plant_seq_gen", sequenceName = "plant_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "species_id", foreignKey = @ForeignKey(name = "SPECIES_ID_FK"), nullable = true)
    private Species species;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Watering> waterings = new ArrayList<>();

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


