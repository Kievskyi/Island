package org.example.domains;

import org.example.utils.AnimalIcons;

import java.util.HashMap;
import java.util.Map;

public class Duck extends Herbivore {

    private String kind_of_animal = "Duck";
    private String icon = AnimalIcons.DUCK.getIcon();
    private double weight = 1.0;
    private double max_satiety = 0.15;
    private double satiety;
    private int max_amount_in_cell = 200;
    private int maxSpeed = 4;
    private Map<String, Integer> chances_to_kill;

    {
        chances_to_kill = new HashMap<>() {{
            put("Wolf", 0);
            put("Snake", 80);
            put("Fox", 0);
            put("Eagle", 0);
            put("Horse", 40);
            put("Deer", 80);
            put("Rabbit", 80);
            put("Mouse", 90);
            put("Goat", 70);
            put("Sheep", 70);
            put("Boar", 50);
            put("Buffalo", 20);
            put("Bear", 10);
            put("Caterpillar", 90);
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
