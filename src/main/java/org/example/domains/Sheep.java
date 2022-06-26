package org.example.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Sheep extends Herbivore {

    private double satiety;
    private int max_amount_in_cell = 140;
    private int leftAlive;

    {
        satiety = ThreadLocalRandom.current().nextDouble(4, 11);
        leftAlive = ThreadLocalRandom.current().nextInt(40, max_amount_in_cell + 1);
    }

    public Sheep() {
        super.setSatiety(satiety);
        super.setMax_amount_in_cell(max_amount_in_cell);
        super.setLeftAlive(leftAlive);
    }
}