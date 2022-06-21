package org.example;

import org.example.controllers.ConsoleController;

public class Main {
    public static void main(String[] args) {
        ConsoleController consoleController = new ConsoleController();
        consoleController.showMainPage();


//        it's a converter

//        for(char c : new StringBuilder().appendCodePoint(0x2694).toString().toCharArray()) {
//            System.out.print("\\u" + String.valueOf(Integer.toHexString(c)));
//        }
//        System.out.println();
    }
}