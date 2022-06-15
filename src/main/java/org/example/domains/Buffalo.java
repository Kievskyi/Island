package org.example.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Buffalo extends Herbivore{

    private String kind_of_animal = "Buffalo";
    private double weight = 700.0;
    private double max_satiety = 100.0;
    private double satiety;
    private int max_amount_in_cell = 10;
    private int maxSpeed = 3;
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
            put("Mouse", 0);
            put("Goat", 0);
            put("Sheep", 0);
            put("Boar", 0);
            put("Bear", 0);
            put("Duck", 0);
            put("Caterpillar", 0);
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
