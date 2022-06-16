package org.example.dao;

import org.example.domains.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalData {

    private Map<Animal, List<Animal>> animalsMap = new HashMap<>();
    private List<Animal> animalList = new ArrayList<>();

    {
        animalList.add(new Bear());
        animalList.add(new Boar());
        animalList.add(new Buffalo());
        animalList.add(new Caterpillar());
        animalList.add(new Deer());
        animalList.add(new Duck());
        animalList.add(new Eagle());
        animalList.add(new Fox());
        animalList.add(new Goat());
        animalList.add(new Horse());
        animalList.add(new Mouse());
        animalList.add(new Rabbit());
        animalList.add(new Sheep());
        animalList.add(new Snake());
        animalList.add(new Wolf());
    }

    private Map<Animal, List<Animal>> initializeDataMap() {

//        for (int i = 0; i < animalList.size(); i++) {
//            var a = animalList.get(i);
//
//
//            animalsMap.put(animalList.get(i), )
//        }
        return null;
    }

    public Map<Animal, List<Animal>> getAnimalsMap() {
        return animalsMap;
    }

    public void setAnimalsMap(Map<Animal, List<Animal>> animalsMap) {
        this.animalsMap = animalsMap;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }
}