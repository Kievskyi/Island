package org.example.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Mouse extends Herbivore {

    private String kind_of_animal = "Mouse";
    private double weight = 0.05;
    private double max_satiety = 0.01;
    private double satiety;
    private int max_amount_in_cell = 500;
    private int maxSpeed = 1;
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
            put("Bear", 0);
            put("Goat", 0);
            put("Sheep", 0);
            put("Boar", 0);
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