package org.example.controllers;

import org.example.dao.AreaData;
import org.example.dao.JsonDao;
import org.example.dao.Properties;
import org.example.domains.Cell;
import org.example.utils.Statistic;

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
                case 1 : startSimulation();
                case 2 : loadNewProperties();
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
        System.out.println("Hello User!\n\n\n\n");
    }
}