package org.example.domains;

public abstract class Animal {

    private double weight;
    private double max_satiety;
    private int max_amount_in_cell;
    private int speed;

    public abstract void eat();

    public abstract void move();

    public abstract void reproduce();

}