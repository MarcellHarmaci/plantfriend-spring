package com.harmaci.habitrack.repository;

import com.harmaci.habitrack.repository.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<Habit, Long> {
}
