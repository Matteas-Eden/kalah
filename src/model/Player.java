package model;

import java.util.List;

public class Player {
    private List<Pit> houses;
    private Pit store;

    public Player(List<Pit> houses, Pit store) {
        this.houses = houses;
        this.store = store;
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
