package org.example.utils;

public enum SystemIcon {
    BLUE_SQUARE("\ud83d\udfe6"),
    YELLOW_SQUARE("\ud83d\udfe8"),
    BLACK_SQUARE("\u2b1b");

    SystemIcon(String icon) {
        this.icon = icon;
    }

    private String icon;

    public String getIcon() {
        return icon;
    }
}