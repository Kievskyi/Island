package org.example.domains;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.AnimalKind;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Bear extends Predator {

    private double satiety;
    private int max_amount_in_cell = 5;
    private int leftAlive;
    private Map<AnimalKind, Integer> chances_to_kill;

    {
        chances_to_kill = new HashMap<>() {{
            put(AnimalKind.SNAKE, 80);
            put(AnimalKind.HORSE, 40);
            put(AnimalKind.DEER, 80);
            put(AnimalKind.RABBIT, 80);
            put(AnimalKind.MOUSE, 90);
            put(AnimalKind.GOAT, 70);
            put(AnimalKind.SHEEP, 70);
            put(AnimalKind.BOAR, 50);
            put(AnimalKind.BUFFALO, 20);
            put(AnimalKind.DUCK, 10);
        }};

        satiety = ThreadLocalRandom.current().nextDouble(20, 70);
        leftAlive = ThreadLocalRandom.current().nextInt(1, max_amount_in_cell + 1);
    }

    public Bear() {
        super.setSatiety(satiety);
        super.setMax_amount_in_cell(max_amount_in_cell);
        super.setLeftAlive(leftAlive);
        super.setChances_to_kill(chances_to_kill);
    }
}