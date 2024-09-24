package com.harmaci.habitrack.mock;

import com.harmaci.habitrack.repository.HabitRepository;
import com.harmaci.habitrack.repository.model.Habit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(HabitRepository repository) {
        List<String> habits = Arrays.asList(
                "Do the dishes",
                "Read a chapter"
        );

        return args -> habits.forEach(it ->
                log.info("Preloading " + repository.save(new Habit(it)))
        );
    }
}
