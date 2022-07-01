package org.example.controllers;

import org.example.dao.AnimalData;
import org.example.dao.AreaData;
import org.example.dao.PlantData;
import org.example.dao.Properties;
import org.example.domains.*;

import java.util.concurrent.ThreadLocalRandom;

public class AreaController implements Runnable {

    private AreaData areaData = AreaData.getInstance();
    private PlantData plantData = PlantData.getInstance();
    private AnimalData animalData = AnimalData.getInstance();
    private Plant plant = new Plant();

    @Override
    public void run() {
        deNextRound();
    }

    public void deNextRound() {
        tryToReproduce();
        doAnimalActions();
        setSatietyAfterRound();
        deleteAnimalIfSatietyNull();
        showArea();
    }

    private void doAnimalActions() {
        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                if (areaData.getArea()[i][j].getPlant() != null &&
                        areaData.getArea()[i][j].getAnimals_in_cell().size() > 1) {
                    if (areaData.getArea()[i][j].isPredAndHerbInCell()) {
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            for (int l = 0; l < areaData.getArea()[i][j].getAnimals_in_cell().size(); l++) {
                                if (k == areaData.getArea()[i][j].getAnimals_in_cell().size()) {
                                    break;
                                }
                                if (areaData.getArea()[i][j].getAnimals_in_cell().get(k).getClass().getSuperclass() == Predator.class) {
                                    areaData.getArea()[i][j].getAnimals_in_cell().get(k).eat(areaData.getArea()[i][j].getAnimals_in_cell().get(l), i, j);
                                } else if (areaData.getArea()[i][j].getAnimals_in_cell().get(k).getClass().getSuperclass() == Herbivore.class) {
                                    areaData.getArea()[i][j].getAnimals_in_cell().get(k).eat(areaData.getArea()[i][j].getPlant(), i, j);
                                }
                            }
                            for (int animalNumber = 0; animalNumber < areaData.getArea()[i][j].getAnimals_in_cell().size(); animalNumber++) {
                                areaData.getArea()[i][j].getAnimals_in_cell().get(animalNumber).move(areaData.getArea()[i][j].getAnimals_in_cell().get(animalNumber), i, j);
                            }
                        }
                    } else if (areaData.getArea()[i][j].isHerbivores()) {
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            areaData.getArea()[i][j].getAnimals_in_cell().get(k).eat(areaData.getArea()[i][j].getPlant(), i, j);
                            areaData.getArea()[i][j].getAnimals_in_cell().get(k).move(areaData.getArea()[i][j].getAnimals_in_cell().get(k), i, j);
                        }
                    } else if (areaData.getArea()[i][j].isPredators()) {
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            for (int l = 0; l < areaData.getArea()[i][j].getAnimals_in_cell().size(); l++) {
                                if (k == areaData.getArea()[i][j].getAnimals_in_cell().size()) {
                                    break;
                                }
                                areaData.getArea()[i][j].getAnimals_in_cell().get(k).eat(areaData.getArea()[i][j].getAnimals_in_cell().get(l), i, j);
                            }
                        }
                        for (int animalNumber = 0; animalNumber < areaData.getArea()[i][j].getAnimals_in_cell().size(); animalNumber++) {
                            areaData.getArea()[i][j].getAnimals_in_cell().get(animalNumber).move(areaData.getArea()[i][j].getAnimals_in_cell().get(animalNumber), i, j);
                        }
                    }
                } else if (areaData.getArea()[i][j].getPlant() != null &&
                        areaData.getArea()[i][j].getAnimals_in_cell().size() == 1) {
                    if (areaData.getArea()[i][j].getAnimals_in_cell().get(0).getClass().getSuperclass() == Herbivore.class) {
                        areaData.getArea()[i][j].getAnimals_in_cell().get(0).eat(areaData.getArea()[i][j].getPlant(), i, j);
                        areaData.getArea()[i][j].getAnimals_in_cell().get(0).move(areaData.getArea()[i][j].getAnimals_in_cell().get(0), i, j);
                    } else if (areaData.getArea()[i][j].getAnimals_in_cell().get(0).getClass().getSuperclass() == Predator.class) {
                        areaData.getArea()[i][j].getAnimals_in_cell().get(0).move(areaData.getArea()[i][j].getAnimals_in_cell().get(0), i, j);
                    }
                } else if (areaData.getArea()[i][j].getAnimals_in_cell().size() == 1 && areaData.getArea()[i][j].getPlant() == null) {
                    areaData.getArea()[i][j].getAnimals_in_cell().get(0).move(areaData.getArea()[i][j].getAnimals_in_cell().get(0), i, j);
                } else if (areaData.getArea()[i][j].getAnimals_in_cell().size() > 1 && areaData.getArea()[i][j].getPlant() == null) {
                    if (areaData.getArea()[i][j].isPredAndHerbInCell()) {
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            for (int l = 0; l < areaData.getArea()[i][j].getAnimals_in_cell().size(); l++) {
                                if (k == areaData.getArea()[i][j].getAnimals_in_cell().size()) {
                                    break;
                                }
                                if (areaData.getArea()[i][j].getAnimals_in_cell().get(k).getClass().getSuperclass() == Predator.class) {
                                    areaData.getArea()[i][j].getAnimals_in_cell().get(k).eat(areaData.getArea()[i][j].getAnimals_in_cell().get(l), i, j);
                                }
                            }
                        }
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            if (!areaData.getArea()[i][j].getAnimals_in_cell().isEmpty()) {
                                areaData.getArea()[i][j].getAnimals_in_cell().get(k).move(areaData.getArea()[i][j].getAnimals_in_cell().get(k), i, j);
                            }
                        }
                    } else if (areaData.getArea()[i][j].isHerbivores()) {
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            areaData.getArea()[i][j].getAnimals_in_cell().get(k).move(areaData.getArea()[i][j].getAnimals_in_cell().get(k), i, j);
                        }
                    } else if (areaData.getArea()[i][j].isPredators()) {
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            for (int l = 0; l < areaData.getArea()[i][j].getAnimals_in_cell().size(); l++) {
                                if (k == areaData.getArea()[i][j].getAnimals_in_cell().size()) {
                                    break;
                                }
                                areaData.getArea()[i][j].getAnimals_in_cell().get(k).eat(areaData.getArea()[i][j].getAnimals_in_cell().get(l), i, j);
                            }
                        }
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            areaData.getArea()[i][j].getAnimals_in_cell().get(k).move(areaData.getArea()[i][j].getAnimals_in_cell().get(k), i, j);
                        }
                    }
                }
            }
        }
    }

    private void deleteAnimalIfSatietyNull() {
        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                    if (areaData.getArea()[i][j].getAnimals_in_cell().get(k).isSatietyIsNull()) {
                        areaData.getArea()[i][j].getAnimals_in_cell().remove(k);
                        k--;
                    }
                }
            }
        }
    }

    private void setSatietyAfterRound() {
        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                    areaData.getArea()[i][j].getAnimals_in_cell().get(k).setSatietyAfterRound();
                }
            }
        }
    }

    public void initializeAndGenerate(Properties properties) {
        areaData.setArea(new Cell[properties.getFieldWidth()][properties.getFieldLength()]);
        areaData.generateArea();
        plantData.initializePlant(properties);
        plant.generatePlants();
        animalData.generateAnimals(properties);
    }

    private void tryToReproduce() {

        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                    if (!areaData.getArea()[i][j].getAnimals_in_cell().isEmpty()) {
                        Animal animal = areaData.getArea()[i][j].getAnimals_in_cell().get(k);
                        int widthOfAnimal = i;
                        int lengthOfAnimal = j;
                        int valueOfSameKindAnimal = 0;
                        int widthOfAnotherAnimal = 0;
                        int lengthOfAnotherAnimal = 0;
                        for (int anotherAnimalWidth = 0; anotherAnimalWidth < areaData.getArea().length; anotherAnimalWidth++) {
                            for (int l = 0; l < areaData.getArea()[anotherAnimalWidth].length; l++) {
                                for (int m = 0; m < areaData.getArea()[anotherAnimalWidth][l].getAnimals_in_cell().size(); m++) {
                                    if (areaData.getArea()[anotherAnimalWidth][l].getAnimals_in_cell().get(m).getAnimalKind().equals(animal.getAnimalKind())) {
                                        valueOfSameKindAnimal += 1;
                                        widthOfAnotherAnimal = anotherAnimalWidth;
                                        lengthOfAnotherAnimal = l;
                                        if (i == anotherAnimalWidth && j == l && k == m) {
                                            valueOfSameKindAnimal -= 1;
                                        }
                                    }
                                }
                            }
                        }
                        if (valueOfSameKindAnimal == 0) {
                            if (animal.getLeftAlive() > 1) {
                                int random = ThreadLocalRandom.current().nextInt(0, 101);
                                if (random < 10) {
                                    animal.reproduce();
                                }
                            }
                        } else if (valueOfSameKindAnimal >= 1) {
                            if (widthOfAnimal == widthOfAnotherAnimal && lengthOfAnimal == lengthOfAnotherAnimal) {
                                int random = ThreadLocalRandom.current().nextInt(0, 101);
                                if (random <= 10) {
                                    animal.reproduce();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void showArea() {
        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                areaData.getArea()[i][j].showCell();
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}