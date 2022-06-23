package org.example.utils;

public enum SystemIcons {
    BLUE("\ud83d\udfe6"),
    YELLOW("\ud83d\udfe8"),
    BLACK("\u2b1b");

    SystemIcons(String icon) {
        this.icon = icon;
    }

    private String icon;

    public String getIcon() {
        return icon;
    }
}
