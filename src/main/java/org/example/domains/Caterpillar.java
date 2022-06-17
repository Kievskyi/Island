package org.example.domains;

import org.example.dao.AreaData;
import org.example.utils.AnimalIcons;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Caterpillar extends Herbivore {

    private AreaData areaData = AreaData.getInstance();
    private String kind_of_animal = "Caterpillar";
    private String icon = AnimalIcons.CATERPILLAR.getIcon();
    private double weight = 0.01;
    private double max_satiety = 0.0;
    private double satiety;
    private int max_amount_in_cell = 1000;
    private int maxSpeed = 0;
    private Map<String, Integer> chances_to_kill;

    {
        chances_to_kill = new HashMap<>() {{
            put("Wolf", 0);
            put("Snake", 0);
            put("Fox", 0);
            put("Eagle", 0);
            put("Horse", 0);
            put("Deer", 0);
            put("Rabbit", 0);
            put("Mouse", 0);
            put("Goat", 0);
            put("Sheep", 0);
            put("Boar", 0);
            put("Buffalo", 0);
            put("Duck", 0);
            put("Bear", 0);
            put("Plant", 100);
        }};
    }

    @Override
    public void eat(Animal animal, int length, int width) {

    }

    @Override
    public void move(Animal animal, int width, int length) {
        int lengthArea = areaData.getArea()[0].length - 1;
        int widthArea = areaData.getArea().length - 1;
        int randomSpeed = ThreadLocalRandom.current().nextInt(0, maxSpeed + 1);
        int randomCorner = ThreadLocalRandom.current().nextInt(0, 3);
        int randomBounds = ThreadLocalRandom.current().nextInt(0, 5);
        int randomCenter = ThreadLocalRandom.current().nextInt(0, 8);
        if (width == widthArea && length == lengthArea) {
            // bottom right corner
            if (randomCorner == 0) {
                //to up
                areaData.getArea()[widthArea - randomSpeed][lengthArea].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomCorner == 1) {
                //diagonally
                areaData.getArea()[widthArea - randomSpeed][lengthArea - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomCorner == 2) {
                //to left
                areaData.getArea()[widthArea][lengthArea - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            }
        } else if (length == 0 && width == 0) {
            //top left corner
            if (randomCorner == 0) {
                //to right
                areaData.getArea()[width][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomCorner == 1) {
                //diagonally
                areaData.getArea()[width + randomSpeed][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomCorner == 2) {
                //to down
                areaData.getArea()[width + randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            }
        } else if (length == lengthArea && width == 0) {
            //top right corner
            if (randomCorner == 0) {
                //to left
                areaData.getArea()[width][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomCorner == 1) {
                //diagonally
                areaData.getArea()[widthArea - randomSpeed][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomCorner == 2) {
                //to down
                areaData.getArea()[width + randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            }
        } else if (length == 0 && width == widthArea) {
            //bottom left corner
            if (randomCorner == 0) {
                //to up
                areaData.getArea()[widthArea - randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomCorner == 1) {
                //diagonally
                areaData.getArea()[width - randomSpeed][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomCorner == 2) {
                //to right
                areaData.getArea()[width][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            }
        } else if (width == 0 && (length >= 1 && length < lengthArea)) {
            //top bounds
            if (randomBounds == 0) {
                //to right
                areaData.getArea()[width][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 1) {
                //to left
                areaData.getArea()[width][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 2) {
                //to down
                areaData.getArea()[width + randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 3) {
                //right diagonal
                areaData.getArea()[width + randomSpeed][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 4) {
                //left diagonal
                areaData.getArea()[width + randomSpeed][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            }
        } else if (width == widthArea && (length >= 1 && length < lengthArea)) {
            //bottom bounds
            if (randomBounds == 0) {
                //to right
                areaData.getArea()[width][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 1) {
                //to left
                areaData.getArea()[width][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 2) {
                //to up
                areaData.getArea()[widthArea - randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 3) {
                //right diagonal
                areaData.getArea()[width - randomSpeed][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 4) {
                //left diagonal
                areaData.getArea()[width - randomSpeed][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            }
        } else if (length == 0 && (width >= 1 && width < widthArea)) {
            //left bounds
            if (randomBounds == 0) {
                //to up
                areaData.getArea()[width - randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 1) {
                //to right
                areaData.getArea()[width][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 2) {
                //to down
                areaData.getArea()[width + randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 3) {
                //top diagonal
                areaData.getArea()[width - randomSpeed][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 4) {
                //bottom diagonal
                areaData.getArea()[width + randomSpeed][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            }
        } else if (length== lengthArea && (width >= 1 && width < widthArea)) {
            //right bounds
            if (randomBounds == 0) {
                //to up
                areaData.getArea()[width - randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 1) {
                //to left
                areaData.getArea()[width][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 2) {
                //to down
                areaData.getArea()[width + randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 3) {
                //top diagonal
                areaData.getArea()[width - randomSpeed][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 4) {
                //bottom diagonal
                areaData.getArea()[width + randomSpeed][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            }
        } else if ((width >= 1 && width < widthArea) && length == 1) {
            //left central bounds
            if (randomBounds == 0) {
                //to right
                areaData.getArea()[width][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomCenter == 1) {
                //to down
                areaData.getArea()[width + randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomCenter == 2) {
                //to up
                areaData.getArea()[width - randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomCenter == 3) {
                //right upper diagonal
                areaData.getArea()[width - randomSpeed][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomCenter == 4) {
                //right bottom diagonal
                areaData.getArea()[width + randomSpeed][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            }
        } else if ((width >= 1 && width < widthArea) && length == lengthArea - 1) {
            //right central bounds
            if (randomBounds == 0) {
                //to left
                areaData.getArea()[width][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 1) {
                //to down
                areaData.getArea()[width + randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 2) {
                //to up
                areaData.getArea()[width - randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 3) {
                //left upper diagonal
                areaData.getArea()[width - randomSpeed][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 4) {
                //left bottom diagonal
                areaData.getArea()[width + randomSpeed][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            }
        } else if (width == widthArea - 1 && (length >= 1 && length < lengthArea)) {
            //bottom central bounds
            if (randomBounds == 0) {
                //to left
                areaData.getArea()[width][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 1) {
                //to right
                areaData.getArea()[width][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 2) {
                //to up
                areaData.getArea()[width - randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 3) {
                //left upper diagonal
                areaData.getArea()[width - randomSpeed][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 4) {
                //right upper diagonal
                areaData.getArea()[width - randomSpeed][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            }
        } else if (width == 1 && (length >= 1 && length < lengthArea)) {
            //top central bounds
            if (randomBounds == 0) {
                //to left
                areaData.getArea()[width][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 1) {
                //to right
                areaData.getArea()[width][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 2) {
                //to down
                areaData.getArea()[width + randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 3) {
                //left bottom diagonal
                areaData.getArea()[width + randomSpeed][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 4) {
                //right bottom diagonal
                areaData.getArea()[width + randomSpeed][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            }
        } else {
            //center
            if (randomCenter == 0) {
                //to left
                areaData.getArea()[width][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 1) {
                //to right
                areaData.getArea()[width][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 2) {
                //to down
                areaData.getArea()[width + randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 3) {
                //left bottom diagonal
                areaData.getArea()[width + randomSpeed][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomBounds == 4) {
                //right bottom diagonal
                areaData.getArea()[width + randomSpeed][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomCenter == 5) {
                //to up
                areaData.getArea()[width - randomSpeed][length].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomCenter == 6) {
                //left upper diagonal
                areaData.getArea()[width - randomSpeed][length - randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            } else if (randomCenter == 7) {
                //right upper diagonal
                areaData.getArea()[width -randomSpeed][length + randomSpeed].setAnimal(animal);
                areaData.getArea()[width][length].getAnimal().remove(animal);
            }
        }
    }

    @Override
    public void reproduce(AreaData areaData) {

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