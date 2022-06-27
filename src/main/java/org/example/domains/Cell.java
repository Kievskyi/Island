package org.example.domains;

import org.example.utils.AnimalIcon;

import java.util.ArrayList;

public class Cell {

    private final String groundIcon = AnimalIcon.GROUND.getIcon();
    private ArrayList<Animal> animals_in_cell = new ArrayList<>();
    private Plant plant;

    public void showCell() {

        if (animals_in_cell.isEmpty() && plant == null) {
            System.out.print(groundIcon);
        }
        if (animals_in_cell.size() == 1 && plant == null) {
            System.out.print(animals_in_cell.get(0).getIcon());
        }
        if (animals_in_cell.size() > 1 && plant == null) {
            if (isPredators()) {
                System.out.print(AnimalIcon.PAWS.getIcon());
            } else if (isPredAndHerbInCell()) {
                System.out.print(AnimalIcon.BATTLE.getIcon());
            } else if (isHerbivores()) {
                System.out.print(AnimalIcon.PAWS.getIcon());
            }
        }
        if (animals_in_cell.isEmpty() && plant != null) {
            System.out.print(plant.getIcon());
        }
        if (animals_in_cell.size() == 1 && plant != null) {
            if (animals_in_cell.get(0).getClass().getSuperclass() == Predator.class) {
                System.out.print(plant.getIcon());
            } else if (animals_in_cell.get(0).getClass().getSuperclass() == Herbivore.class) {
                System.out.print(AnimalIcon.PLATE.getIcon());
            }
        }
        if (animals_in_cell.size() > 1 && plant != null) {
            if (isPredators()) {
                System.out.print(AnimalIcon.PAWS.getIcon());
            } else if (isPredAndHerbInCell()) {
                System.out.print(AnimalIcon.BATTLE.getIcon());
            } else if (isHerbivores()) {
                System.out.print(AnimalIcon.PLATE.getIcon());
            }
        }
    }

    public boolean isHerbivores() {
        ArrayList<Animal> herbivores = new ArrayList<>();
        boolean isTrue = true;

        for (int animal = 0; animal < animals_in_cell.size(); animal++) {
            if (animals_in_cell.get(animal).getClass().getSuperclass() == Herbivore.class) {
                herbivores.add(animals_in_cell.get(animal));
            }
        }

        if (herbivores.size() > 1 && herbivores.size() == animals_in_cell.size()) {
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

        for (int animal = 0; animal < animals_in_cell.size(); animal++) {
            if (animals_in_cell.get(animal).getClass().getSuperclass() == Predator.class) {
                predators.add(animals_in_cell.get(animal));
            } else if (animals_in_cell.get(animal).getClass().getSuperclass() == Herbivore.class) {
                herbivore.add(animals_in_cell.get(animal));
            }

            if (!predators.isEmpty() && !herbivore.isEmpty()) {
                isTrue = true;
            } else {
                isTrue = false;
            }
        }
        return isTrue;
    }


    public boolean isPredators() {
        ArrayList<Animal> predators = new ArrayList<>();
        for (int animal = 0; animal < animals_in_cell.size(); animal++) {
            if (animals_in_cell.get(animal).getClass().getSuperclass() == Predator.class)
                predators.add(animals_in_cell.get(animal));
        }
        if (predators.size() > 1 && predators.size() == animals_in_cell.size()) {
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