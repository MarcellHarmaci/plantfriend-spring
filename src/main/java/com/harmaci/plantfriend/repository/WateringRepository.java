package com.harmaci.plantfriend.repository;

import com.harmaci.plantfriend.repository.model.Watering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WateringRepository extends JpaRepository<Watering, Long> {
}
