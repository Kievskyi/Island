package org.example.domains;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.AnimalKind;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Horse extends Herbivore {

    private double satiety;
    private int max_amount_in_cell = 20;
    private int leftAlive;
    private Map<AnimalKind, Integer> chances_to_kill;

    {
        chances_to_kill = new HashMap<>() {{
            put(AnimalKind.SNAKE, 80);
        }};
        satiety = ThreadLocalRandom.current().nextDouble(20, 50);
        leftAlive = ThreadLocalRandom.current().nextInt(5 , max_amount_in_cell + 1);
    }

    public Horse() {
        super.setSatiety(satiety);
        super.setMax_amount_in_cell(max_amount_in_cell);
        super.setLeftAlive(leftAlive);
        super.setChances_to_kill(chances_to_kill);
    }
}