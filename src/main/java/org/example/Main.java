package org.example;

import org.example.controllers.ConsoleController;
import org.example.utils.Statistic;

public class Main {
    public static void main(String[] args) {
        ConsoleController consoleController = new ConsoleController();
        consoleController.showMainPage();
//        Statistic statistic = Statistic.getInstance();
//        statistic.showStatistic();


        //TODO repair a multithreading


//        it's a converter

//        for(char c : new StringBuilder().appendCodePoint(0x2B1B).toString().toCharArray()) {
//            System.out.print("\\u" + String.valueOf(Integer.toHexString(c)));
//        }
//        System.out.println();
    }
}