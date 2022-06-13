package org.example.domains;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
public class Wolf extends Predator {

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