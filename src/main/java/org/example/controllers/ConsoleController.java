package org.example.controllers;

import org.example.dao.LoadJsonDao;
import org.example.dao.Properties;
import org.example.domains.Field;

public class ConsoleController {

    public void startSimulation(){
        printWelcomePage();
        Properties properties = new LoadJsonDao().load();
        Field field = Field.builder()
                .fieldSize(new int[properties.getFieldLength()][properties.getFieldWidth()])
                .build();

    }

    private void printWelcomePage() {

    }
}