package org.example.dao;

import org.example.domains.*;

import java.util.ArrayList;
import java.util.List;

public class AnimalData {

    private List<Animal> animalList = new ArrayList<>();
//    private Map<Animal, List<Animal>> animalsMap = new HashMap<>();

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

//        initializeMap();
    }

//    private void initializeMap() {
//
//        for (int i = 0; i < animalList.size(); i++) {
//            int random = ThreadLocalRandom.current().nextInt(1, animalList.get(i).getMax_amount_in_cell());
//            List<Animal> list = new ArrayList<>();
//            for (int j = 0; j < random; j++) {
//                list.add(animalList.get(i));
//            }
//            animalsMap.put(animalList.get(i), list);
//        }
//
//    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }
}