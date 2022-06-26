package org.example.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.example.domains.*;
import org.example.utils.AnimalKind;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Properties {
    @JsonProperty("Area length")
    private int fieldLength;
    @JsonProperty("Area width")
    private int fieldWidth;
    @JsonProperty("Plant properties")
    private Plant plant = new Plant();
    @JsonProperty("Properties of animals")
    private Map<AnimalKind, Animal> propertiesOfAnimals;

    {
        propertiesOfAnimals = new HashMap<>() {{
            put(AnimalKind.BEAR, new Bear());
            put(AnimalKind.BOAR, new Boar());
            put(AnimalKind.BUFFALO, new Buffalo());
            put(AnimalKind.CATERPILLAR, new Caterpillar());
            put(AnimalKind.DEER, new Deer());
            put(AnimalKind.DUCK, new Duck());
            put(AnimalKind.EAGLE, new Eagle());
            put(AnimalKind.FOX, new Fox());
            put(AnimalKind.GOAT, new Goat());
            put(AnimalKind.HORSE, new Horse());
            put(AnimalKind.MOUSE, new Mouse());
            put(AnimalKind.RABBIT, new Rabbit());
            put(AnimalKind.SHEEP, new Sheep());
            put(AnimalKind.SNAKE, new Snake());
            put(AnimalKind.WOLF, new Wolf());
        }};
    }

    public Properties(int fieldLength, int fieldWidth, Plant plant, Map<AnimalKind, Animal> propertiesOfAnimals) {
        this.fieldLength = fieldLength;
        this.fieldWidth = fieldWidth;
        this.plant = plant;
        this.propertiesOfAnimals = propertiesOfAnimals;
    }

    public Properties() {
    }
}