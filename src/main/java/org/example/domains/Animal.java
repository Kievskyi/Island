package org.example.domains;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import org.example.dao.AnimalData;
import org.example.dao.AreaData;
import org.example.utils.AnimalKind;
import org.example.utils.Statistic;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bear.class, name = "Bear"),
        @JsonSubTypes.Type(value = Boar.class, name = "Boar"),
        @JsonSubTypes.Type(value = Buffalo.class, name = "Buffalo"),
        @JsonSubTypes.Type(value = Caterpillar.class, name = "Caterpillar"),
        @JsonSubTypes.Type(value = Deer.class, name = "Deer"),
        @JsonSubTypes.Type(value = Duck.class, name = "Duck"),
        @JsonSubTypes.Type(value = Eagle.class, name = "Eagle"),
        @JsonSubTypes.Type(value = Fox.class, name = "Fox"),
        @JsonSubTypes.Type(value = Goat.class, name = "Goat"),
        @JsonSubTypes.Type(value = Horse.class, name = "Horse"),
        @JsonSubTypes.Type(value = Mouse.class, name = "Mouse"),
        @JsonSubTypes.Type(value = Rabbit.class, name = "Rabbit"),
        @JsonSubTypes.Type(value = Sheep.class, name = "Sheep"),
        @JsonSubTypes.Type(value = Snake.class, name = "Snake"),
        @JsonSubTypes.Type(value = Wolf.class, name = "Wolf"),

})
@Data
public abstract class Animal {

    private String icon;
    private double weight;
    private double max_satiety;
    @JsonIgnore
    private double satiety;
    private double energyConsumption;
    @JsonIgnore
    private int max_amount_in_cell;
    @JsonIgnore
    private int leftAlive;
    private int maxSpeed;
    private AnimalKind animalKind;
    @JsonIgnore
    private Statistic statistic = Statistic.getInstance();
    @JsonIgnore
    private AreaData areaData = AreaData.getInstance();
    @JsonIgnore
    private AnimalData animalData = AnimalData.getInstance();

    private Map<AnimalKind, Integer> chances_to_kill;

    public void eat(Animal animal, int width, int length) {


        if (chances_to_kill.containsKey(animal.getAnimalKind())) {
            for (Map.Entry<AnimalKind, Integer> chances : chances_to_kill.entrySet()) {
                if (chances.getKey().equals(animal.getAnimalKind())) {
                    int random = ThreadLocalRandom.current().nextInt(0, 101);
                    if (random <= chances.getValue()) {
                        if (satiety < max_satiety) {        //Here we 100% will kill prey animal
                            while (satiety < max_satiety || animal.getLeftAlive() > 0) {
                                if (animal.getAnimalKind().equals(AnimalKind.CATERPILLAR)) {
                                    if ((satiety + (animal.getWeight() * animal.getLeftAlive())) > max_satiety) {
                                        satiety = max_satiety;
                                        sendKillToStatistic(animalKind);
                                        sendDiedAnimalToStatistic(animal);
                                        animal.setLeftAlive(0);
                                    } else {
                                        satiety += animal.getWeight();
                                        sendKillToStatistic(animalKind);
                                        sendDiedAnimalToStatistic(animal);
                                        animal.setLeftAlive(0);

                                    }
                                } else {
                                    if (satiety + animal.getWeight() > max_satiety) {
                                        if (animal.getLeftAlive() == 1) {
                                            satiety = max_satiety;
                                            sendKillToStatistic(animalKind);
                                            sendDiedAnimalToStatistic(animal);
                                            animal.setLeftAlive(0);
                                        } else {
                                            satiety = max_satiety;
                                            animal.setLeftAlive(animal.getLeftAlive() - 1);
                                            sendKillToStatistic(animalKind);
                                            sendDiedAnimalToStatistic(animal);
                                        }
                                    } else {
                                        satiety += animal.getWeight();
                                        animal.setLeftAlive(animal.getLeftAlive() - 1);
                                        sendKillToStatistic(animalKind);
                                        sendDiedAnimalToStatistic(animal);
                                    }
                                }
                            }
                            if (animal.getLeftAlive() == 0) {
                                areaData.getArea()[width][length].getAnimals_in_cell().remove(animal);
                            }
                        }
                    }
                }
            }
        }
    }

    public void eat(Plant plant, int width, int length) {

        if (satiety < max_satiety) {
            while (satiety < max_satiety || plant.getLeftAlive() > 0) {   //TODO BUG!!!
                if (plant.getLeftAlive() == 1) {
                    satiety += plant.getWeight();
                    plant.setLeftAlive(0);
                    areaData.getArea()[width][length].setPlant(null);
                } else {
                    satiety += plant.getWeight();
                    plant.setLeftAlive(plant.getLeftAlive() - 1);
                }
            }
        }
    }

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

    public void reproduce() {
        int randomWidth = ThreadLocalRandom.current().nextInt(0, areaData.getArea().length);
        int randomLength = ThreadLocalRandom.current().nextInt(0, areaData.getArea()[0].length);

        Animal animal = new AnimalFactory().create(animalKind);
        animal.setAnimalKind(animalKind);

        for (Map.Entry<AnimalKind, Animal> map : animalData.getAnimalMap().entrySet()) {
            if (map.getKey().equals(animal.getAnimalKind())) {
                animal.setAnimalKind(map.getValue().getAnimalKind());
                animal.setChances_to_kill(map.getValue().getChances_to_kill());
                animal.setEnergyConsumption(map.getValue().getEnergyConsumption());
                animal.setIcon(map.getValue().getIcon());
                animal.setMax_amount_in_cell(map.getValue().getMax_amount_in_cell());
                animal.setWeight(map.getValue().getWeight());
                animal.setMax_satiety(map.getValue().getMax_satiety());
                animal.setMaxSpeed(map.getValue().getMaxSpeed());
            }
        }
        areaData.getArea()[randomWidth][randomLength].setAnimals_in_cell(animal);
    }

    private void sendDiedAnimalToStatistic(Animal animal) {
        for (Map.Entry<AnimalKind, Integer> map : statistic.getMapOfDiedAnimals().entrySet()) {
            if (map.getKey().equals(animal.getAnimalKind())) {
                map.setValue(map.getValue() + 1);
            }
        }
    }

    private void sendKillToStatistic(AnimalKind animalKind) {
        for (Map.Entry<AnimalKind, Integer> map : statistic.getMapOfKills().entrySet()) {
            if (map.getKey().equals(animalKind)) {
                map.setValue(map.getValue() + 1);
            }
        }
    }

    public void setSatietyAfterRound() {
        if (satiety > 0) {
            satiety -= energyConsumption;
        }
    }

    @JsonIgnore
    public boolean isSatietyIsNull() {
        boolean isHungry = false;
        if (satiety > 0) {
            isHungry = false;
        } else if (satiety <= 0) {
            isHungry = true;
        }
        return isHungry;
    }
}