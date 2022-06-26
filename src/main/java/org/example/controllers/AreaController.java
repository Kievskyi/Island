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
//        for (int i = 0; i < 5; i++) {
            deNextRound();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    public void deNextRound() {

        tryToReproduce();

        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                //если на этой клетке есть растение и несколько животных
                if (areaData.getArea()[i][j].getPlant() != null &&
                        areaData.getArea()[i][j].getAnimals_in_cell().size() > 1) {
                    //если один из них травоядный а другой хищник
                    if (areaData.getArea()[i][j].isPredAndHerbInCell()) {
                        //каждый хищник должен попробовать съесть травоядного до момента полного насыщения
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            for (int l = 0; l < areaData.getArea()[i][j].getAnimals_in_cell().size(); l++) {
                                if (k == areaData.getArea()[i][j].getAnimals_in_cell().size()) {
                                    break;
                                }
                                //если эивотное хищник - то есть животного
                                if (areaData.getArea()[i][j].getAnimals_in_cell().get(k).getClass().getSuperclass() == Predator.class) {
                                    areaData.getArea()[i][j].getAnimals_in_cell().get(k).eat(areaData.getArea()[i][j].getAnimals_in_cell().get(l), i, j);
                                    //если эивотное травоядное - есть растение
                                } else if (areaData.getArea()[i][j].getAnimals_in_cell().get(k).getClass().getSuperclass() == Herbivore.class) {
                                    areaData.getArea()[i][j].getAnimals_in_cell().get(k).eat(areaData.getArea()[i][j].getPlant(), i, j);
                                }
                            }
                            //после действий - вызываем у всех животных метод move()
                            for (int l = 0; l < areaData.getArea()[i][j].getAnimals_in_cell().size(); l++) {
                                areaData.getArea()[i][j].getAnimals_in_cell().get(l).move(areaData.getArea()[i][j].getAnimals_in_cell().get(l), i, j);
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
                        //пробуют джруг друга съесть
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            for (int l = 0; l < areaData.getArea()[i][j].getAnimals_in_cell().size(); l++) {
                                if (k == areaData.getArea()[i][j].getAnimals_in_cell().size()) {
                                    break;
                                }
                                areaData.getArea()[i][j].getAnimals_in_cell().get(k).eat(areaData.getArea()[i][j].getAnimals_in_cell().get(l), i, j);
                            }
                        }
                        //а потом вызываем у уцелевших метод move()
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
                    } else if (areaData.getArea()[i][j].getAnimals_in_cell().get(0).getClass().getSuperclass() == Predator.class) {
                        areaData.getArea()[i][j].getAnimals_in_cell().get(0).move(areaData.getArea()[i][j].getAnimals_in_cell().get(0), i, j);
                    }
                    //если на этой клетке есть животное и нет растений - то вызывается метод move()
                } else if (areaData.getArea()[i][j].getAnimals_in_cell().size() == 1 && areaData.getArea()[i][j].getPlant() == null) {
                    areaData.getArea()[i][j].getAnimals_in_cell().get(0).move(areaData.getArea()[i][j].getAnimals_in_cell().get(0), i, j);
                    //если на этой клетке есть несколько животных и нет растений
                } else if (areaData.getArea()[i][j].getAnimals_in_cell().size() > 1 && areaData.getArea()[i][j].getPlant() == null) {
                    //каждый хищник должен попробовать съесть травоядного до момента полного насыщения
                    if (areaData.getArea()[i][j].isPredAndHerbInCell()) {
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            for (int l = 0; l < areaData.getArea()[i][j].getAnimals_in_cell().size(); l++) {
                                if (k == areaData.getArea()[i][j].getAnimals_in_cell().size()) {
                                    break;
                                }
                                //если эивотное хищник - то есть животного
                                if (areaData.getArea()[i][j].getAnimals_in_cell().get(k).getClass().getSuperclass() == Predator.class) {
                                    areaData.getArea()[i][j].getAnimals_in_cell().get(k).eat(areaData.getArea()[i][j].getAnimals_in_cell().get(l), i, j);
                                }
                            }
                        }
                        //после действий - вызываем у всех животных метод move()
                        for (int l = 0; l < areaData.getArea()[i][j].getAnimals_in_cell().size(); l++) {
                            if (!areaData.getArea()[i][j].getAnimals_in_cell().isEmpty()) {
                                areaData.getArea()[i][j].getAnimals_in_cell().get(l).move(areaData.getArea()[i][j].getAnimals_in_cell().get(l), i, j);
                            }
                        }
                        //если оба животных травоядные
                    } else if (areaData.getArea()[i][j].isHerbivores()) {
                        //то они просто едят до полного насыщения
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            areaData.getArea()[i][j].getAnimals_in_cell().get(k).move(areaData.getArea()[i][j].getAnimals_in_cell().get(k), i, j);
                        }
                        //если оба животных хищники
                    } else if (areaData.getArea()[i][j].isPredators()) {
                        //пробуют джруг друга съесть
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            for (int l = 0; l < areaData.getArea()[i][j].getAnimals_in_cell().size(); l++) {
                                if (k == areaData.getArea()[i][j].getAnimals_in_cell().size()) {
                                    break;
                                }
                                areaData.getArea()[i][j].getAnimals_in_cell().get(k).eat(areaData.getArea()[i][j].getAnimals_in_cell().get(l), i, j);
                            }
                        }
                        //а потом вызываем у уцелевших метод move()
                        for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                            areaData.getArea()[i][j].getAnimals_in_cell().get(k).move(areaData.getArea()[i][j].getAnimals_in_cell().get(k), i, j);
                        }
                    }
                }
            }
        }
        setSatietyAfterRound();
        deleteAnimalIfSatietyNull();
        showArea();
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
                    //если на этой ячейке есть животное
                    if (!areaData.getArea()[i][j].getAnimals_in_cell().isEmpty()) {
                        //получили эти эивотное
                        Animal animal = areaData.getArea()[i][j].getAnimals_in_cell().get(k);
                        int widthOfAnimal = i;
                        int lengthOfAnimal = j;
                        int valueOfSameKindAnimal = 0;
                        int widthOfAnotherAnimal = 0;
                        int lengthOfAnotherAnimal = 0;
                        //проходимся по всем животным на поле
                        for (int l = 0; l < areaData.getArea().length; l++) {
                            for (int m = 0; m < areaData.getArea()[l].length; m++) {
                                for (int n = 0; n < areaData.getArea()[l][m].getAnimals_in_cell().size(); n++) {
                                    //обходим элемент листа в которым лежит наше животное
                                    if (areaData.getArea()[l][m].getAnimals_in_cell().get(n).getAnimalKind().equals(animal.getAnimalKind())) {
                                        //если полученное животное будет того же типа что и наш animals - то мы инкрементируем valueOfSameKindAnimal
                                        valueOfSameKindAnimal += 1;
                                        widthOfAnotherAnimal = l;
                                        lengthOfAnotherAnimal = m;
                                        if (i == l && j == m && k == n) {
                                            valueOfSameKindAnimal -= 1;
                                        }
                                    }
                                }
                            }
                        }
                        //если наше животное одно на поле
                        if (valueOfSameKindAnimal == 0) {
                            //если в стае осталось больше 1 животного
                            if (animal.getLeftAlive() > 1) {
                                //то с шансом 10% делаем ребенка
                                int random = ThreadLocalRandom.current().nextInt(0, 101);
                                if (random < 10) {
                                    animal.reproduce();
                                }
                            }

                            //если на поле более 1 животного этого вида
                        } else if (valueOfSameKindAnimal >= 1) {
                            //если их координаты совпадают
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