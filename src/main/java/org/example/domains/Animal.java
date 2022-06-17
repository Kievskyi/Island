package org.example.domains;


import org.example.dao.AreaData;

public abstract class Animal {

    private String icon;
    private String kind_of_animal;
    private double weight;
    private double max_satiety;
    private int max_amount_in_cell;
    private int speed;
    private AreaData areaData;

    public abstract void eat(Animal animal, int length, int width);

    public abstract void move(Animal animal, int width, int length);

    public abstract void reproduce(AreaData areaData);

    public AreaData getAreaData() {
        return areaData;
    }

    public void setAreaData(AreaData areaData) {
        this.areaData = areaData;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getKind_of_animal() {
        return kind_of_animal;
    }

    public void setKind_of_animal(String kind_of_animal) {
        this.kind_of_animal = kind_of_animal;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getMax_satiety() {
        return max_satiety;
    }

    public void setMax_satiety(double max_satiety) {
        this.max_satiety = max_satiety;
    }

    public int getMax_amount_in_cell() {
        return max_amount_in_cell;
    }

    public void setMax_amount_in_cell(int max_amount_in_cell) {
        this.max_amount_in_cell = max_amount_in_cell;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}