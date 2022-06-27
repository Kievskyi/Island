package org.example.controllers;

import org.example.dao.JsonDao;
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
            System.out.println();
            System.out.println(Color.BG_YELLOW.getColor() + Color.BLACK.getColor() + " You want to load new properties? " + Color.RESET.getColor());
            System.out.println();
            System.out.println(Color.BG_BLACK.getColor() + Color.GREEN.getColor() + " 1 - Yes " + Color.RESET.getColor());
            System.out.println();
            System.out.println(Color.BG_BLACK.getColor() + Color.RED.getColor() + " 2 - No " + Color.RESET.getColor());

            String answer = scanner.nextLine();

            if (answer.equals("1")) {
                properties = new JsonDao().load();
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

        for (int width = 0; width < 7; width++) {
            for (int length = 0; length < 16; length++) {
                if (width == 1 && (length == 1 || length == 2 || length == 3 || length == 6 || length == 10 || length == 12
                        || length == 13 || length == 14)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon() + " ");
                } else if (width == 2 && (length == 1 || length == 4 || length == 7 || length == 9 || length == 12)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon() + " ");
                } else if (width == 3 && (length == 1 || length == 2 || length == 3 || length == 8 || length == 12
                        || length == 13 || length == 14)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon() + " ");
                } else if (width == 4 && (length == 1 || length == 4 || length == 8 || length == 12)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon() + " ");
                } else if (width == 5 && (length == 1 || length == 2 || length == 3 || length == 8 || length == 12
                        || length == 13 || length == 14)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon() + " ");
                } else if ((width == 0 || width == 6) || ((width > 0 && width < 6) && (length == 0 || length == 15))) {
                    System.out.print(SystemIcon.BLUE_SQUARE.getIcon() + " ");
                } else {
                    System.out.print(SystemIcon.BLACK_SQUARE.getIcon() + " ");
                }
            }
            System.out.println();
        }
    }

    private void printWelcomeText() {
        for (int width = 0; width < 17; width++) {
            for (int length = 0; length < 30; length++) {
                if (width == 1 && (length == 1 || length == 5 || length == 7 || length == 8 || length == 10
                        || length == 14 || length == 18 || length == 21 || length == 25 || length == 27 || length == 28)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (width == 2 && (length == 1 || length == 5 || length == 7 || length == 10 || length == 13 || length == 15
                        || length == 17 || length == 19 || length == 21 || length == 22 || length == 24 || length == 25 || length == 27)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (width == 3 && (length == 1 || length == 3 || length == 5 || length == 7 || length == 8 || length == 10 || length == 13 || length == 17 || length == 19
                        || length == 21 || length == 23 || length == 25 || length == 27 || length == 28)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (width == 4 && (length == 1 || length == 3 || length == 5 || length == 7 || length == 10 || length == 13 || length == 15 || length == 17 || length == 19 || length == 21
                        || length == 25 || length == 27)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (width == 5 && (length == 2 || length == 4 || length == 7 || length == 8 || length == 10 || length == 11 || length == 14 || length == 18 || length == 21
                        || length == 25 || length == 27 || length == 28)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (width == 7 && (length == 11 || length == 12 || length == 13 || length == 15 || length == 16 || length == 17)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (width == 8 && (length == 12 || length == 15 || length == 17)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (width == 9 && (length == 12 || length == 15 || length == 16 || length == 17)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (width == 11 && (length == 4 || length == 7 || length == 8 || length == 10 || length == 15 || length == 18 || length == 21
                        || length == 23 || length == 24)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (width == 12 && (length == 4 || length == 6 || length == 10 || length == 14 || length == 16 || length == 18 || length == 19
                        || length == 21 || length == 23 || length == 25)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (width == 13 && (length == 4 || length == 7 || length == 10 || length == 14 || length == 15 || length == 16 || length == 18
                        || length == 20 || length == 21 || length == 23 || length == 25)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (width == 14 && (length == 4 || length == 8 || length == 10 || length == 14 || length == 16 || length == 18 || length == 21 ||
                        length == 23 || length == 25)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                } else if (width == 15 && (length == 4 || length == 6 || length == 7 || length == 10 || length == 11 || length == 12 || length == 14 ||
                        length == 16 || length == 18 || length == 21 || length == 23 || length == 24)) {
                    System.out.print(SystemIcon.YELLOW_SQUARE.getIcon());
                    System.out.print(" ");
                }
                else if ((width == 0 && length >= 0 && length < 30) || ((width >= 1 && width <= 29) && (length == 0 || length == 29)) || (width == 16 && (length >= 0 && length <= 29))) {
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