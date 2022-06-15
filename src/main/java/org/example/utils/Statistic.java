package org.example.utils;

import org.example.domains.Animal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class Statistic {

    private int total_number_of_animals_on_area;
//    private int total_alive_types;
    private LocalDateTime total_simulation_time = LocalDateTime.now();  //TODO make a correct type
    private List<Animal> satiety;


}
