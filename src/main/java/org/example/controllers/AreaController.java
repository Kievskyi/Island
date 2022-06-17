package org.example.controllers;

import org.example.dao.AnimalData;
import org.example.dao.AreaData;
import org.example.domains.Cell;
import org.example.domains.Plant;

import java.util.concurrent.ThreadLocalRandom;


public class AreaController implements Runnable {

    private AreaData areaData = AreaData.getInstance();
    private Plant plant = new Plant();
    private AnimalData animalData = new AnimalData();


    @Override
    public void run() {
        deNextRound();
    }

    public synchronized void deNextRound() {
        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                int animalsValueInCell = areaData.getArea()[i][j].getAnimal().size();
                if (animalsValueInCell == 1) {
                    areaData.getArea()[i][j].getAnimal().get(0).move(areaData.getArea()[i][j].getAnimal().get(0), i, j);
                } else if (animalsValueInCell > 1) {
                    for (int k = 0; k < animalsValueInCell; k++) {
                        areaData.getArea()[i][j].getAnimal().get(k).eat(areaData.getArea()[i][j].getAnimal().get(k), i, j);
                    }
                }
            }
        }
        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                areaData.getArea()[i][j].showCell();
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }


    public void generateArea() {

        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                areaData.getArea()[i][j] = new Cell();
            }
        }
    }

    public void generatePlants() {

        if (plant.getMax_amount_on_area() == 0) {
            plant.setMax_amount_on_area(30);
        }

        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                int random = ThreadLocalRandom.current().nextInt(0, 101);

                if (random <= 2 && plant.getValue_on_area() < plant.getMax_amount_on_area()) {
                    areaData.getArea()[i][j].setPlant(new Plant());
                    plant.setValue_on_area(plant.getValue_on_area() + 1);
                }
            }
        }
    }

    public void generateAnimals() {
        int maxCounter = 15;
        int counter = 0;

        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                int random = ThreadLocalRandom.current().nextInt(0, 140);

                if (random <= 1 && areaData.getArea()[i][j].getAnimal().size() == 0 && areaData.getArea()[i][j].getPlant() == null && counter < maxCounter) {
                    areaData.getArea()[i][j].setAnimal(animalData.getAnimalList().get(counter));
                    counter++;
                }

            }
        }

        if (counter != 14) {
            for (int i = 0; i < areaData.getArea().length; i++) {
                for (int j = 0; j < areaData.getArea()[i].length; j++) {
                    int random = ThreadLocalRandom.current().nextInt(0, 100);

                    if (random <= 5 && areaData.getArea()[i][j].getAnimal() == null && areaData.getArea()[i][j].getPlant() == null && counter < maxCounter) {
                        areaData.getArea()[i][j].setAnimal(animalData.getAnimalList().get(counter));
                        counter++;
                    }

                }
            }
        }
        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                areaData.getArea()[i][j].showCell();
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public AreaData getAreaData() {
        return areaData;
    }

    public void setAreaData(Cell[][] areaSize) {
        this.areaData.setArea(areaSize);
    }
}