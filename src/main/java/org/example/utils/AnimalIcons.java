package org.example.utils;

public enum AnimalIcons {

    EATPLANT("\uD83C\uDF75"),
    FEWPREDATORS("\uD83D\uDC3E"),
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

    AnimalIcons(String icon) {
        this.icon = icon;
    }

    private String icon;

    public String getIcon() {
        return icon;
    }
}