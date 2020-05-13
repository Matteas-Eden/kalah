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
     * 3. Player 1 goes first
     * 4. Each player takes a turn, receiving input
     * 5. Once a player has no non-empty houses, the game ends
     * 6. If a player inputs 'q' for quit, the game ends
     * 7. When the game ends, all the seeds from a player's house go into their store
     */
    public void play(IO io) {

        Player player1 = new Player(GameConfig.NUM_HOUSES);
        Player player2 = new Player(GameConfig.NUM_HOUSES);

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        Board board = new Board(players);

        int playerNum = GameConfig.STARTING_PLAYER - 1;
        boolean earlyFinish = false;
        int selection;

        GameIO.printBoard(board, io);

        while (!players.get(playerNum).hasOnlyEmptyHouses()) {

            selection = GameIO.getMove(board, playerNum, io);

            switch (selection) {
                case Macros.QUIT_GAME:
                    earlyFinish = true;
                    break;
                case Macros.BAD_SELECTION:
                    break;
                default:
                    if (!board.makeMove(playerNum, selection))
                        playerNum = (playerNum < GameConfig.NUM_PLAYERS - 1) ? playerNum + 1 : 0;
            }

            if (earlyFinish)
                break;

            GameIO.printBoard(board, io);
        }

        io.println("Game over");
        GameIO.printBoard(board, io);

        if (!earlyFinish) {
            board.cleanup();
            GameIO.printGameResult(players, io);
        }

    }
}
