package org.example.domains;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.AnimalIcons;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Bear extends Predator {

    //    private AnimalIcons icon = AnimalIcons.BEAR.getIcon();
    private String kind_of_animal = "Bear";
    private double weight = 500.0;
    private double max_satiety = 80.0;
    private double satiety;
    private int max_amount_in_cell = 5;
    private int maxSpeed = 2;
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