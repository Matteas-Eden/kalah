package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Player> players;
    private List<Pit> pits;

    public Board(final List<Player> players) {
        this.players = players;
        this.pits = new ArrayList<>();

        for (Player player : players) {
            pits.addAll(player.getHouses());
            pits.add(player.getStore());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean makeMove(int playerNum, int houseNum) {
        /* When a player selects a house;
        * The seeds in that house get distributed one by one
        * through each player's house and store in order
        * until there are no more seeds to distribute
        *
        * If the last seed lands in the player's store, that
        * player gets another turn.
        * */
        // Find the pit and retrieve number of seeds in it
        int pitsIndex = playerNum * (GameConfig.NUM_HOUSES + 1) + houseNum - 1;
        int seeds = pits.get(pitsIndex).getSeeds();

        // Clear the pit
        pits.get(pitsIndex).clearSeeds();
        pitsIndex++;

        // Distribute seeds through all other pits until none are left
        while (seeds > 0) {

            // Don't put seeds in opponent's store
            if (pitsIndex == (playerNum == 1 ? 1 : 2) * (GameConfig.NUM_HOUSES + 1) - 1) {
                pitsIndex = (pitsIndex < pits.size() - 1) ? pitsIndex + 1 : 0;
                continue;
            }

            Pit pit = pits.get(pitsIndex);
            pit.incrementSeeds();
            seeds--;
            pitsIndex = (pitsIndex < pits.size() - 1) ? pitsIndex + 1 : 0;
        }

        return pitsIndex == 0 || pitsIndex == 7;

    }

    public void cleanup() {
        // Needs to empty out all the houses and put remaining seeds in player stores
        for (Player player : players) {
            Pit store = player.getStore();
            for (Pit house : player.getHouses()) {
                store.incrementSeeds(house.getSeeds());
                house.clearSeeds();
            }
        }
    }
}
