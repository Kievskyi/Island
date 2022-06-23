package org.example.domains;

import org.example.utils.AnimalType;

public class AnimalFactory {
    private Animal animalType;

    public Animal create(AnimalType type) {

        switch (type) {
            case BEAR:
                animalType = new Bear();
                break;
            case BOAR:
                animalType = new Boar();
                break;

            case BUFFALO:
                animalType = new Buffalo();
                break;

            case CATERPILLAR:
                animalType = new Caterpillar();
                break;

            case DEER:
                animalType = new Deer();
                break;

            case DUCK:
                animalType = new Duck();
                break;

            case EAGLE:
                animalType = new Eagle();
                break;

            case FOX:
                animalType = new Fox();
                break;

            case GOAT:
                animalType = new Goat();
                break;

            case HORSE:
                animalType = new Horse();
                break;

            case MOUSE:
                animalType = new Mouse();
                break;

            case RABBIT:
                animalType = new Rabbit();
                break;

            case SHEEP:
                animalType = new Sheep();
                break;

            case SNAKE:
                animalType = new Snake();
                break;

            case WOLF:
                animalType = new Wolf();
                break;
        }

        return animalType;
    }
}