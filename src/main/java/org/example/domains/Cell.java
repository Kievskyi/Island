package org.example.domains;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.AnimalIcons;


public class Cell {

    private String groundIcon = AnimalIcons.GROUND.getIcon();
    private Animal animal;
    private Plant plant;

    public void showCell() {
        if (animal == null && plant == null) {
            System.out.print(groundIcon);
        } else if (animal != null) {
            System.out.print(animal.getIcon());
        } else if (plant != null) {
            System.out.print(plant.getIcon());
        }
    }

    public String getGroundIcon() {
        return groundIcon;
    }

    public void setGroundIcon(String groundIcon) {
        this.groundIcon = groundIcon;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

}
