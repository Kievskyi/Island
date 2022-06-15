package org.example.domains;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Wolf extends Predator {

    private String kind_of_animal = "Wolf";
    private double weight = 50.0;
    private double max_satiety = 8.0;
    private double satiety;
    private int max_amount_in_cell = 30;
    private int maxSpeed = 3;
    private Map<String, Integer> chances_to_kill;

    {
        chances_to_kill = new HashMap<>() {{
            put("Bear", 0);
            put("Snake", 0);
            put("Fox", 0);
            put("Eagle", 0);
            put("Horse", 10);
            put("Deer", 15);
            put("Rabbit", 60);
            put("Mouse", 80);
            put("Goat", 60);
            put("Sheep", 70);
            put("Boar", 15);
            put("Buffalo", 10);
            put("Duck", 40);
            put("Caterpillar", 0);
            put("Plant", 0);
        }};
    }

    @Override
    public void eat() {

    }

    @Override
    public void move() {

    }

    @Override
    public void reproduce() {

    }
}