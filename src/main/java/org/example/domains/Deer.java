package org.example.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Deer extends Herbivore {

    private double satiety;
    private int max_amount_in_cell = 20;
    private int leftAlive;
    private int maxSpeed = 4;

    {
        satiety = ThreadLocalRandom.current().nextDouble(25, 35);
        leftAlive = ThreadLocalRandom.current().nextInt(5, max_amount_in_cell + 1);
    }

    public Deer() {
        super.setSatiety(satiety);
        super.setMax_amount_in_cell(max_amount_in_cell);
        super.setMaxSpeed(maxSpeed);
    }
}