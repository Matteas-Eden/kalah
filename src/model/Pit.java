package model;

public class Pit {

    private int seeds;

    public Pit(int seeds) {
        this.seeds = seeds;
    }

    public int getSeeds() {
        return seeds;
    }

    public void clearSeeds() {
        this.seeds = 0;
    }

    public void incrementSeeds() {
        this.seeds++;
    }

    public void incrementSeeds(int amount) {
        this.seeds += amount;
    }

    public boolean isEmpty() {
        return seeds == 0;
    }
}
