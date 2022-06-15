package org.example.controllers;

import org.example.dao.JsonDao;
import org.example.dao.Properties;
import org.example.domains.Area;

import java.util.Scanner;

public class ConsoleController {

    public void showMainPage() {
        printWelcomeText();
        choiceOfUser();
    }

    private void choiceOfUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Start\n");
        System.out.println("2 - Properties\n");
        System.out.println("0 - Exit\n");

        do {
            int choice = scanner.nextInt();
            int exit = 0;

            if (choice == exit)
                break;

            switch (choice) {
                case 1 -> startSimulation();
                case 2 -> loadNewProperties();
            }
        } while (true);
    }

    private void startSimulation() {
        Properties properties = new JsonDao().load();
        Area area = Area.builder()
                .fieldSize(new int[properties.getFieldWidth()][properties.getFieldLength()])
                .build();
        area.generateField();
    }

    private void loadNewProperties() {

        //TODO think about have i to do this method.

    }


    private void printWelcomeText() {
        System.out.println("Hello User!\n\n\n\n");
    }
}