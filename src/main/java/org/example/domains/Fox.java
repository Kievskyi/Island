package org.example.domains;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.AnimalKind;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Fox extends Predator {

    private double satiety;
    private int max_amount_in_cell = 30;
    private int leftAlive;
    private Map<AnimalKind, Integer> chances_to_kill;

    {
        chances_to_kill = new HashMap<>() {{
            put(AnimalKind.RABBIT, 70);
            put(AnimalKind.MOUSE, 90);
            put(AnimalKind.DUCK, 60);
            put(AnimalKind.CATERPILLAR, 40);
        }};

        satiety = ThreadLocalRandom.current().nextDouble(0.8, 1.5);
        leftAlive = ThreadLocalRandom.current().nextInt(10, max_amount_in_cell + 1);
    }

    public Fox() {
        super.setSatiety(satiety);
        super.setMax_amount_in_cell(max_amount_in_cell);
        super.setLeftAlive(leftAlive);
        super.setChances_to_kill(chances_to_kill);
    }
}