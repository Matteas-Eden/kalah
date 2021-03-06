package model;

public class GameConfig {
    public static final int STARTING_SEEDS = 4;
    public static final int SEED_LOSS_PER_PIT = 1; // Should never exceed STARTING_SEEDS
    public static final int NUM_HOUSES = 6;
    public static final int NUM_PLAYERS = 2;
    public static final int STARTING_PLAYER = 1; // Should never exceed NUM_PLAYERS
    public static final boolean VERTICAL_OUTPUT = false;
    public static final int HUMAN_PLAYER = 0;
    public static final int ROBOT_PLAYER = 1;
}
