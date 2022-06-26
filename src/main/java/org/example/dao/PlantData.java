package org.example.dao;

import lombok.Getter;
import lombok.Setter;
import org.example.domains.Plant;

@Getter
@Setter
public class PlantData {

    private static PlantData INSTANCE ;
    private Plant plant = new Plant();

    public PlantData() {
    }

    public static PlantData getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PlantData();
        }
        return INSTANCE;
    }

    public void initializePlant(Properties properties) {
        plant.setIcon(properties.getPlant().getIcon());
        plant.setMax_amount_on_area(properties.getPlant().getMax_amount_on_area());
        plant.setWeight(properties.getPlant().getWeight());
        plant.setMax_amount_in_cell(properties.getPlant().getMax_amount_in_cell());
    }
}