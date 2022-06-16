package org.example.domains;

import org.example.dao.AnimalData;

import java.util.concurrent.ThreadLocalRandom;


public class Area implements Runnable {
    private Cell [][] area;
    private Plant plant = new Plant();
    private AnimalData animalData = new AnimalData();

    @Override
    public void run() {
        generatePlants();
    }


    public void generateArea() {

        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                area[i][j] = new Cell();
            }
        }
    }

    public void generatePlants() {
        plant.setMax_amount_on_area(30);
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                int random = ThreadLocalRandom.current().nextInt(0, 101);

                if (random <= 2 && plant.getValue_on_area() < plant.getMax_amount_on_area()) {
                    area[i][j].setPlant(new Plant());
                    plant.setValue_on_area(plant.getValue_on_area() + 1);
                }
            }
        }

        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                area[i][j].showCell();
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public void generateAnimals() {
        int maxCounter = 15;
        int counter = 0;

        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                int random = ThreadLocalRandom.current().nextInt(0, 140);

                if (random <= 1 && area[i][j].getAnimal() == null && area[i][j].getPlant() == null && counter < maxCounter) {
                    area[i][j].setAnimal(animalData.getAnimalList().get(counter));
                    counter++;
                }

            }
        }

        if (counter != 14) {
            for (int i = 0; i < area.length; i++) {
                for (int j = 0; j < area[i].length; j++) {
                    int random = ThreadLocalRandom.current().nextInt(0, 100);

                    if (random <= 5 && area[i][j].getAnimal() == null && area[i][j].getPlant() == null && counter < maxCounter) {
                        area[i][j].setAnimal(animalData.getAnimalList().get(counter));
                        counter++;
                    }

                }
            }
        }
    }

    public Cell[][] getArea() {
        return area;
    }

    public void setArea(Cell[][] area) {
        this.area = area;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public AnimalData getAnimalData() {
        return animalData;
    }

    public void setAnimalData(AnimalData animalData) {
        this.animalData = animalData;
    }


}