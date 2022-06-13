package org.example.domains;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Builder
@Getter
@Setter
public class Field {
    private int[][] fieldSize;

    public Field(int length, int width) {
        this.fieldSize = new int[width][length];
    }

    public Field generateField() {

        return null;
    }

    @Override
    public String toString() {
        return "Map{" +
                "size=" + Arrays.toString(fieldSize) +
                '}';
    }
}