package org.example.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Eagle extends Predator{

    private String kind_of_animal = "Eagle";
    private double weight = 6.0;
    private double max_satiety = 1.0;
    private double satiety;
    private int max_amount_in_cell = 20;
    private int maxSpeed = 3;
    private Map<String, Integer> chances_to_kill;

    {
        chances_to_kill = new HashMap<>() {{
            put("Wolf", 0);
            put("Snake", 80);
            put("Fox", 10);
            put("Bear", 0);
            put("Horse", 0);
            put("Deer", 0);
            put("Rabbit", 90);
            put("Mouse", 90);
            put("Goat", 0);
            put("Sheep", 0);
            put("Boar", 0);
            put("Buffalo", 0);
            put("Duck", 80);
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
