package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Pit> houses;
    private Pit store;

    public Player(int numHouses) {
        this.houses = new ArrayList<>();
        for (int i = 0; i < numHouses; i++) {
            this.houses.add(new Pit(GameConfig.STARTING_SEEDS));
        }
        this.store = new Pit(0);
    }

    public List<Pit> getHouses() {
        return houses;
    }

    public Pit getStore() {
        return store;
    }

    public boolean hasOnlyEmptyHouses() {
        for (Pit house : houses) {
            if (house.getSeeds() != 0) return false;
        }
        return true;
    }

}
