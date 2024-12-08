package com.harmaci.plantfriend.repository.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Watering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "watering_seq_gen")
//    @SequenceGenerator(name = "watering_seq_gen", sequenceName = "watering_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plant_id", foreignKey = @ForeignKey(name = "PLANT_ID_FK"))
    private Plant plant;

    private LocalDate date;

    private String comment;

    public Watering() {
    }

    public Watering(Plant plant, LocalDate date, String comment) {
        this.plant = plant;
        this.date = date;
        this.comment = comment;
    }
}


