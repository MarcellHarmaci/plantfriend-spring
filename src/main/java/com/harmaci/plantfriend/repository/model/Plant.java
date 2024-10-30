package com.harmaci.plantfriend.repository.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.openapitools.model.PlantData;

import java.util.Objects;

@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plant_seq_gen")
    @SequenceGenerator(name = "plant_seq_gen", sequenceName = "plant_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    /**
     * Default constructor for Interceptor
     */
    public Plant() {
    }

    /**
     * Constructor for a Habit without an id
     */
    public Plant(String name) {
        this.name = name;
    }

    public Plant(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Plant(Long id, PlantData plantData) {
        this.id = id;
        this.name = plantData.getName();
    }

    public Plant(PlantData plantData) {
        this.name = plantData.getName();
    }

    public Plant id(Long id) {
        this.id = id;
        return this;
    }

    @NotNull
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public Plant name(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Plant plant)) {
            return false;
        }

        return Objects.equals(this.id, plant.id) &&
                Objects.equals(this.name, plant.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Habit {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}


