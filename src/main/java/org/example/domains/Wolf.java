package org.example.domains;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.AnimalKind;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Wolf extends Predator {

    private double satiety;
    private int max_amount_in_cell = 30;
    private int leftAlive;

    private Map<AnimalKind, Integer> chances_to_kill;

    {
        chances_to_kill = new HashMap<>() {{
            put(AnimalKind.HORSE, 10);
            put(AnimalKind.DEER, 15);
            put(AnimalKind.RABBIT, 60);
            put(AnimalKind.MOUSE, 80);
            put(AnimalKind.GOAT, 60);
            put(AnimalKind.SHEEP, 70);
            put(AnimalKind.BOAR, 15);
            put(AnimalKind.BUFFALO, 10);
            put(AnimalKind.DUCK, 40);
        }};

        satiety = ThreadLocalRandom.current().nextDouble(3, 6);
        leftAlive = ThreadLocalRandom.current().nextInt(13, max_amount_in_cell + 1);
    }

    public Wolf() {
        super.setSatiety(satiety);
        super.setMax_amount_in_cell(max_amount_in_cell);
        super.setLeftAlive(leftAlive);
        super.setChances_to_kill(chances_to_kill);
    }
}