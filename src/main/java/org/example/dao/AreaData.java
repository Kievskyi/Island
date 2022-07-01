package org.example.dao;

import lombok.Getter;
import lombok.Setter;
import org.example.domains.Cell;

@Setter
@Getter
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
        if (INSTANCE == null) {
            INSTANCE = new AreaData();
        }
        return INSTANCE;
    }
}