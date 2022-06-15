package org.example.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Duck extends Herbivore {

    private String kind_of_animal = "Duck";
    private double weight = 1.0;
    private double max_satiety = 0.15;
    private double satiety;
    private int max_amount_in_cell = 200;
    private int maxSpeed = 4;
    private Map<String, Integer> chances_to_kill;

    {
        chances_to_kill = new HashMap<>() {{
            put("Wolf", 0);
            put("Snake", 80);
            put("Fox", 0);
            put("Eagle", 0);
            put("Horse", 40);
            put("Deer", 80);
            put("Rabbit", 80);
            put("Mouse", 90);
            put("Goat", 70);
            put("Sheep", 70);
            put("Boar", 50);
            put("Buffalo", 20);
            put("Bear", 10);
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
