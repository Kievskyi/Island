package org.example.domains;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.AnimalIcons;

import java.util.ArrayList;


public class Cell {

    private String groundIcon = AnimalIcons.GROUND.getIcon();
    private ArrayList<Animal> animal = new ArrayList<>();
    private Plant plant;

    public void showCell() {
        if (animal.size() == 0 && plant == null) {
            System.out.print(groundIcon);
        } else if (animal.size() == 1 && plant == null) {
            System.out.print(animal.get(0).getIcon());
        }else if (animal.size() > 1 && plant == null) {
            System.out.print(AnimalIcons.BATTLE.getIcon());
        } else if (animal.size() == 0 && plant != null) {
            System.out.print(plant.getIcon());
        }
    }

    public String getGroundIcon() {
        return groundIcon;
    }

    public void setGroundIcon(String groundIcon) {
        this.groundIcon = groundIcon;
    }

//    public Animal getAnimal() {
//        return animal;
//    }
//
//    public void setAnimal(Animal animal) {
//        this.animal = animal;
//    }


    public ArrayList<Animal> getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal.add(animal);
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

}
