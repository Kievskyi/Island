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

/*
TODO - our objects of animals and etc. will be in separate ArrayList<Animal>. This lists might be placed in single class which will be initialized before start a multithreading.
TODO - when will generating a field need to place all animals and plants in a different cells. Otherwise we must write a logic with eat and reproduce in generate method .

 */
