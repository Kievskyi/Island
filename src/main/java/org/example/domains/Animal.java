package org.example.domains;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.AnimalIcons;

@Getter
@Setter
public abstract class Animal {

    private AnimalIcons icon;
    private String kind_of_animal;
    private double weight;
    private double max_satiety;
    private int max_amount_in_cell;
    private int speed;

    public abstract void eat();

    public abstract void move();

    public abstract void reproduce();

}