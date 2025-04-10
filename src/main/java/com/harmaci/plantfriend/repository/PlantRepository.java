package com.harmaci.plantfriend.repository;

import com.harmaci.plantfriend.repository.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Long> {
}
