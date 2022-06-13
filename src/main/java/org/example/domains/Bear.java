package org.example.domains;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Bear extends Predator {

    private double weight;
    private double max_satiety;
    private int max_amount_in_cell;
    private int speed;

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