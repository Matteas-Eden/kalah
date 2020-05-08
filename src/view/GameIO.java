package view;

import com.qualitascorpus.testsupport.IO;
import model.Board;
import model.Pit;
import model.Player;

import java.util.List;
import java.util.ListIterator;

public class GameIO {

    public static int getMove(Board board, int playerNum, IO io) {
        io.println(String.format("Player %d's turn - Specify house number or 'q' to quit: ", playerNum+1));
        String input = io.readFromKeyboard("");
        return input.equals("q") ? -1:Integer.parseInt(input);
    }

    // TODO: Refactor HEAVILY
    //  Specifically need to ensure the output scales for N houses
    public static void printBoard(Board board, IO io) {

        List<Player> players = board.getPlayers();

        String p1Store = (players.get(0).getStore().getSeeds() > 9) ?
                String.format(" %d |", players.get(0).getStore().getSeeds()) :
                String.format("  %d |", players.get(0).getStore().getSeeds());

        String p2Store = (players.get(1).getStore().getSeeds() > 9) ?
                String.format("| %d ", players.get(1).getStore().getSeeds()) :
                String.format("|  %d ", players.get(1).getStore().getSeeds());

        io.println("+----+-------+-------+-------+-------+-------+-------+----+");

        io.println("| P2 " + formatHousesAsString(players.get(1).getHouses(), true) + p1Store);
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println(p2Store + formatHousesAsString(players.get(0).getHouses(), false) + " P1 |");

        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    public static void printGameResult(List<Player> players, IO io) {
        Player winner = players.get(0);
        for (Player player : players) {
            io.println(String.format("\tplayer %d:%d", players.indexOf(player) + 1, player.getStore().getSeeds()));
            if (player.getStore().getSeeds() > winner.getStore().getSeeds()) winner = player;
        }
        io.println(String.format("Player %d wins!", players.indexOf(winner)));
    }

    public static String formatHousesAsString(List<Pit> houses, boolean reverse) {
        StringBuilder out = new StringBuilder("|");
        for (Pit house : houses) {
            out.append(String.format(" %d[ %d] |", reverse ? houses.size() - houses.indexOf(house):houses.indexOf(house) + 1,
                    house.getSeeds()));
        }
        return out.toString();
    }

}
