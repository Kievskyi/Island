package org.example.utils;

import org.example.domains.Animal;

import java.time.LocalDateTime;
import java.util.List;

public class Statistic implements Runnable {

    private int total_number_of_animals_on_area;
//    private int total_alive_types;
    private LocalDateTime total_simulation_time = LocalDateTime.now();  //TODO make a correct type
    private List<Animal> satiety;
    int i = 0;
    String b = "________";
    int c = 1;


    @Override
    public void run() {

        System.out.println(i);
        System.out.println(b);
        System.out.println(c);
        i++;
        c++;
    }
}
