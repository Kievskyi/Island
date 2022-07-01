package org.example.dao;

import lombok.Getter;
import lombok.Setter;
import org.example.domains.*;
import org.example.utils.AnimalKind;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class AnimalData {
    private static AnimalData INSTANCE ;
    private AreaData areaData = AreaData.getInstance();
    private Map<AnimalKind, Animal> animalMap = new HashMap<>();

    private AnimalData() {
    }

    public void generateAnimals(Properties properties) {
        int maxCounter = AnimalKind.values().length;
        int counter = 0;
        animalMap.putAll(properties.getPropertiesOfAnimals());

        while (counter < 15) {
            for (int i = 0; i < areaData.getArea().length; i++) {
                for (int j = 0; j < areaData.getArea()[i].length; j++) {
                    int random = ThreadLocalRandom.current().nextInt(0, 140);

                    if (random <= 1 && areaData.getArea()[i][j].getAnimals_in_cell().size() == 0 && areaData.getArea()[i][j].getPlant() == null
                            && counter < maxCounter) {

                        Animal animal = new AnimalFactory().create(AnimalKind.valueOf(counter));
                        animal.setAnimalKind(AnimalKind.valueOf(counter));

                        for (Map.Entry<AnimalKind, Animal> map : properties.getPropertiesOfAnimals().entrySet()) {
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
                        areaData.getArea()[i][j].setAnimals_in_cell(animal);
                        counter++;
                    }
                }
            }
        }
    }

    public static AnimalData getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new AnimalData();
        }
        return INSTANCE;
    }
}