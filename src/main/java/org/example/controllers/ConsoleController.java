package org.example.controllers;

import org.example.dao.LoadJsonDao;
import org.example.domains.Map;

public class ConsoleController {
    LoadJsonDao jsonDao = new LoadJsonDao();

    public void startSimulation(){
        printWelcomePage();
        jsonDao.load();
        Map map = new Map().generateMap();
    }

    private void printWelcomePage() {

    }
}
