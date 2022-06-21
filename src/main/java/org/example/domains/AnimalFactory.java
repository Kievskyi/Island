package org.example.domains;

import org.example.utils.AnimalType;

public class AnimalFactory {
    private Animal animalType;

    public Animal create(AnimalType type) {

        switch (type) {
            case BEAR:
                animalType = new Bear();
            case BOAR:
                animalType = new Bear();
            case BUFFALO:
                animalType = new Buffalo();
            case CATERPILLAR:
                animalType = new Caterpillar();
            case DEER:
                animalType = new Deer();
            case DUCK:
                animalType = new Duck();
            case EAGLE:
                animalType = new Eagle();
            case FOX:
                animalType = new Fox();
            case GOAT:
                animalType = new Goat();
            case HORSE:
                animalType = new Horse();
            case MOUSE:
                animalType = new Mouse();
            case RABBIT:
                animalType = new Rabbit();
            case SHEEP:
                animalType = new Sheep();
            case SNAKE:
                animalType = new Snake();
            case WOLF:
                animalType = new Wolf();
        }

        return animalType;
    }
}