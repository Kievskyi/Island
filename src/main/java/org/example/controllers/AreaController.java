package org.example.controllers;

import org.example.dao.AnimalData;
import org.example.dao.AreaData;
import org.example.domains.Cell;
import org.example.domains.Herbivore;
import org.example.domains.Plant;
import org.example.domains.Predator;

import java.util.concurrent.ThreadLocalRandom;


public class AreaController implements Runnable {

    private AreaData areaData = AreaData.getInstance();
    private Plant plant = new Plant();
    private AnimalData animalData = new AnimalData();


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            deNextRound();

//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
    }

    public synchronized void deNextRound() {
        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                //если на этой клетке есть растение и несколько животных
                if (areaData.getArea()[i][j].getPlant() != null &&
                        areaData.getArea()[i][j].getAnimals_in_cell().size() > 1) {
                    //если один травоядный а другой хищник
                    if (areaData.getArea()[i][j].isPredAndHerbInCell()) {
                        //каждый хищник должен попробовать съесть травоядного до момента полного насыщения
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            //если эивотное хищник - то есть животного
                            if (areaData.getArea()[i][j].getAnimals_in_cell().get(k).getClass().getSuperclass() == Predator.class) {
                                areaData.getArea()[i][j].getAnimals_in_cell().get(k).eat(areaData.getArea()[i][j].getAnimals_in_cell().get(k), i, j);


                                //если эивотное травоядное - есть растение
                            } else if (areaData.getArea()[i][j].getAnimals_in_cell().get(k).getClass().getSuperclass() == Herbivore.class) {
                                areaData.getArea()[i][j].getAnimals_in_cell().get(k).eat(areaData.getArea()[i][j].getPlant(), i, j);
                            }
                        }
                        //если оба животных травоядные
                    } else if (areaData.getArea()[i][j].isHerbivores()) {
                        //то они просто едят до полного насыщения
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            areaData.getArea()[i][j].getAnimals_in_cell().get(k).eat(areaData.getArea()[i][j].getPlant(), i, j);
                            areaData.getArea()[i][j].getAnimals_in_cell().get(k).move(areaData.getArea()[i][j].getAnimals_in_cell().get(k), i, j);

                        }
                        //если оба животных хищники
                    } else if (areaData.getArea()[i][j].isPredators()) {
                        //то вызывыается метод move()
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            areaData.getArea()[i][j].getAnimals_in_cell().get(k).move(areaData.getArea()[i][j].getAnimals_in_cell().get(k), i, j);
                        }
                    }
                    //если на этой клетке есть растение и 1 животное
                } else if (areaData.getArea()[i][j].getPlant() != null &&
                        areaData.getArea()[i][j].getAnimals_in_cell().size() == 1) {
                    //если животное травоядное - есть растение
                    if (areaData.getArea()[i][j].getAnimals_in_cell().get(0).getClass().getSuperclass() == Herbivore.class) {
                        areaData.getArea()[i][j].getAnimals_in_cell().get(0).eat(areaData.getArea()[i][j].getPlant(), i, j);
                        areaData.getArea()[i][j].getAnimals_in_cell().get(0).move(areaData.getArea()[i][j].getAnimals_in_cell().get(0), i, j);

                        //или если животное хищник - вызывает метод move()
                    } else {
                        areaData.getArea()[i][j].getAnimals_in_cell().get(0).move(areaData.getArea()[i][j].getAnimals_in_cell().get(0), i, j);
                    }
                    //если на этой клетке есть животное и нет растений - то вызывается метод move()
                } else if (areaData.getArea()[i][j].getAnimals_in_cell().size() == 1 && areaData.getArea()[i][j].getPlant() == null) {
                    areaData.getArea()[i][j].getAnimals_in_cell().get(0).move(areaData.getArea()[i][j].getAnimals_in_cell().get(0), i, j);
                }
            }
        }
        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                areaData.getArea()[i][j].showCell();
                System.out.print(" ");
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
        int maxCounter = animalData.getAnimalList().size();
        int counter = 0;

        while (counter < 14) {
            for (int i = 0; i < areaData.getArea().length; i++) {
                for (int j = 0; j < areaData.getArea()[i].length; j++) {
                    int random = ThreadLocalRandom.current().nextInt(0, 140);

                    if (random <= 1 && areaData.getArea()[i][j].getAnimals_in_cell().size() == 0 && areaData.getArea()[i][j].getPlant() == null
                            && counter < maxCounter) {
                        areaData.getArea()[i][j].setAnimals_in_cell(animalData.getAnimalList().get(counter));

                        counter++;
                    }
                }
            }
        }

//        if (counter != 14) {
//            for (int i = 0; i < areaData.getArea().length; i++) {
//                for (int j = 0; j < areaData.getArea()[i].length; j++) {
//                    int random = ThreadLocalRandom.current().nextInt(0, 100);
//
//                    if (random <= 5 && areaData.getArea()[i][j].getAnimals_in_cell() == null && areaData.getArea()[i][j].getPlant() == null
//                            && counter < maxCounter) {
//                        areaData.getArea()[i][j].setAnimals_in_cell(animalData.getAnimalList().get(counter));
//
//                        int amountInCell = ThreadLocalRandom.current()
//                                .nextInt(1, animalData.getAnimalList().get(counter).getMax_amount_in_cell() + 1);
//
//                        areaData.getArea()[i][j].getAnimals_in_cell().get(0)
//                                .setLeftAlive(amountInCell);
//
//                        counter++;
//                    }
//
//                }
//            }
//        }
        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                areaData.getArea()[i][j].showCell();
                System.out.print(" ");
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