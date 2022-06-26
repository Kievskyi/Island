package org.example.utils;

import lombok.Getter;
import lombok.Setter;
import org.example.dao.AnimalData;
import org.example.dao.AreaData;
import org.example.domains.Animal;
import org.example.domains.AnimalFactory;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Statistic implements Runnable {

    private static Statistic INSTANCE;
    private AnimalData animalData;
    private AreaData areaData = AreaData.getInstance();
    private Map<AnimalKind, Integer> mapOfDiedAnimals;
    private Map<AnimalKind, Integer> mapOfKills;

    {
        mapOfDiedAnimals = new HashMap<>() {{
            put(AnimalKind.BEAR, 0);
            put(AnimalKind.BOAR, 0);
            put(AnimalKind.BUFFALO, 0);
            put(AnimalKind.CATERPILLAR, 0);
            put(AnimalKind.DEER, 0);
            put(AnimalKind.DUCK, 0);
            put(AnimalKind.EAGLE, 0);
            put(AnimalKind.FOX, 0);
            put(AnimalKind.GOAT, 0);
            put(AnimalKind.HORSE, 0);
            put(AnimalKind.MOUSE, 0);
            put(AnimalKind.RABBIT, 0);
            put(AnimalKind.SHEEP, 0);
            put(AnimalKind.SNAKE, 0);
            put(AnimalKind.WOLF, 0);
        }};

        mapOfKills = new HashMap<>() {{
            put(AnimalKind.BEAR, 0);
            put(AnimalKind.BOAR, 0);
            put(AnimalKind.BUFFALO, 0);
            put(AnimalKind.CATERPILLAR, 0);
            put(AnimalKind.DEER, 0);
            put(AnimalKind.DUCK, 0);
            put(AnimalKind.EAGLE, 0);
            put(AnimalKind.FOX, 0);
            put(AnimalKind.GOAT, 0);
            put(AnimalKind.HORSE, 0);
            put(AnimalKind.MOUSE, 0);
            put(AnimalKind.RABBIT, 0);
            put(AnimalKind.SHEEP, 0);
            put(AnimalKind.SNAKE, 0);
            put(AnimalKind.WOLF, 0);
        }};
    }

    private Statistic() {
    }

    @Override
    public void run() {
        showStatistic();
    }

    public void showStatistic() {
        System.out.printf("%-27s %5s %5s %5s %5s  %5s %5s %5s  %5s %5s %5s %5s %5s  %5s %5s %5s", Color.BG_BLACK.getColor() + " Animals :" + Color.RESET.getColor()
                , AnimalIcon.valueOf(0), AnimalIcon.valueOf(1), AnimalIcon.valueOf(2), AnimalIcon.valueOf(3), AnimalIcon.valueOf(4),
                AnimalIcon.valueOf(5), AnimalIcon.valueOf(6), AnimalIcon.valueOf(7), AnimalIcon.valueOf(8), AnimalIcon.valueOf(9),
                AnimalIcon.valueOf(10), AnimalIcon.valueOf(11), AnimalIcon.valueOf(12), AnimalIcon.valueOf(13), AnimalIcon.valueOf(14));

        System.out.println();

        System.out.printf("%-30s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s", Color.BG_BLACK.getColor() + " Left alive flocks :" + Color.RESET.getColor(),
                getLeftAliveFlocks(AnimalKind.BEAR), getLeftAliveFlocks(AnimalKind.BOAR), getLeftAliveFlocks(AnimalKind.BUFFALO),
                getLeftAliveFlocks(AnimalKind.CATERPILLAR), getLeftAliveFlocks(AnimalKind.DEER), getLeftAliveFlocks(AnimalKind.DUCK),
                getLeftAliveFlocks(AnimalKind.EAGLE), getLeftAliveFlocks(AnimalKind.FOX), getLeftAliveFlocks(AnimalKind.GOAT),
                getLeftAliveFlocks(AnimalKind.HORSE), getLeftAliveFlocks(AnimalKind.MOUSE), getLeftAliveFlocks(AnimalKind.RABBIT),
                getLeftAliveFlocks(AnimalKind.SHEEP), getLeftAliveFlocks(AnimalKind.SNAKE), getLeftAliveFlocks(AnimalKind.WOLF));

        System.out.println();

        System.out.printf("%-30s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s", Color.BG_BLACK.getColor() + " Left alive amount :" + Color.RESET.getColor(),
                getLeftAliveAmount(AnimalKind.BEAR), getLeftAliveAmount(AnimalKind.BOAR), getLeftAliveAmount(AnimalKind.BUFFALO),
                getLeftAliveAmount(AnimalKind.CATERPILLAR), getLeftAliveAmount(AnimalKind.DEER), getLeftAliveAmount(AnimalKind.DUCK),
                getLeftAliveAmount(AnimalKind.EAGLE), getLeftAliveAmount(AnimalKind.FOX), getLeftAliveAmount(AnimalKind.GOAT),
                getLeftAliveAmount(AnimalKind.HORSE), getLeftAliveAmount(AnimalKind.MOUSE), getLeftAliveAmount(AnimalKind.RABBIT),
                getLeftAliveAmount(AnimalKind.SHEEP), getLeftAliveAmount(AnimalKind.SNAKE), getLeftAliveAmount(AnimalKind.WOLF));

        System.out.println();

        System.out.printf("%-30s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s", Color.BG_BLACK.getColor() + " Kills :" + Color.RESET.getColor(),
                getKills(AnimalKind.BEAR), getKills(AnimalKind.BOAR), getKills(AnimalKind.BUFFALO),
                getKills(AnimalKind.CATERPILLAR), getKills(AnimalKind.DEER), getKills(AnimalKind.DUCK),
                getKills(AnimalKind.EAGLE), getKills(AnimalKind.FOX), getKills(AnimalKind.GOAT),
                getKills(AnimalKind.HORSE), getKills(AnimalKind.MOUSE), getKills(AnimalKind.RABBIT),
                getKills(AnimalKind.SHEEP), getKills(AnimalKind.SNAKE), getKills(AnimalKind.WOLF));

        System.out.println();

        System.out.printf("%-30s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s", Color.BG_BLACK.getColor() + " Died :" + Color.RESET.getColor(),
                getNumberOfDeaths(AnimalKind.BEAR), getNumberOfDeaths(AnimalKind.BOAR), getNumberOfDeaths(AnimalKind.BUFFALO),
                getNumberOfDeaths(AnimalKind.CATERPILLAR), getNumberOfDeaths(AnimalKind.DEER), getNumberOfDeaths(AnimalKind.DUCK),
                getNumberOfDeaths(AnimalKind.EAGLE), getNumberOfDeaths(AnimalKind.FOX), getNumberOfDeaths(AnimalKind.GOAT),
                getNumberOfDeaths(AnimalKind.HORSE), getNumberOfDeaths(AnimalKind.MOUSE), getNumberOfDeaths(AnimalKind.RABBIT),
                getNumberOfDeaths(AnimalKind.SHEEP), getNumberOfDeaths(AnimalKind.SNAKE), getNumberOfDeaths(AnimalKind.WOLF));

        System.out.println();
        System.out.println();

//        System.out.printf("%-10s %-20s %-20s %-8s %s", "Animal", "Left alive flocks", "Left alive amount", "Kills", "Died");
//        for (int j = 0; j < AnimalKind.values().length; j++) {
//            String icon = AnimalIcon.valueOf(j);
//            int flocks = getLeftAliveFlocks(AnimalKind.valueOf(j));
//            int amount = getLeftAliveAmount(AnimalKind.valueOf(j));
//            int kills = getKills(AnimalKind.valueOf(j));
//            int died = getNumberOfDeaths(AnimalKind.valueOf(j));
//            System.out.println();
//            System.out.printf("%-10s %-20d %-20d %-8d %d", icon, flocks, amount, kills, died);
//        }
//        System.out.println();
//        System.out.println();
    }

    private int getLeftAliveFlocks(AnimalKind animalKind) {
        int aliveFlocks = 0;
        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                    if (animalKind.equals(areaData.getArea()[i][j].getAnimals_in_cell().get(k).getAnimalKind())) {
                        aliveFlocks += 1;
                    }
                }
            }
        }
        return aliveFlocks;
    }

    private int getLeftAliveAmount(AnimalKind animalKind) {
        int aliveAmount = 0;
        for (int i = 0; i < areaData.getArea().length; i++) {
            for (int j = 0; j < areaData.getArea()[i].length; j++) {
                for (int k = 0; k < areaData.getArea()[i][j].getAnimals_in_cell().size(); k++) {
                    if (animalKind.equals(areaData.getArea()[i][j].getAnimals_in_cell().get(k).getAnimalKind())) {
                        aliveAmount += areaData.getArea()[i][j].getAnimals_in_cell().get(k).getLeftAlive();
                    }
                }
            }
        }
        return aliveAmount;
    }

    private int getKills(AnimalKind animalKind) {
        int kills = 0;

        for (Map.Entry<AnimalKind, Integer> map : mapOfKills.entrySet()) {
            if (map.getKey().equals(animalKind)) {
                kills = map.getValue();
            }
        }
        return kills;
    }

    private int getNumberOfDeaths(AnimalKind animalKind) {
        int died = 0;

        for (Map.Entry<AnimalKind, Integer> map : mapOfDiedAnimals.entrySet()) {
            if (map.getKey().equals(animalKind)) {
                died = map.getValue();
            }
        }
        return died;
    }

    public static Statistic getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Statistic();
        }
        return INSTANCE;
    }
}