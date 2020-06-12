package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<House> houses;
    private final Store store;

    public Player(int numHouses) {
        this.houses = new ArrayList<>();
        for (int i = 0; i < numHouses; i++) {
            this.houses.add(new House(GameConfig.STARTING_SEEDS));
        }
        this.store = new Store();
    }

    public List<House> getHouses() {
        return houses;
    }

    public Store getStore() {
        return store;
    }

    public boolean hasOnlyEmptyHouses() {
        for (House house : houses) {
            if (!house.isEmpty()) return false;
        }
        return true;
    }

}
