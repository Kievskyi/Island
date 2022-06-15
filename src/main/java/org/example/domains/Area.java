package org.example.domains;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.utils.AnimalIcons;

import java.util.Arrays;

@Builder
@Getter
@Setter
public class Area {
    private int[][] fieldSize;

    public Area(int[][] size) {
        this.fieldSize = size;
    }

    public void generateField() {

        for (int i = 0; i < fieldSize.length; i++) {
            for (int j = 0; j < fieldSize[i].length; j++) {
                System.out.print(AnimalIcons.GROUND.getIcon());
            }
            System.out.println();
        }
    }
}