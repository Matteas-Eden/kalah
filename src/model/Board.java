package model;

import java.util.List;

public class Board {
    private List<Player> players;

    public Board(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
