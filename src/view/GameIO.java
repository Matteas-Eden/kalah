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

    public static void printBoard(Board board, IO io) {

        List<Player> players = board.getPlayers();

        io.println("+----+-------+-------+-------+-------+-------+-------+----+");

//        ListIterator<Player> itr = players.listIterator(players.size());
//        int playerCount = players.size();
//
//        while (itr.hasPrevious()) {
//
//            io.println("|    |-------+-------+-------+-------+-------+-------|    |");
//        }

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
        StringBuilder out = new StringBuilder("| ");
        for (Pit house : houses) {
            out.append(String.format("%d[ %d] |", reverse ? houses.size() - houses.indexOf(house):houses.indexOf(house),
                    house.getSeeds()));
        }
        return out.toString();
    }

}
