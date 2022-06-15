package org.example.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Boar extends Herbivore {

    private String kind_of_animal = "Boar";
    private double weight = 400.0;
    private double max_satiety = 50.0;
    private double satiety;
    private int max_amount_in_cell = 50;
    private int maxSpeed = 2;
    private Map<String, Integer> chances_to_kill;

    {
        chances_to_kill = new HashMap<>() {{
            put("Wolf", 0);
            put("Snake", 0);
            put("Fox", 0);
            put("Eagle", 0);
            put("Horse", 0);
            put("Deer", 0);
            put("Rabbit", 0);
            put("Mouse", 50);
            put("Goat", 0);
            put("Sheep", 0);
            put("Bear", 0);
            put("Buffalo", 0);
            put("Duck", 0);
            put("Caterpillar", 90);
            put("Plant", 100);
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