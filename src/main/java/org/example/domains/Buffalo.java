package org.example.domains;

import org.example.utils.AnimalIcons;

import java.util.HashMap;
import java.util.Map;

public class Buffalo extends Herbivore{

    private String kind_of_animal = "Buffalo";
    private String icon = AnimalIcons.BUFFALO.getIcon();
    private double weight = 700.0;
    private double max_satiety = 100.0;
    private double satiety;
    private int max_amount_in_cell = 10;
    private int maxSpeed = 3;
    private Map<String, Integer> chances_to_kill;

    {
        chances_to_kill = new HashMap<>() {{
            put("Wolf", 0);
            put("Snake", 0);
            put("Fox", 0);
            put("Eagle", 0);
            put("Horse", 0);
            put("Deer", 0);
            put("Rabbit", 0);
            put("Mouse", 0);
            put("Goat", 0);
            put("Sheep", 0);
            put("Boar", 0);
            put("Bear", 0);
            put("Duck", 0);
            put("Caterpillar", 0);
            put("Plant", 100);
        }};
    }

    @Override
    public void eat() {

    }

    @Override
    public void move() {

    }

    @Override
    public void reproduce() {

    }

    @Override
    public String getKind_of_animal() {
        return kind_of_animal;
    }

    @Override
    public void setKind_of_animal(String kind_of_animal) {
        this.kind_of_animal = kind_of_animal;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getMax_satiety() {
        return max_satiety;
    }

    @Override
    public void setMax_satiety(double max_satiety) {
        this.max_satiety = max_satiety;
    }

    public double getSatiety() {
        return satiety;
    }

    public void setSatiety(double satiety) {
        this.satiety = satiety;
    }

    @Override
    public int getMax_amount_in_cell() {
        return max_amount_in_cell;
    }

    @Override
    public void setMax_amount_in_cell(int max_amount_in_cell) {
        this.max_amount_in_cell = max_amount_in_cell;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Map<String, Integer> getChances_to_kill() {
        return chances_to_kill;
    }

    public void setChances_to_kill(Map<String, Integer> chances_to_kill) {
        this.chances_to_kill = chances_to_kill;
    }
}
