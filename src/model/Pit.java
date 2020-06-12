package model;

public abstract class Pit {

    protected int seeds;

    public Pit(int seeds) {
        this.seeds = seeds;
    }

    public int getSeeds() {
        return seeds;
    }

    public void incrementSeeds(int amount) {
        this.seeds += amount;
    }

    public boolean isEmpty() {
        return seeds == 0;
    }
}
