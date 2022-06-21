package org.example.domains;

import org.example.utils.AnimalIcons;

import java.util.ArrayList;


public class Cell {

    private final String groundIcon = AnimalIcons.GROUND.getIcon();
    private ArrayList<Animal> animals_in_cell = new ArrayList<>();
    private Plant plant;

    public void showCell() {
        for (int i = 0; i < animals_in_cell.size(); i++) {

        }
        if (animals_in_cell.isEmpty() && plant == null) {
            System.out.print(groundIcon);
        } else if (isPredators() && animals_in_cell.size() > 1) {
            System.out.print(AnimalIcons.FEWPREDATORS.getIcon());
        } else if (animals_in_cell.size() == 1 && plant == null) {
            System.out.print(animals_in_cell.get(0).getIcon());
//        } else if (animals_in_cell.size() > 1 && plant == null) {
//            System.out.print(AnimalIcons.BATTLE.getIcon());
        } else if (animals_in_cell.size() == 0 && plant != null) {
            System.out.print(plant.getIcon());
        } else if (isHerbivores() && plant != null) {
            System.out.print(AnimalIcons.EATPLANT.getIcon());
        }
    }

    public boolean isHerbivores() {
        ArrayList<Animal> herbivores = new ArrayList<>();
        boolean isTrue = true;

        for (int i = 0; i < animals_in_cell.size(); i++) {
            if (animals_in_cell.get(i).getClass().getSuperclass() == Herbivore.class) {
                herbivores.add(animals_in_cell.get(i));
            }
        }

        if (herbivores.size() == animals_in_cell.size()) {
            isTrue = true;
        } else {
            isTrue = false;
        }

        return isTrue;
    }

    public boolean isPredAndHerbInCell() {
        ArrayList<Animal> predators = new ArrayList<>();
        ArrayList<Animal> herbivore = new ArrayList<>();
        boolean isTrue = true;

        for (int i = 0; i < animals_in_cell.size(); i++) {
            if (animals_in_cell.get(i).getClass().getSuperclass() == Predator.class) {
                predators.add(animals_in_cell.get(i));
            } else if (animals_in_cell.get(i).getClass().getSuperclass() == Herbivore.class) {
                herbivore.add(animals_in_cell.get(i));
            }

            if (predators.size() > 1 && herbivore.size() > 1) {
                isTrue = true;
            } else {
                isTrue = false;
            }
        }
        return isTrue;
    }


    public boolean isPredators() {
        ArrayList<Animal> predators = new ArrayList<>();
        for (int i = 0; i < animals_in_cell.size(); i++) {
            if (animals_in_cell.get(i).getClass().getSuperclass() == Predator.class)
                predators.add(animals_in_cell.get(i));
        }
        if (!predators.isEmpty() && predators.size() == animals_in_cell.size()) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Animal> getAnimals_in_cell() {
        return animals_in_cell;
    }

    public void setAnimals_in_cell(Animal animals_in_cell) {
        this.animals_in_cell.add(animals_in_cell);
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}