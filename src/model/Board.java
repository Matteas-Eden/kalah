package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Player> players;
    private final List<Pit> pits;

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
        boolean bonusTurn = false;

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

            if (seeds == GameConfig.SEED_LOSS_PER_PIT) {

                seeds -= GameConfig.SEED_LOSS_PER_PIT;

                // Check if the last seed falls in a store
                if (pitsIndex == GameConfig.NUM_HOUSES | pitsIndex == GameConfig.NUM_HOUSES * 2 + 1) {
                    bonusTurn = true;
                    pit.incrementSeeds(GameConfig.SEED_LOSS_PER_PIT);
                    continue;
                }

                // Don't attempt capture on pits not owned by player
                if (!(pitsIndex >= playerNum * (GameConfig.NUM_HOUSES + 1) &&
                        pitsIndex < playerNum * (GameConfig.NUM_HOUSES + 1) + GameConfig.NUM_HOUSES)){
                    pit.incrementSeeds(GameConfig.SEED_LOSS_PER_PIT);
                    continue;
                }

                // Evaluate if a capture takes place (really need to ensure that captures only happen for a player's
                // own pit)
                Pit oppositePit = pits.get(pits.size() - 2 - pitsIndex);
                if (pit.getSeeds() == 0 && oppositePit.getSeeds() != 0) {
                    pits.get(playerNum == 0 ? GameConfig.NUM_HOUSES : GameConfig.NUM_HOUSES * 2 + 1).incrementSeeds(oppositePit.getSeeds() + GameConfig.SEED_LOSS_PER_PIT);
                    oppositePit.clearSeeds();
                }
                else pit.incrementSeeds(GameConfig.SEED_LOSS_PER_PIT);

                continue;
            }

            pit.incrementSeeds(GameConfig.SEED_LOSS_PER_PIT);
            seeds -= GameConfig.SEED_LOSS_PER_PIT;
            pitsIndex = (pitsIndex < pits.size() - 1) ? pitsIndex + 1 : 0;

        }

        return bonusTurn;

    }

    public boolean hasPlayerWithAllEmptyHouses() {
        for (Player player : players) {
            if (player.hasOnlyEmptyHouses())
                    return true;
        }
        return false;
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
