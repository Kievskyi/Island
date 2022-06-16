package org.example.domains;

import org.example.utils.AnimalIcons;

public class Plant {

    private String icon = AnimalIcons.PLANT.getIcon();
    private String kind_of_animal = "Plant";
    private int max_amount_in_cell = 200;
    private int max_amount_on_area;
    private int value_on_area;
    private int weight = 1;

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

    public int getMax_amount_in_cell() {
        return max_amount_in_cell;
    }

    public void setMax_amount_in_cell(int max_amount_in_cell) {
        this.max_amount_in_cell = max_amount_in_cell;
    }

    public int getMax_amount_on_area() {
        return max_amount_on_area;
    }

    public void setMax_amount_on_area(int max_amount_on_area) {
        this.max_amount_on_area = max_amount_on_area;
    }

    public int getValue_on_area() {
        return value_on_area;
    }

    public void setValue_on_area(int value_on_area) {
        this.value_on_area = value_on_area;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}