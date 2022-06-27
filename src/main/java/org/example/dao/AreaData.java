package org.example.dao;

import org.example.domains.Cell;

public class AreaData {

    private static AreaData INSTANCE;
    private volatile Cell[][] area;

    private AreaData() {
    }

    public void generateArea() {

        for (int width = 0; width < area.length; width++) {
            for (int length = 0; length < area[width].length; length++) {
                area[width][length] = new Cell();
            }
        }
    }

    public static AreaData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AreaData();
        }
        return INSTANCE;
    }

    public Cell[][] getArea() {
        return area;
    }

    public void setArea(Cell[][] area) {
        this.area = area;
    }
}