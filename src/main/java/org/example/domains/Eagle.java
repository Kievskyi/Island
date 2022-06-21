package org.example.domains;

import org.example.dao.AreaData;
import org.example.utils.AnimalIcons;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Eagle extends Predator {

    private AreaData areaData = AreaData.getInstance();
    private String kind_of_animal = "Eagle";
    private String icon = AnimalIcons.EAGLE.getIcon();
    private double weight = 6.0;
    private double max_satiety = 1.0;
    private double satiety;
    private int max_amount_in_cell = 20;
    private int leftAlive;
    private int maxSpeed = 3;
    private Map<String, Integer> chances_to_kill;

    {
        chances_to_kill = new HashMap<>() {{
            put("Wolf", 0);
            put("Snake", 80);
            put("Fox", 10);
            put("Bear", 0);
            put("Horse", 0);
            put("Deer", 0);
            put("Rabbit", 90);
            put("Mouse", 90);
            put("Goat", 0);
            put("Sheep", 0);
            put("Boar", 0);
            put("Buffalo", 0);
            put("Duck", 80);
            put("Caterpillar", 0);
            put("Plant", 0);
        }};
        satiety = ThreadLocalRandom.current().nextDouble(0.2, 0.7);
        leftAlive = ThreadLocalRandom.current().nextInt(4 , max_amount_in_cell + 1);
    }

    @Override
    public void eat(Animal animal, int width, int length) {


        if (chances_to_kill.containsKey(animal.getKind_of_animal())) {
            for (Map.Entry<String, Integer> chances : chances_to_kill.entrySet()) {
                if (chances.getKey().equals(animal.getKind_of_animal())) {
                    if (chances.getValue() > 0) {
                        int random = ThreadLocalRandom.current().nextInt(0, 101);
                        if (random <= chances.getValue()) {
                            if (satiety < max_satiety) {        //Here we 100% will kill prey animal
                                while (satiety < max_satiety && animal.getLeftAlive() > 0) {
                                    if (satiety + animal.getWeight() > max_satiety) {
                                        if (animal.getLeftAlive() == 1) {
                                            satiety = max_satiety;
                                            areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                                        } else {
                                            satiety = max_satiety;
                                            animal.setLeftAlive(animal.getLeftAlive() - 1);
                                        }
                                    } else {
                                        satiety += animal.getWeight();
                                        animal.setLeftAlive(animal.getLeftAlive() - 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public void move(Animal animal, int width, int length) {
        int lengthArea = areaData.getArea()[0].length - 1;
        int widthArea = areaData.getArea().length - 1;
        int randomSpeed = ThreadLocalRandom.current().nextInt(0, maxSpeed + 1);
        int randomCorner = ThreadLocalRandom.current().nextInt(0, 3);
        int randomBounds = ThreadLocalRandom.current().nextInt(0, 6);
        int randomCentralBounds = ThreadLocalRandom.current().nextInt(0, 4);
        int randomCenter = ThreadLocalRandom.current().nextInt(0, 8);
        if (width == widthArea && length == lengthArea) {
            // bottom right corner
            if (randomCorner == 0) {
                //to up
                areaData.getArea()[width - randomSpeed][length].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomCorner == 1) {
                //diagonally
                areaData.getArea()[width - randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomCorner == 2) {
                //to left
                areaData.getArea()[width][length - randomSpeed].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            }
        } else if (length == 0 && width == 0) {
            //top left corner
            if (randomCorner == 0) {
                //to right
                areaData.getArea()[width][length + randomSpeed].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomCorner == 1) {
                //diagonally
                areaData.getArea()[width + randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomCorner == 2) {
                //to down
                areaData.getArea()[width + randomSpeed][length].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            }
        } else if (length == lengthArea && width == 0) {
            //top right corner
            if (randomCorner == 0) {
                //to left
                areaData.getArea()[width][length - randomSpeed].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomCorner == 1) {
                //diagonally
                areaData.getArea()[width + randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomCorner == 2) {
                //to down
                areaData.getArea()[width + randomSpeed][length].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            }
        } else if (length == 0 && width == widthArea) {
            //bottom left corner
            if (randomCorner == 0) {
                //to up
                areaData.getArea()[width - randomSpeed][length].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomCorner == 1) {
                //diagonally
                areaData.getArea()[width - randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomCorner == 2) {
                //to right
                areaData.getArea()[width][length + randomSpeed].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            }
        } else if (width == 0 && (length >= 1 && length < lengthArea)) {
            //top bounds
            if (randomBounds == 0) {
                //to right
                if (randomSpeed + length > lengthArea) {
                    areaData.getArea()[width][lengthArea].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width][length + randomSpeed].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            } else if (randomBounds == 1) {
                //to left
                if (length - randomSpeed < 0) {
                    areaData.getArea()[width][0].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width][length - randomSpeed].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            } else if (randomBounds == 2) {
                //to down
                areaData.getArea()[width + randomSpeed][length].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomBounds == 3) {
                //right diagonal
                if (randomSpeed + length > lengthArea) {
                    areaData.getArea()[width + (lengthArea - length)][lengthArea].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width + randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            } else if (randomBounds == 4) {
                //left diagonal
                if (length - randomSpeed < 0) {
                    areaData.getArea()[width + length][0].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width + randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            }
        } else if (width == widthArea && (length >= 1 && length < lengthArea)) {
            //bottom bounds
            if (randomBounds == 0) {
                //to right
                if (randomSpeed + length > lengthArea) {
                    areaData.getArea()[width][lengthArea].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width][length + randomSpeed].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            } else if (randomBounds == 1) {
                //to left
                if (length - randomSpeed < 0) {
                    areaData.getArea()[width][0].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width][length - randomSpeed].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            } else if (randomBounds == 2) {
                //to up
                areaData.getArea()[widthArea - randomSpeed][length].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomBounds == 3) {
                //right diagonal
                if (randomSpeed + length > lengthArea) {
                    areaData.getArea()[width - (lengthArea - length)][lengthArea].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width - randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            } else if (randomBounds == 4) {
                //left diagonal
                if (length - randomSpeed < 0) {
                    areaData.getArea()[widthArea - length][0].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width - randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            }
        } else if (length == 0 && (width >= 1 && width < widthArea)) {
            //left bounds
            if (randomBounds == 0) {
                //to up
                if (width - randomSpeed < 0) {
                    areaData.getArea()[0][length].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width - randomSpeed][length].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            } else if (randomBounds == 1) {
                //to right
                areaData.getArea()[width][length + randomSpeed].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomBounds == 2) {
                //to down
                if (width + randomSpeed > widthArea) {
                    areaData.getArea()[widthArea][length].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width + randomSpeed][length].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            } else if (randomBounds == 3) {
                //top diagonal
                if (width - randomSpeed < 0) {
                    areaData.getArea()[0][width].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width - randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            } else if (randomBounds == 4) {
                //bottom diagonal
                if (width + randomSpeed > widthArea) {
                    areaData.getArea()[widthArea][widthArea - width].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width + randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            }
        } else if (length == lengthArea && (width >= 1 && width < widthArea)) {
            //right bounds
            if (randomBounds == 0) {
                //to up
                if (width - randomSpeed < 0) {
                    areaData.getArea()[0][length].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width - randomSpeed][length].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            } else if (randomBounds == 1) {
                //to left
                areaData.getArea()[width][length - randomSpeed].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomBounds == 2) {
                //to down
                if (width + randomSpeed > widthArea) {
                    areaData.getArea()[widthArea][length].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width + randomSpeed][length].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            } else if (randomBounds == 3) {
                //top diagonal
                if (width - randomSpeed < 0) {
                    areaData.getArea()[0][lengthArea - width].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width - randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            } else if (randomBounds == 4) {
                //bottom diagonal
                if (width + randomSpeed > widthArea) {
                    areaData.getArea()[widthArea][lengthArea - width].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width + randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            }
        } else if (((width > 0 && width < maxSpeed) || (width > (widthArea - maxSpeed) && width < widthArea)) && (length > 0 && length < lengthArea)
                || ((width >= maxSpeed && width <= widthArea - maxSpeed) && (length > 0 && length < maxSpeed))
                || ((width >= maxSpeed && width <= widthArea - maxSpeed) && (length > lengthArea - maxSpeed && length < lengthArea))) {
            //central bounds
            if (randomCentralBounds == 0) {
                //to left
                if (length - randomSpeed < 0) {
                    areaData.getArea()[width][0].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width][length - randomSpeed].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            } else if (randomCentralBounds == 1) {
                //to right
                if (randomSpeed + length > lengthArea) {
                    areaData.getArea()[width][lengthArea].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width][length + randomSpeed].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            } else if (randomCentralBounds == 2) {
                //to down
                if (width + randomSpeed > widthArea) {
                    areaData.getArea()[widthArea][length].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width + randomSpeed][length].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            } else if (randomCentralBounds == 3) {
                //to up
                if (width - randomSpeed < 0) {
                    areaData.getArea()[0][length].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                } else {
                    areaData.getArea()[width - randomSpeed][length].setAnimals_in_cell(animal);
                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                }
            }
        } else if ((width >= maxSpeed && width <= widthArea - maxSpeed) && (length >= maxSpeed && length <= lengthArea - maxSpeed)) {
            //center
            if (randomCenter == 0) {
                //to left
                areaData.getArea()[width][length - randomSpeed].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomCenter == 1) {
                //to right
                areaData.getArea()[width][length + randomSpeed].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomCenter == 2) {
                //to down
                areaData.getArea()[width + randomSpeed][length].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomCenter == 3) {
                //left bottom diagonal
                areaData.getArea()[width + randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomCenter == 4) {
                //right bottom diagonal
                areaData.getArea()[width + randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomCenter == 5) {
                //to up
                areaData.getArea()[width - randomSpeed][length].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomCenter == 6) {
                //left upper diagonal
                areaData.getArea()[width - randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            } else if (randomCenter == 7) {
                //right upper diagonal
                areaData.getArea()[width - randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
            }
        }
//            } else if (randomBounds == 3) {
//                //left bottom diagonal
//                //весь низ за пределы
//                if (width + randomSpeed >= widthArea && length > randomSpeed) {
//                    areaData.getArea()[widthArea][length - (widthArea - width)].setAnimals_in_cell(animal);
//                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    //левый нижний угол за пределы
//                } else if (width + randomSpeed >= widthArea && length == 1 && randomSpeed > length) {
//                    areaData.getArea()[width + 1][0].setAnimals_in_cell(animal);
//                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                } else if (width + randomSpeed >= widthArea && length == 2 && randomSpeed > length) {
//                    if (width == 17) {
//                        areaData.getArea()[width + 2][length - 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 18) {
//                        areaData.getArea()[width + 1][length - 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                } else if (width + randomSpeed >= widthArea && length == 3 && randomSpeed > length) {
//                    if (width == 17) {
//                        areaData.getArea()[width + 2][length - 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 18) {
//                        areaData.getArea()[width + 1][length - 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 16) {
//                        areaData.getArea()[width + 3][length - 3].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                } else if (width + randomSpeed >= widthArea && length == 4 && randomSpeed == length) {
//                    if (width == 17) {
//                        areaData.getArea()[width + 2][length - 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 18) {
//                        areaData.getArea()[width + 1][length - 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 16) {
//                        areaData.getArea()[width + 3][length - 3].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 15) {
//                        areaData.getArea()[width + 4][length - 4].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                    //left central bounds
//                } else if ((width > 0 && width <= widthArea - maxSpeed) && length > 0 && length <= maxSpeed) {
//                    if (length == 1 && randomSpeed > length) {
//                        areaData.getArea()[width + 1][length - 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == 2 && randomSpeed > length) {
//                        areaData.getArea()[width + 2][length - 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == 3 && randomSpeed > length) {
//                        areaData.getArea()[width + 3][length - 3].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == 4 && randomSpeed == length) {
//                        areaData.getArea()[width + 4][length - 4].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                    //top central bounds
//                } else if ((length > 0 && length <= lengthArea - maxSpeed) && width > 0 && width <= maxSpeed) {
//                    if (length == 1 && randomSpeed > length) {
//                        areaData.getArea()[width + 1][length - 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == 2 && randomSpeed > length) {
//                        areaData.getArea()[width + 2][length - 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == 3 && randomSpeed > length) {
//                        areaData.getArea()[width + 3][length - 3].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == 4 && randomSpeed == length) {
//                        areaData.getArea()[width + 4][length - 4].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                    //right central bounds
//                } else if ((width > 0 && width <= widthArea - maxSpeed) && length >= lengthArea - maxSpeed) {
//                    if (length == 1 && randomSpeed > length) {
//                        areaData.getArea()[width + randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                }
//            } else if (randomBounds == 4) {
//                //right bottom diagonal
//
//                //left central bounds
//                if ((width > 0 && width <= widthArea) && length > 0 && length <= maxSpeed) {
//                    if (length == 1 && randomSpeed > length) {
//                        areaData.getArea()[width + 1][length + 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == 2 && randomSpeed > length) {
//                        areaData.getArea()[width + 2][length + 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == 3 && randomSpeed > length) {
//                        areaData.getArea()[width + 3][length + 3].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == 4 && randomSpeed == length) {
//                        areaData.getArea()[width + 4][length + 4].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                    //top central bounds
//                } else if ((length > 0 && length < lengthArea) && width > 0 && width <= maxSpeed) {
//                    if (length == lengthArea - 1 && randomSpeed > length) {
//                        areaData.getArea()[width + 1][length + 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == lengthArea - 2 && randomSpeed > length) {
//                        areaData.getArea()[width + 2][length + 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == lengthArea - 3 && randomSpeed > length) {
//                        areaData.getArea()[width + 3][length + 3].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == lengthArea - 4 && randomSpeed == length) {
//                        areaData.getArea()[width + 4][length + 4].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                    //right bottom corner central bounds
//                } else if (width + randomSpeed >= widthArea && length == lengthArea - 1 && randomSpeed > length) {
//                    areaData.getArea()[width + 1][length + 1].setAnimals_in_cell(animal);
//                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                } else if (width + randomSpeed >= widthArea && length == lengthArea - 2 && randomSpeed > length) {
//                    if (width == 17) {
//                        areaData.getArea()[width + 2][length + 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 18) {
//                        areaData.getArea()[width + 1][length + 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                } else if (width + randomSpeed >= widthArea && length == lengthArea - 3 && randomSpeed > length) {
//                    if (width == 17) {
//                        areaData.getArea()[width + 2][length + 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 18) {
//                        areaData.getArea()[width + 1][length + 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 16) {
//                        areaData.getArea()[width + 3][length + 3].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                } else if (width + randomSpeed >= widthArea && length == lengthArea - 4 && randomSpeed == length) {
//                    if (width == 17) {
//                        areaData.getArea()[width + 2][length + 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 18) {
//                        areaData.getArea()[width + 1][length + 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 16) {
//                        areaData.getArea()[width + 3][length + 3].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 15) {
//                        areaData.getArea()[width + 4][length + 4].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                } else if ((width > maxSpeed && width < widthArea - maxSpeed) && (length > lengthArea - maxSpeed && length < lengthArea)) {
//                    if (length == lengthArea - 1 && randomSpeed > length) {
//                        areaData.getArea()[width + 1][length + 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == lengthArea - 2 && randomSpeed > length) {
//                        areaData.getArea()[width + 2][length + 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == lengthArea - 3 && randomSpeed > length) {
//                        areaData.getArea()[width + 3][length + 3].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == lengthArea - 4 && randomSpeed == length) {
//                        areaData.getArea()[width + 4][length + 4].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                } else if ((width > widthArea - maxSpeed && width < widthArea) && (length > maxSpeed && length < lengthArea - maxSpeed)) {
//                    if (width == 17) {
//                        areaData.getArea()[width + 2][length + 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 18) {
//                        areaData.getArea()[width + 1][length + 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 16) {
//                        areaData.getArea()[width + 3][length + 3].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 15) {
//                        areaData.getArea()[width + 4][length + 4].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                }

//            } else if (randomCenter == 6) {
//                //left upper diagonal
//                //left bounds
//                if ((width > 0 && width <= widthArea) && length > 0 && length <= maxSpeed) {
//                    if (length == 1 && randomSpeed > length) {
//                        areaData.getArea()[width - 1][length - 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == 2 && randomSpeed > length) {
//                        areaData.getArea()[width - 2][length - 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == 3 && randomSpeed > length) {
//                        areaData.getArea()[width - 3][length - 3].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == 4 && randomSpeed == length) {
//                        areaData.getArea()[width - 4][length - 4].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                    //top central bounds
//                } else if ((length > maxSpeed && length < lengthArea) && width > 0 && width <= maxSpeed) {
//                    if (width == 1 && randomSpeed > width) {
//                        areaData.getArea()[width - 1][length - 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 2 && randomSpeed > width) {
//                        areaData.getArea()[width - 2][length - 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 3 && randomSpeed > width) {
//                        areaData.getArea()[width - 3][length - 3].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 4 && randomSpeed > width) {
//                        areaData.getArea()[width - 4][length - 4].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                    //right bounds
//                } else if (width > maxSpeed && length > lengthArea - maxSpeed) {
//                    areaData.getArea()[width - randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
//                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    //bottom bounds
//                } else if ((width >= widthArea - maxSpeed) && (length > maxSpeed && length < lengthArea - maxSpeed)) {
//                    areaData.getArea()[width - randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
//                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                }
//            } else if (randomCenter == 7) {
        //right upper diagonal
        //left bounds
//                if ((width > 0 && width <= widthArea) && length > 0 && length <= maxSpeed) {
//                    if (length == 1 && randomSpeed > length) {
//                        areaData.getArea()[width - 1][length + 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == 2 && randomSpeed > length) {
//                        areaData.getArea()[width - 2][length + 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == 3 && randomSpeed > length) {
//                        areaData.getArea()[width - 3][length + 3].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == 4 && randomSpeed == length) {
//                        areaData.getArea()[width - 4][length + 4].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                    //top central bounds
//                } else if ((length > maxSpeed && length < lengthArea) && width > 0 && width <= maxSpeed) {
//                    if (width == 1 && randomSpeed > width) {
//                        areaData.getArea()[width - 1][length + 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 2 && randomSpeed > width) {
//                        areaData.getArea()[width - 2][length + 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 3 && randomSpeed > width) {
//                        areaData.getArea()[width - 3][length + 3].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (width == 4 && randomSpeed > width) {
//                        areaData.getArea()[width - 4][length + 4].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                    //right bounds
//                } else if (width > maxSpeed && length > lengthArea - maxSpeed) {
//                    if (length == lengthArea - 1 && randomSpeed > length) {
//                        areaData.getArea()[width - 1][length + 1].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == lengthArea - 2 && randomSpeed > length) {
//                        areaData.getArea()[width - 2][length + 2].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == lengthArea - 3 && randomSpeed > length) {
//                        areaData.getArea()[width - 3][length + 3].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    } else if (length == lengthArea - 4 && randomSpeed == length) {
//                        areaData.getArea()[width - 4][length + 4].setAnimals_in_cell(animal);
//                        areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                    }
//                    //bottom bounds
//                } else if ((width >= widthArea - maxSpeed) && (length > maxSpeed && length < lengthArea - maxSpeed)) {
//                    areaData.getArea()[width - randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
//                    areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//                }
//            }

//        } else if ((width >= 1 && width < widthArea) && randomSpeed <= (lengthArea - length)) {
//            //right central bounds
//            if (randomCenter == 0) {
//                //to left
//                areaData.getArea()[width][length - randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomBounds == 1) {
//                //to right
//                areaData.getArea()[width][length + randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomBounds == 2) {
//                //to down
//                areaData.getArea()[width + randomSpeed][length].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomBounds == 3) {
//                //left bottom diagonal
//                areaData.getArea()[width + randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomBounds == 4) {
//                //right bottom diagonal
//                areaData.getArea()[width + randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomCenter == 5) {
//                //to up
//                areaData.getArea()[width - randomSpeed][length].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomCenter == 6) {
//                //left upper diagonal
//                areaData.getArea()[width - randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomCenter == 7) {
//                //right upper diagonal
//                areaData.getArea()[width - randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            }
//        } else if ((randomSpeed <= widthArea - width) && (length >= 1 && length < lengthArea)) {
//            //bottom central bounds
//            if (randomCenter == 0) {
//                //to left
//                areaData.getArea()[width][length - randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomBounds == 1) {
//                //to right
//                areaData.getArea()[width][length + randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomBounds == 2) {
//                //to down
//                areaData.getArea()[width + randomSpeed][length].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomBounds == 3) {
//                //left bottom diagonal
//                areaData.getArea()[width + randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomBounds == 4) {
//                //right bottom diagonal
//                areaData.getArea()[width + randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomCenter == 5) {
//                //to up
//                areaData.getArea()[width - randomSpeed][length].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomCenter == 6) {
//                //left upper diagonal
//                areaData.getArea()[width - randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomCenter == 7) {
//                //right upper diagonal
//                areaData.getArea()[width - randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            }
//        } else if (width >= randomSpeed && (length >= 1 && length < lengthArea)) {
//            //top central bounds
//            if (randomCenter == 0) {
//                //to left
//                areaData.getArea()[width][length - randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomBounds == 1) {
//                //to right
//                areaData.getArea()[width][length + randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomBounds == 2) {
//                //to down
//                areaData.getArea()[width + randomSpeed][length].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomBounds == 3) {
//                //left bottom diagonal
//                areaData.getArea()[width + randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomBounds == 4) {
//                //right bottom diagonal
//                areaData.getArea()[width + randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomCenter == 5) {
//                //to up
//                areaData.getArea()[width - randomSpeed][length].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomCenter == 6) {
//                //left upper diagonal
//                areaData.getArea()[width - randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomCenter == 7) {
//                //right upper diagonal
//                areaData.getArea()[width - randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            }
//        } else {
//            //center
//            if (randomCenter == 0) {
//                //to left
//                areaData.getArea()[width][length - randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomBounds == 1) {
//                //to right
//                areaData.getArea()[width][length + randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomBounds == 2) {
//                //to down
//                areaData.getArea()[width + randomSpeed][length].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomBounds == 3) {
//                //left bottom diagonal
//                areaData.getArea()[width + randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomBounds == 4) {
//                //right bottom diagonal
//                areaData.getArea()[width + randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomCenter == 5) {
//                //to up
//                areaData.getArea()[width - randomSpeed][length].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomCenter == 6) {
//                //left upper diagonal
//                areaData.getArea()[width - randomSpeed][length - randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            } else if (randomCenter == 7) {
//                //right upper diagonal
//                areaData.getArea()[width - randomSpeed][length + randomSpeed].setAnimals_in_cell(animal);
//                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
//            }
    }
    @Override
    public void reproduce(Animal animal, int width, int length) {

    }

    @Override
    public int getLeftAlive() {
        return leftAlive;
    }

    @Override
    public void setLeftAlive(int leftAlive) {
        this.leftAlive = leftAlive;
    }

    @Override
    public String getKind_of_animal() {
        return kind_of_animal;
    }

    @Override
    public void setKind_of_animal(String kind_of_animal) {
        this.kind_of_animal = kind_of_animal;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getMax_satiety() {
        return max_satiety;
    }

    @Override
    public void setMax_satiety(double max_satiety) {
        this.max_satiety = max_satiety;
    }

    public double getSatiety() {
        return satiety;
    }

    public void setSatiety(double satiety) {
        this.satiety = satiety;
    }

    @Override
    public int getMax_amount_in_cell() {
        return max_amount_in_cell;
    }

    @Override
    public void setMax_amount_in_cell(int max_amount_in_cell) {
        this.max_amount_in_cell = max_amount_in_cell;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Map<String, Integer> getChances_to_kill() {
        return chances_to_kill;
    }

    public void setChances_to_kill(Map<String, Integer> chances_to_kill) {
        this.chances_to_kill = chances_to_kill;
    }
}