package org.example.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Buffalo extends Herbivore {

    private double satiety;
    private int max_amount_in_cell = 10;
    private int leftAlive;

    {
        satiety = ThreadLocalRandom.current().nextDouble(30, 70);
        leftAlive = ThreadLocalRandom.current().nextInt(3, max_amount_in_cell + 1);
    }

    public Buffalo() {
        super.setSatiety(satiety);
        super.setMax_amount_in_cell(max_amount_in_cell);
        super.setLeftAlive(leftAlive);
    }
}