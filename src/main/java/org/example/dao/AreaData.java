package org.example.dao;

import org.example.domains.Cell;

public class AreaData {


    private static AreaData INSTANCE;
    private volatile Cell[][] area;

    private AreaData() {
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