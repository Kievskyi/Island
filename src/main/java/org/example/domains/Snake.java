package org.example.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Snake extends Predator {

    private String kind_of_animal = "Snake";
    private double weight = 15.0;
    private double max_satiety = 3.0;
    private double satiety;
    private int max_amount_in_cell = 30;
    private int maxSpeed = 1;
    private Map<String, Integer> chances_to_kill;

    {
        chances_to_kill = new HashMap<>() {{
            put("Wolf", 0);
            put("Bear", 0);
            put("Fox", 15);
            put("Eagle", 0);
            put("Horse", 0);
            put("Deer", 0);
            put("Rabbit", 20);
            put("Mouse", 40);
            put("Goat", 0);
            put("Sheep", 0);
            put("Boar", 0);
            put("Buffalo", 0);
            put("Duck", 10);
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