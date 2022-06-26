package org.example.dao;

import org.example.domains.Cell;

public class AreaData {


    private static AreaData INSTANCE;
    private volatile Cell[][] area;

    private AreaData() {
    }

    public void generateArea() {

        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                area[i][j] = new Cell();
            }
        }
    }

    public static AreaData getInstance() {
        if(INSTANCE == null) {
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