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
        for (int width = 0; width < areaData.getArea().length; width++) {
            for (int length = 0; length < areaData.getArea()[width].length; length++) {
                if (areaData.getArea()[width][length].getPlant() != null &&
                        areaData.getArea()[width][length].getAnimals_in_cell().size() > 1) {
                    if (areaData.getArea()[width][length].isPredAndHerbInCell()) {
                        for (int animal = 0; animal < areaData.getArea()[width][length].getAnimals_in_cell().size(); animal++) {
                            for (int anotherAnimal = 0; anotherAnimal < areaData.getArea()[width][length].getAnimals_in_cell().size(); anotherAnimal++) {
                                if (animal == areaData.getArea()[width][length].getAnimals_in_cell().size()) {
                                    break;
                                }
                                if (areaData.getArea()[width][length].getAnimals_in_cell().get(animal).getClass().getSuperclass() == Predator.class) {
                                    areaData.getArea()[width][length].getAnimals_in_cell().get(animal).eat(areaData.getArea()[width][length].getAnimals_in_cell().get(anotherAnimal), width, length);
                                } else if (areaData.getArea()[width][length].getAnimals_in_cell().get(animal).getClass().getSuperclass() == Herbivore.class) {
                                    areaData.getArea()[width][length].getAnimals_in_cell().get(animal).eat(areaData.getArea()[width][length].getPlant(), width, length);
                                }
                            }
                            for (int animalNumber = 0; animalNumber < areaData.getArea()[width][length].getAnimals_in_cell().size(); animalNumber++) {
                                areaData.getArea()[width][length].getAnimals_in_cell().get(animalNumber).move(areaData.getArea()[width][length].getAnimals_in_cell().get(animalNumber), width, length);
                            }
                        }
                    } else if (areaData.getArea()[width][length].isHerbivores()) {
                        for (int animal = 0; animal < areaData.getArea()[width][length].getAnimals_in_cell().size(); animal++) {
                            areaData.getArea()[width][length].getAnimals_in_cell().get(animal).eat(areaData.getArea()[width][length].getPlant(), width, length);
                            areaData.getArea()[width][length].getAnimals_in_cell().get(animal).move(areaData.getArea()[width][length].getAnimals_in_cell().get(animal), width, length);
                        }
                    } else if (areaData.getArea()[width][length].isPredators()) {
                        for (int animal = 0; animal < areaData.getArea()[width][length].getAnimals_in_cell().size(); animal++) {
                            for (int anotherAnimal = 0; anotherAnimal < areaData.getArea()[width][length].getAnimals_in_cell().size(); anotherAnimal++) {
                                if (animal == areaData.getArea()[width][length].getAnimals_in_cell().size()) {
                                    break;
                                }
                                areaData.getArea()[width][length].getAnimals_in_cell().get(animal).eat(areaData.getArea()[width][length].getAnimals_in_cell().get(anotherAnimal), width, length);
                            }
                        }
                        for (int animalNumber = 0; animalNumber < areaData.getArea()[width][length].getAnimals_in_cell().size(); animalNumber++) {
                            areaData.getArea()[width][length].getAnimals_in_cell().get(animalNumber).move(areaData.getArea()[width][length].getAnimals_in_cell().get(animalNumber), width, length);
                        }
                    }
                } else if (areaData.getArea()[width][length].getPlant() != null &&
                        areaData.getArea()[width][length].getAnimals_in_cell().size() == 1) {
                    if (areaData.getArea()[width][length].getAnimals_in_cell().get(0).getClass().getSuperclass() == Herbivore.class) {
                        areaData.getArea()[width][length].getAnimals_in_cell().get(0).eat(areaData.getArea()[width][length].getPlant(), width, length);
                        areaData.getArea()[width][length].getAnimals_in_cell().get(0).move(areaData.getArea()[width][length].getAnimals_in_cell().get(0), width, length);
                    } else if (areaData.getArea()[width][length].getAnimals_in_cell().get(0).getClass().getSuperclass() == Predator.class) {
                        areaData.getArea()[width][length].getAnimals_in_cell().get(0).move(areaData.getArea()[width][length].getAnimals_in_cell().get(0), width, length);
                    }
                } else if (areaData.getArea()[width][length].getAnimals_in_cell().size() == 1 && areaData.getArea()[width][length].getPlant() == null) {
                    areaData.getArea()[width][length].getAnimals_in_cell().get(0).move(areaData.getArea()[width][length].getAnimals_in_cell().get(0), width, length);
                } else if (areaData.getArea()[width][length].getAnimals_in_cell().size() > 1 && areaData.getArea()[width][length].getPlant() == null) {
                    if (areaData.getArea()[width][length].isPredAndHerbInCell()) {
                        for (int animal = 0; animal < areaData.getArea()[width][length].getAnimals_in_cell().size(); animal++) {
                            for (int anotherAnimal = 0; anotherAnimal < areaData.getArea()[width][length].getAnimals_in_cell().size(); anotherAnimal++) {
                                if (animal == areaData.getArea()[width][length].getAnimals_in_cell().size()) {
                                    break;
                                }
                                if (areaData.getArea()[width][length].getAnimals_in_cell().get(animal).getClass().getSuperclass() == Predator.class) {
                                    areaData.getArea()[width][length].getAnimals_in_cell().get(animal).eat(areaData.getArea()[width][length].getAnimals_in_cell().get(anotherAnimal), width, length);
                                }
                            }
                        }
                        for (int animal = 0; animal < areaData.getArea()[width][length].getAnimals_in_cell().size(); animal++) {
                            if (!areaData.getArea()[width][length].getAnimals_in_cell().isEmpty()) {
                                areaData.getArea()[width][length].getAnimals_in_cell().get(animal).move(areaData.getArea()[width][length].getAnimals_in_cell().get(animal), width, length);
                            }
                        }
                    } else if (areaData.getArea()[width][length].isHerbivores()) {
                        for (int animal = 0; animal < areaData.getArea()[width][length].getAnimals_in_cell().size(); animal++) {
                            areaData.getArea()[width][length].getAnimals_in_cell().get(animal).move(areaData.getArea()[width][length].getAnimals_in_cell().get(animal), width, length);
                        }
                    } else if (areaData.getArea()[width][length].isPredators()) {
                        for (int animal = 0; animal < areaData.getArea()[width][length].getAnimals_in_cell().size(); animal++) {
                            for (int anotherAnimal = 0; anotherAnimal < areaData.getArea()[width][length].getAnimals_in_cell().size(); anotherAnimal++) {
                                if (animal == areaData.getArea()[width][length].getAnimals_in_cell().size()) {
                                    break;
                                }
                                areaData.getArea()[width][length].getAnimals_in_cell().get(animal).eat(areaData.getArea()[width][length].getAnimals_in_cell().get(anotherAnimal), width, length);
                            }
                        }
                        for (int animal = 0; animal < areaData.getArea()[width][length].getAnimals_in_cell().size(); animal++) {
                            areaData.getArea()[width][length].getAnimals_in_cell().get(animal).move(areaData.getArea()[width][length].getAnimals_in_cell().get(animal), width, length);
                        }
                    }
                }
            }
        }
    }

    private void deleteAnimalIfSatietyNull() {
        for (int width = 0; width < areaData.getArea().length; width++) {
            for (int length = 0; length < areaData.getArea()[width].length; length++) {
                for (int animal = 0; animal < areaData.getArea()[width][length].getAnimals_in_cell().size(); animal++) {
                    if (areaData.getArea()[width][length].getAnimals_in_cell().get(animal).isSatietyIsNull()) {
                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                        animal--;
                    }
                }
            }
        }
    }

    private void setSatietyAfterRound() {
        for (int width = 0; width < areaData.getArea().length; width++) {
            for (int length = 0; length < areaData.getArea()[width].length; length++) {
                for (int animalNumber = 0; animalNumber < areaData.getArea()[width][length].getAnimals_in_cell().size(); animalNumber++) {
                    areaData.getArea()[width][length].getAnimals_in_cell().get(animalNumber).setSatietyAfterRound();
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

        for (int width = 0; width < areaData.getArea().length; width++) {
            for (int length = 0; length < areaData.getArea()[width].length; length++) {
                for (int animalNumber = 0; animalNumber < areaData.getArea()[width][length].getAnimals_in_cell().size(); animalNumber++) {
                    if (!areaData.getArea()[width][length].getAnimals_in_cell().isEmpty()) {
                        Animal animal = areaData.getArea()[width][length].getAnimals_in_cell().get(animalNumber);
                        int widthOfAnimal = width;
                        int lengthOfAnimal = length;
                        int valueOfSameKindAnimal = 0;
                        int widthOfAnotherAnimal = 0;
                        int lengthOfAnotherAnimal = 0;
                        for (int anotherAnimalWidth = 0; anotherAnimalWidth < areaData.getArea().length; anotherAnimalWidth++) {
                            for (int anotherAnimalLength = 0; anotherAnimalLength < areaData.getArea()[anotherAnimalWidth].length; anotherAnimalLength++) {
                                for (int anotherAnimalNumber = 0; anotherAnimalNumber < areaData.getArea()[anotherAnimalWidth][anotherAnimalLength].getAnimals_in_cell().size(); anotherAnimalNumber++) {
                                    if (areaData.getArea()[anotherAnimalWidth][anotherAnimalLength].getAnimals_in_cell().get(anotherAnimalNumber).getAnimalKind().equals(animal.getAnimalKind())) {
                                        valueOfSameKindAnimal += 1;
                                        widthOfAnotherAnimal = anotherAnimalWidth;
                                        lengthOfAnotherAnimal = anotherAnimalLength;
                                        if (width == anotherAnimalWidth && length == anotherAnimalLength && animalNumber == anotherAnimalNumber) {
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
        for (int width = 0; width < areaData.getArea().length; width++) {
            for (int length = 0; length < areaData.getArea()[width].length; length++) {
                areaData.getArea()[width][length].showCell();
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}