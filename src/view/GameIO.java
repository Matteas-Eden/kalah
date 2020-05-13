package view;

import com.qualitascorpus.testsupport.IO;
import model.*;

import java.util.List;

public class GameIO {

    public static int getMove(Board board, int playerNum, IO io) {

        io.print(String.format("Player P%d's turn - Specify house number or 'q' to quit: ", playerNum + 1));
        String input = io.readFromKeyboard("");

        if (input.equals("q")) {
            return Macros.QUIT_GAME;
        }

        int selection = Integer.parseInt(input);

        // Need to ensure the player selects a non-empty house
        if (board.getPlayers().get(playerNum).getHouses().get(selection - 1).getSeeds() == 0) {
            io.println("House is empty. Move again.");
            selection = Macros.BAD_SELECTION;
        }

        return selection;

    }

    public static void printBoard(Board board, IO io) {

        List<Player> players = board.getPlayers();

        String dividerLine = createDividerLine(GameConfig.NUM_HOUSES, false);
        String dividerLineGaps = createDividerLine(GameConfig.NUM_HOUSES, true);

        String p1Store = String.format(" %2d |", players.get(0).getStore().getSeeds());
        String p2Store = String.format("| %2d ", players.get(1).getStore().getSeeds());

        io.println(dividerLine);
        io.println("| P2 " + formatHousesAsString(players.get(1).getHouses(), true) + p1Store);
        io.println(dividerLineGaps);
        io.println(p2Store + formatHousesAsString(players.get(0).getHouses(), false) + " P1 |");
        io.println(dividerLine);

    }

    /*
    * Construct the divider line to match the number of houses
    * */
    private static String createDividerLine(int numSections, boolean gaps) {
        StringBuilder divider = new StringBuilder((gaps) ? "|    |" : "+----+");
        for (int i = 0; i < numSections - 1; i++) {
            divider.append("-------+");
        }
        divider.append("-------");
        divider.append((gaps) ? "|    |" : "+----+");
        return divider.toString();
    }

    public static void printGameResult(List<Player> players, IO io, Board board, boolean gameQuit) {

        io.println("Game over");
        GameIO.printBoard(board, io);

        if (gameQuit) return;

        board.cleanup();

        Player winner = players.get(0);

        for (Player player : players) {
            io.println(String.format("\tplayer %d:%d", players.indexOf(player) + 1, player.getStore().getSeeds()));
            if (player.getStore().getSeeds() > winner.getStore().getSeeds()) winner = player;
        }

        if (players.get(0).getStore().getSeeds() == players.get(1).getStore().getSeeds()) io.println("A tie!");
        else io.println(String.format("Player %d wins!", players.indexOf(winner) + 1));
    }

    /*
     * Constructing a string for the houses for printing the board
     * Needs to print out one player from left to right, and the other player from right to left
     */
    public static String formatHousesAsString(List<Pit> houses, boolean isPlayerTwo) {
        StringBuilder out = new StringBuilder("|");
        if (!isPlayerTwo) {
            for (Pit house : houses) {
                out.append(String.format(" %d[%2d] |", houses.indexOf(house) + 1,
                        house.getSeeds()));
            }
        } else {
            for (int i = houses.size(); i > 0; i--) {
                out.append(String.format(" %d[%2d] |", i,
                        houses.get(i - 1).getSeeds()));
            }
        }
        return out.toString();
    }

}
