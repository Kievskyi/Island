package org.example.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.example.dao.AreaData;
import org.example.dao.PlantData;
import org.example.utils.AnimalIcon;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Plant implements Runnable {
    @JsonIgnore
    private AreaData areaData = AreaData.getInstance();
    private String icon = AnimalIcon.PLANT.getIcon();
    private int max_amount_in_cell = 200;
    @JsonIgnore
    private int leftAlive;
    private int max_amount_on_area;
    @JsonIgnore
    private int value_on_area;
    private int weight = 1;

    public void generatePlants() {

        PlantData plantData = PlantData.getInstance();
        int counterOfPlants = 0;

        if (max_amount_on_area == 0) {
            max_amount_on_area = plantData.getPlant().getMax_amount_on_area();
        }

        for (int width = 0; width < areaData.getArea().length; width++) {
            for (int length = 0; length < areaData.getArea()[width].length; length++) {
                if (areaData.getArea()[width][length].getPlant() != null) {
                    counterOfPlants += 1;
                }
            }
        }

        if (counterOfPlants > 0) {
            for (int width = 0; width < areaData.getArea().length; width++) {
                for (int length = 0; length < areaData.getArea()[width].length; length++) {
                    if (areaData.getArea()[width][length].getPlant() != null) {
                        areaData.getArea()[width][length].setPlant(null);
                    }
                }
            }
            setNewPlantOnArea();
        } else if (counterOfPlants == 0) {
            setNewPlantOnArea();
        }
        value_on_area = 0;
    }

    private void setNewPlantOnArea() {
        PlantData plantData = PlantData.getInstance();
        while (value_on_area < max_amount_on_area) {
            for (int width = 0; width < areaData.getArea().length; width++) {
                for (int length = 0; length < areaData.getArea()[width].length; length++) {
                    int random = ThreadLocalRandom.current().nextInt(0, 101);

                    if (random <= 2 && value_on_area < max_amount_on_area) {
                        Plant plant = new Plant();
                        plant.setWeight(plantData.getPlant().getWeight());
                        plant.setIcon(plantData.getPlant().getIcon());
                        plant.setMax_amount_in_cell(plantData.getPlant().getMax_amount_in_cell());
                        plant.setMax_amount_on_area(plantData.getPlant().getMax_amount_on_area());
                        plant.setLeftAlive(ThreadLocalRandom.current().nextInt(50, max_amount_in_cell + 1));
                        areaData.getArea()[width][length].setPlant(plant);
                        value_on_area += 1;
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        generatePlants();
    }
}