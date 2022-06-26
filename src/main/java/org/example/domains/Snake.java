package org.example.domains;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.AnimalKind;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Snake extends Predator {

    private double satiety;
    private int max_amount_in_cell = 30;
    private int leftAlive;
    private Map<AnimalKind, Integer> chances_to_kill;

    {
        chances_to_kill = new HashMap<>() {{
            put(AnimalKind.FOX, 15);
            put(AnimalKind.RABBIT, 20);
            put(AnimalKind.MOUSE, 40);
            put(AnimalKind.DUCK, 10);
        }};

        satiety = ThreadLocalRandom.current().nextDouble(1, 2.3);
        leftAlive = ThreadLocalRandom.current().nextInt(13 , max_amount_in_cell + 1);
    }

    public Snake() {
        super.setSatiety(satiety);
        super.setMax_amount_in_cell(max_amount_in_cell);
        super.setLeftAlive(leftAlive);
        super.setChances_to_kill(chances_to_kill);
    }
}