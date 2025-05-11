package com.harmaci.plantfriend.repository.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Entity
@Data
@Accessors(fluent = true)
public class Watering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "watering_seq_gen")
//    @SequenceGenerator(name = "watering_seq_gen", sequenceName = "watering_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plant_id", foreignKey = @ForeignKey(name = "PLANT_ID_FK"), nullable = false)
    private Plant plant;

    @Column(nullable = false)
    private LocalDate date;

    /**
     * An integer describing the health of the plant. The higher, the better. Domain: [1:5]
     */
    @Column(nullable = false)
    private Integer plantHealth;

    @Column(nullable = true)
    private String comment;

    public Watering() {
    }

    public Watering(Long id, Plant plant, LocalDate date, Integer plantHealth, String comment) {
        this.id = id;
        this.date = date;
        this.plantHealth = plantHealth;
        this.comment = comment;
    }

    public Watering(Plant plant, LocalDate date, Integer plantHealth, String comment) {
        this.plant = plant;
        this.date = date;
        this.plantHealth = plantHealth;
        this.comment = comment;
    }
}


