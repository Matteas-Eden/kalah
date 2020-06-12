package model;

public class House extends Pit {

    public House(int seeds) {
        super(seeds);
    }

    public void clearSeeds() {
        this.seeds = 0;
    }
}
