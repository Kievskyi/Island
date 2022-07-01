package org.example.controllers;

import org.example.dao.JsonFileDao;
import org.example.dao.Properties;
import org.example.domains.Plant;
import org.example.utils.Color;
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
            int choice = printMainMenuText();
            int exit = 0;

            if (choice == exit) {
                printExitText();
                break;
            }

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

    private int printMainMenuText() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.BG_BLACK.getColor() + Color.YELLOW.getColor() + " 1 - Start " + Color.RESET.getColor());
        System.out.println();
        System.out.println(Color.BG_BLACK.getColor() + Color.BLUE.getColor() + " 2 - Properties " + Color.RESET.getColor());
        System.out.println();
        System.out.println(Color.BG_BLACK.getColor() + Color.RED.getColor() + " 0 - Exit " + Color.RESET.getColor());
        System.out.println();

        int choice = scanner.nextInt();
        return choice;
    }

    private void startSimulation() throws InterruptedException {
        properties = new JsonFileDao().load();
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
            System.out.println();
            System.out.println(Color.BG_YELLOW.getColor() + Color.BLACK.getColor() + " You want to load new properties? " + Color.RESET.getColor());
            System.out.println();
            System.out.println(Color.BG_BLACK.getColor() + Color.GREEN.getColor() + " 1 - Yes " + Color.RESET.getColor());
            System.out.println();
            System.out.println(Color.BG_BLACK.getColor() + Color.RED.getColor() + " 2 - No " + Color.RESET.getColor());

            String answer = scanner.nextLine();

            if (answer.equals("1")) {
                properties = new JsonFileDao().load();
                System.out.println();
                break;
            } else if (answer.equals("2")) {
                System.out.println();
                break;
            }
        } while (true);
    }

    private void printExitText() {

        System.out.println();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 16; j++) {
                if (i == 1 && (j == 1 || j == 2 || j == 3 || j == 6 || j == 10 || j == 12
                        || j == 13 || j == 14)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon() + " ");
                } else if (i == 2 && (j == 1 || j == 4 || j == 7 || j == 9 || j == 12)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon() + " ");
                } else if (i == 3 && (j == 1 || j == 2 || j == 3 || j == 8 || j == 12
                        || j == 13 || j == 14)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon() + " ");
                } else if (i == 4 && (j == 1 || j == 4 || j == 8 || j == 12)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon() + " ");
                } else if (i == 5 && (j == 1 || j == 2 || j == 3 || j == 8 || j == 12
                        || j == 13 || j == 14)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon() + " ");
                } else if ((i == 0 || i == 6) || ((i > 0 && i < 6) && (j == 0 || j == 15))) {
                    System.out.print(SystemIcon.BLUE_SQUARE.getIcon() + " ");
                } else {
                    System.out.print(SystemIcon.BLACK_SQUARE.getIcon() + " ");
                }
            }
            System.out.println();
        }
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
                else if ((i == 0 && j >= 0 && j < 30) || ((i >= 1 && i <= 29) && (j == 0 || j == 29)) || (i == 16 && (j >= 0 && j <= 29))) {
                    System.out.print(SystemIcon.BLUE_SQUARE.getIcon());
                    System.out.print(" ");
                } else {
                    System.out.print(SystemIcon.BLACK_SQUARE.getIcon());
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}