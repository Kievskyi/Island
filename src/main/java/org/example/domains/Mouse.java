package org.example.domains;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.AnimalKind;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Mouse extends Herbivore {

    private double satiety;
    private int max_amount_in_cell = 500;
    private int leftAlive;
    private Map<AnimalKind, Integer> chances_to_kill;

    {
        chances_to_kill = new HashMap<>() {{
            put(AnimalKind.CATERPILLAR, 90);
        }};

        satiety = ThreadLocalRandom.current().nextDouble(0.002, 0.006);
        leftAlive = ThreadLocalRandom.current().nextInt(200, max_amount_in_cell + 1);
    }

    public Mouse() {
        super.setSatiety(satiety);
        super.setMax_amount_in_cell(max_amount_in_cell);
        super.setLeftAlive(leftAlive);
        super.setChances_to_kill(chances_to_kill);
    }
}