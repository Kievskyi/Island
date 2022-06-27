package org.example.domains;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.AnimalKind;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Duck extends Herbivore {

    private double satiety;
    private int max_amount_in_cell = 200;
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
            put(AnimalKind.BEAR, 10);
            put(AnimalKind.CATERPILLAR, 90);
        }};
        satiety = ThreadLocalRandom.current().nextDouble(0.3, 0.9);
        leftAlive = ThreadLocalRandom.current().nextInt(40, max_amount_in_cell + 1);
    }

    public Duck() {
        super.setSatiety(satiety);
        super.setMax_amount_in_cell(max_amount_in_cell);
        super.setLeftAlive(leftAlive);
        super.setChances_to_kill(chances_to_kill);
    }
}