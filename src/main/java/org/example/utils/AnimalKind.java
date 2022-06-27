package org.example.utils;

import org.example.domains.*;

public enum AnimalKind {
    BEAR(0),
    BOAR(1),
    BUFFALO(2),
    CATERPILLAR(3),
    DEER(4),
    DUCK(5),
    EAGLE(6),
    FOX(7),
    GOAT(8),
    HORSE(9),
    MOUSE(10),
    RABBIT(11),
    SHEEP(12),
    SNAKE(13),
    WOLF(14);

    private int value;

    AnimalKind(int value) {
        this.value = value;
    }

    public static AnimalKind valueOf(int value) {
        AnimalKind animalKind = null;

        switch (value) {
            case 0:
                animalKind = BEAR;
                break;
            case 1:
                animalKind = BOAR;
                break;
            case 2:
                animalKind = BUFFALO;
                break;
            case 3:
                animalKind = CATERPILLAR;
                break;
            case 4:
                animalKind = DEER;
                break;
            case 5:
                animalKind = DUCK;
                break;
            case 6:
                animalKind = EAGLE;
                break;
            case 7:
                animalKind = FOX;
                break;
            case 8:
                animalKind = GOAT;
                break;
            case 9:
                animalKind = HORSE;
                break;
            case 10:
                animalKind = MOUSE;
                break;
            case 11:
                animalKind = RABBIT;
                break;
            case 12:
                animalKind = SHEEP;
                break;
            case 13:
                animalKind = SNAKE;
                break;
            case 14:
                animalKind = WOLF;
                break;
        }
        return animalKind;
    }
}