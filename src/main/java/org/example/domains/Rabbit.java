package org.example.domains;

import lombok.Getter;

@Getter
public class Rabbit extends Herbivore{

    public Rabbit(double weight, double max_satiety, int max_amount_in_cell, int speed) {
        super(weight, max_satiety, max_amount_in_cell, speed);
    }
}
