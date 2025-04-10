package com.harmaci.plantfriend.repository;

import com.harmaci.plantfriend.repository.model.Watering;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WateringRepository extends JpaRepository<Watering, Long> {

    Sort SORT_BY_DATE = Sort.by("date");

    // TODO consider returning
    //  a Page - knows the full page count,
    //  a Slice - knows if there's a next page or
    //  a List - current page only
    List<Watering> findAllByOrderByDateDesc(Pageable pageable);

    List<Watering> findAllByPlantId(long plantId);

}
