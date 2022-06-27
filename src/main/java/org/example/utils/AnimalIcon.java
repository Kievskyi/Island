package org.example.utils;

public enum AnimalIcon {

    PLATE("\uD83C\uDF75"),
    PAWS("\uD83D\uDC3E"),
    BATTLE("\u2694"),
    GROUND("\ud83d\udfeb"),
    BEAR("\uD83D\uDC3B"),
    BOAR("\uD83D\uDC17"),
    BUFFALO("\uD83D\uDC03"),
    CATERPILLAR("\uD83D\uDC1B"),
    DEER("\ud83e\udd8c"),
    DUCK("\ud83e\udd86"),
    EAGLE("\ud83e\udd85"),
    FOX("\ud83e\udd8a"),
    GOAT("\uD83D\uDC10"),
    HORSE("\uD83D\uDC34"),
    MOUSE("\uD83D\uDC2D"),
    PLANT("\uD83C\uDF40"),
    RABBIT("\uD83D\uDC30"),
    SHEEP("\uD83D\uDC11"),
    SNAKE("\uD83D\uDC0D"),
    WOLF("\uD83D\uDC3A");

    AnimalIcon(String icon) {
        this.icon = icon;
    }

    private String icon;

    public String getIcon() {
        return icon;
    }

    public static String valueOf(int value) {
        String animalIcon = null;

        switch (value) {
            case 0:
                animalIcon = BEAR.getIcon();
                break;
            case 1:
                animalIcon = BOAR.getIcon();
                break;
            case 2:
                animalIcon = BUFFALO.getIcon();
                break;
            case 3:
                animalIcon = CATERPILLAR.getIcon();
                break;
            case 4:
                animalIcon = DEER.getIcon();
                break;
            case 5:
                animalIcon = DUCK.getIcon();
                break;
            case 6:
                animalIcon = EAGLE.getIcon();
                break;
            case 7:
                animalIcon = FOX.getIcon();
                break;
            case 8:
                animalIcon = GOAT.getIcon();
                break;
            case 9:
                animalIcon = HORSE.getIcon();
                break;
            case 10:
                animalIcon = MOUSE.getIcon();
                break;
            case 11:
                animalIcon = RABBIT.getIcon();
                break;
            case 12:
                animalIcon = SHEEP.getIcon();
                break;
            case 13:
                animalIcon = SNAKE.getIcon();
                break;
            case 14:
                animalIcon = WOLF.getIcon();
                break;
        }
        return animalIcon;
    }
}