package org.example.controllers;

import org.example.dao.AreaData;
import org.example.dao.JsonDao;
import org.example.dao.Properties;
import org.example.domains.Cell;
import org.example.utils.Statistic;
import org.example.utils.SystemIcons;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
                case 1:
                    startSimulation();
                case 2:
                    loadNewProperties();
            }
        } while (true);
    }

    private void startSimulation() {
        Properties properties = new JsonDao().load();
        AreaController area = generateSimulation(properties);

        area.run();

//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
//        executorService.scheduleAtFixedRate(area, 2, 2, TimeUnit.SECONDS);
//        executorService.scheduleAtFixedRate(new Statistic(), 2, 5, TimeUnit.SECONDS);

//        try {
////            Thread.sleep(20000);
////            executorService.shutdown();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    private AreaController generateSimulation(Properties properties) {
        AreaController area = new AreaController();
        area.setAreaData(new Cell[properties.getFieldWidth()][properties.getFieldLength()]);
        area.generateArea();
        area.generatePlants();
        area.generateAnimals();
        return area;
    }

    private void loadNewProperties() {

        //TODO think about have i to do this method.

    }


    private void printWelcomeText() {
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 30; j++) {
                if (i == 1 && (j == 1 || j == 5 || j == 7 || j == 8 || j == 10
                        || j == 14 || j == 18 || j == 21 || j == 25 || j == 27 || j == 28)) {
                    System.out.print(SystemIcons.YELLOW.getIcon());
                    System.out.print(" ");
                } else if (i == 2 && (j == 1 || j == 5 || j == 7 || j == 10 || j == 13 || j == 15
                        || j == 17 || j == 19 || j == 21 || j == 22 || j == 24 || j == 25 || j == 27)) {
                    System.out.print(SystemIcons.YELLOW.getIcon());
                    System.out.print(" ");
                } else if (i == 3 && (j == 1 || j == 3 || j == 5 || j == 7 || j == 8 || j == 10 || j == 13 || j == 17 || j == 19
                        || j == 21 || j == 23 || j == 25 || j == 27 || j == 28)) {
                    System.out.print(SystemIcons.YELLOW.getIcon());
                    System.out.print(" ");
                } else if (i == 4 && (j == 1 || j == 3 || j == 5 || j == 7 || j == 10 || j == 13 || j == 15 || j == 17 || j == 19 || j == 21
                        || j == 25 || j == 27)) {
                    System.out.print(SystemIcons.YELLOW.getIcon());
                    System.out.print(" ");
                } else if (i == 5 && (j == 2 || j == 4 || j == 7 || j == 8 || j == 10 || j == 11 || j == 14 || j == 18 || j == 21
                        || j == 25 || j == 27 || j == 28)) {
                    System.out.print(SystemIcons.YELLOW.getIcon());
                    System.out.print(" ");
                } else if (i == 7 && (j == 11 || j == 12 || j == 13 || j == 15 || j == 16 || j == 17)) {
                    System.out.print(SystemIcons.YELLOW.getIcon());
                    System.out.print(" ");
                } else if (i == 8 && (j == 12 || j == 15 || j == 17)) {
                    System.out.print(SystemIcons.YELLOW.getIcon());
                    System.out.print(" ");
                } else if (i == 9 && (j == 12 || j == 15 || j == 16 || j == 17)) {
                    System.out.print(SystemIcons.YELLOW.getIcon());
                    System.out.print(" ");
                } else if (i == 11 && (j == 4 ||  j == 7 || j == 8 || j == 10 ||  j == 15 || j == 18 || j == 21
                        || j == 23 || j == 24)) {
                    System.out.print(SystemIcons.YELLOW.getIcon());
                    System.out.print(" ");
                } else if (i == 12 && (j == 4 || j == 6 || j == 10 || j == 14 || j == 16 || j == 18 || j == 19
                        || j == 21 || j == 23 || j == 25)) {
                    System.out.print(SystemIcons.YELLOW.getIcon());
                    System.out.print(" ");
                } else if (i == 13 && (j == 4 || j == 7 || j == 10 || j == 14 || j == 15 || j == 16 || j == 18
                        || j == 20 || j == 21 || j == 23 || j == 25)) {
                    System.out.print(SystemIcons.YELLOW.getIcon());
                    System.out.print(" ");
                } else if (i == 14 && (j == 4 || j == 8 || j == 10 || j == 14 || j == 16 || j == 18 || j == 21 ||
                        j == 23 || j == 25)) {
                    System.out.print(SystemIcons.YELLOW.getIcon());
                    System.out.print(" ");
                } else if (i == 15 && (j == 4 || j == 6 || j == 7 ||  j == 10 || j == 11 || j == 12 || j == 14 ||
                        j == 16 || j == 18 || j == 21 || j == 23 || j == 24)) {
                    System.out.print(SystemIcons.YELLOW.getIcon());
                    System.out.print(" ");
                }
                //bounds
                else if ((i == 0 && j >= 0 && j < 30) || ((i >= 1 && i <= 29) && (j == 0 || j == 29)) || (i == 16 && (j >= 0 && j <= 29))) {
                    System.out.print(SystemIcons.BLUE.getIcon());
                    System.out.print(" ");
                } else {
                    //font
                    System.out.print(SystemIcons.BLACK.getIcon());
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}