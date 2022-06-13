package org.example.domains;

import java.util.Arrays;

public class Map {
    int[][] size;

    public Map generateMap() {

        return new Map();
    }

    @Override
    public String toString() {
        return "Map{" +
                "size=" + Arrays.toString(size) +
                '}';
    }
}
