package org.example.domains;

public class Animal {

    double weight;
    double max_satiety;
    int max_amount_in_cell;
    int speed;

    public Animal(double weight, double max_satiety, int max_amount_in_cell, int speed) {
        this.weight = weight;
        this.max_satiety = max_satiety;
        this.max_amount_in_cell = max_amount_in_cell;
        this.speed = speed;
    }

    public void eat() {

    }

    public void move() {

    }

    public void reproduce() {

    }

    @Override
    public String toString() {
        return "Animal{" +
                "weight=" + weight +
                ", max_satiety=" + max_satiety +
                ", max_amount_in_cell=" + max_amount_in_cell +
                ", speed=" + speed +
                '}';
    }
}
