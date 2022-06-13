package org.example;

import org.example.controllers.ConsoleController;
import org.example.dao.LoadJsonDao;
import org.example.domains.Animal;
import org.example.domains.Wolf;

public class Main {
    public static void main(String[] args) {
        ConsoleController consoleController = new ConsoleController();
        consoleController.startSimulation();
    }
}