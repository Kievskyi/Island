package org.example.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Caterpillar extends Herbivore {

    private double satiety;
    private int max_amount_in_cell = 1000;
    private int leftAlive;

    {
        leftAlive = ThreadLocalRandom.current().nextInt(300, max_amount_in_cell + 1);
    }

    public Caterpillar() {
        super.setSatiety(satiety);
        super.setMax_amount_in_cell(max_amount_in_cell);
        super.setLeftAlive(leftAlive);
    }
}