package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import model.Board;
import model.GameConfig;
import model.Macros;
import model.Player;
import view.GameIO;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {
    public static void main(String[] args) {
        new Kalah().play(new MockIO());
    }

    /**
     * 1. Create players for game
     * 2. Initialise board with seeds in houses
     * 3. Starting player makes the first move
     * 4. Each player takes a turn and provides input
     * 5. On a player's turn, if they only have empty houses, the game ends
     * 6. If a player inputs 'q' for quit, the game ends
     * 7. When the game ends, all the seeds from players' houses go into their store
     */
    public void play(IO io) {

        List<Player> players = setupPlayers(2);

        Board board = new Board(players);

        int playerNum = GameConfig.STARTING_PLAYER - 1;
        boolean gameQuit = false;
        int selection;

        GameIO.printBoard(board, io, true);

        while (!players.get(playerNum).hasOnlyEmptyHouses()) {

            selection = GameIO.getMove(board, playerNum, io);

            switch (selection) {
                case Macros.QUIT_GAME:
                    gameQuit = true;
                    break;
                case Macros.BAD_SELECTION:
                    break;
                default:
                    if (!board.makeMove(playerNum, selection))
                        playerNum = (playerNum < GameConfig.NUM_PLAYERS - 1) ? playerNum + 1 : 0;
            }

            if (gameQuit)
                break;

            GameIO.printBoard(board, io, true);

        }

        GameIO.printGameResult(players, io, board, gameQuit);

    }

    private static List<Player> setupPlayers(int numPlayers) {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(GameConfig.NUM_HOUSES));
        }

        return players;
    }
}
