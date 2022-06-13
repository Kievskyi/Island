package org.example.domains;

import lombok.Getter;

@Getter
public class Mouse extends Herbivore{

    public Mouse(double weight, double max_satiety, int max_amount_in_cell, int speed) {
        super(weight, max_satiety, max_amount_in_cell, speed);
    }
}
