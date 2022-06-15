package org.example.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Fox extends Predator {

    private String kind_of_animal = "Fox";
    private double weight = 8.0;
    private double max_satiety = 2.0;
    private double satiety;
    private int max_amount_in_cell = 30;
    private int maxSpeed = 2;
    private Map<String, Integer> chances_to_kill;

    {
        chances_to_kill = new HashMap<>() {{
            put("Wolf", 0);
            put("Snake", 0);
            put("Bear", 0);
            put("Eagle", 0);
            put("Horse", 0);
            put("Deer", 0);
            put("Rabbit", 70);
            put("Mouse", 90);
            put("Goat", 0);
            put("Sheep", 0);
            put("Boar", 0);
            put("Buffalo", 0);
            put("Duck", 60);
            put("Caterpillar", 40);
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