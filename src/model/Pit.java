package model;

public class Pit {

    private int seeds;

    public Pit(int seeds) {
        this.seeds = seeds;
    }

    public int getSeeds() {
        return seeds;
    }

    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }

    public boolean isEmpty() {
        return seeds == 0;
    }
}
