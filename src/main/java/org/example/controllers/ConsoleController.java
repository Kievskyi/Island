package org.example.controllers;

import org.example.dao.JsonDao;
import org.example.dao.Properties;
import org.example.domains.Plant;
import org.example.utils.Statistic;
import org.example.utils.SystemIcon;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConsoleController {
    private Statistic statistic = Statistic.getInstance();
    private Properties properties;

    public void showMainPage() {
        printWelcomeText();
        choiceOfUser();
    }

    private void choiceOfUser() {


        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1 - Start\n");
            System.out.println("2 - Properties\n");
            System.out.println("0 - Exit\n");

            int choice = scanner.nextInt();
            int exit = 0;

            if (choice == exit)
                break;

            switch (choice) {
                case 1:
                    try {
                        startSimulation();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    loadNewProperties();
                    break;
            }
        } while (true);
    }

    private void startSimulation() throws InterruptedException {
        properties = new JsonDao().load();
        AreaController area = generateSimulation(properties);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        executorService.scheduleAtFixedRate(area, 1, 1, TimeUnit.SECONDS);
        Thread.sleep(150);
        executorService.scheduleAtFixedRate(statistic, 1, 1, TimeUnit.SECONDS);
        Thread.sleep(100);
        executorService.scheduleAtFixedRate(new Plant(), 3, 3, TimeUnit.SECONDS);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            int exit = scanner.nextInt();
            if (exit == 0) {
                executorService.shutdown();
                break;
            }
        }
    }

    private AreaController generateSimulation(Properties properties) {
        AreaController area = new AreaController();
        area.initializeAndGenerate(properties);
        return area;
    }

    private void loadNewProperties() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("You want to load new properties?");
            System.out.println("1 - Yes");
            System.out.println("2 - No");

            String answer = scanner.nextLine();

            if (answer.equals("1")) {
                properties = new JsonDao().load();
                break;
            } else if (answer.equals("2")){
                break;
            }
        } while (true);
    }


    private void printWelcomeText() {
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 30; j++) {
                if (i == 1 && (j == 1 || j == 5 || j == 7 || j == 8 || j == 10
                        || j == 14 || j == 18 || j == 21 || j == 25 || j == 27 || j == 28)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (i == 2 && (j == 1 || j == 5 || j == 7 || j == 10 || j == 13 || j == 15
                        || j == 17 || j == 19 || j == 21 || j == 22 || j == 24 || j == 25 || j == 27)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (i == 3 && (j == 1 || j == 3 || j == 5 || j == 7 || j == 8 || j == 10 || j == 13 || j == 17 || j == 19
                        || j == 21 || j == 23 || j == 25 || j == 27 || j == 28)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (i == 4 && (j == 1 || j == 3 || j == 5 || j == 7 || j == 10 || j == 13 || j == 15 || j == 17 || j == 19 || j == 21
                        || j == 25 || j == 27)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (i == 5 && (j == 2 || j == 4 || j == 7 || j == 8 || j == 10 || j == 11 || j == 14 || j == 18 || j == 21
                        || j == 25 || j == 27 || j == 28)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (i == 7 && (j == 11 || j == 12 || j == 13 || j == 15 || j == 16 || j == 17)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (i == 8 && (j == 12 || j == 15 || j == 17)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (i == 9 && (j == 12 || j == 15 || j == 16 || j == 17)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (i == 11 && (j == 4 || j == 7 || j == 8 || j == 10 || j == 15 || j == 18 || j == 21
                        || j == 23 || j == 24)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (i == 12 && (j == 4 || j == 6 || j == 10 || j == 14 || j == 16 || j == 18 || j == 19
                        || j == 21 || j == 23 || j == 25)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (i == 13 && (j == 4 || j == 7 || j == 10 || j == 14 || j == 15 || j == 16 || j == 18
                        || j == 20 || j == 21 || j == 23 || j == 25)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (i == 14 && (j == 4 || j == 8 || j == 10 || j == 14 || j == 16 || j == 18 || j == 21 ||
                        j == 23 || j == 25)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (i == 15 && (j == 4 || j == 6 || j == 7 || j == 10 || j == 11 || j == 12 || j == 14 ||
                        j == 16 || j == 18 || j == 21 || j == 23 || j == 24)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                }
                //bounds
                else if ((i == 0 && j >= 0 && j < 30) || ((i >= 1 && i <= 29) && (j == 0 || j == 29)) || (i == 16 && (j >= 0 && j <= 29))) {
                    System.out.print(SystemIcon.BLUE_SQUARE.getIcon());
                    System.out.print(" ");
                } else {
                    //font
                    System.out.print(SystemIcon.BLACK_SQUARE.getIcon());
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}