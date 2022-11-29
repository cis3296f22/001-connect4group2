import java.awt.*;
import java.util.Random;

// dump AI, randomly drop tokens

// problem, may not work because grid is passed as an instance

/**
 * AI_easy implements the AI class because it is an AI type
 * this has the code for the easy version of the AI
 */

public class AI_easy implements AI{

    /**
     * chooses grid randomly
     * @param turn number of turns
     * @param numPlayers player num
     * @param cols column num
     * @param rows row num
     * @param players player array
     * @param grid the board
     * @return turns
     */
    public int generateAIMove(int turn, int numPlayers, int cols, int rows, Player[] players, Color[][] grid) {
        int ySpot = 0;
        Random rand = new Random();
        int xSpot = rand.nextInt(cols);
        while ((grid[ySpot][xSpot] == Color.WHITE) && (ySpot < rows - 1)) {
            ySpot++;
        }
        if (grid[ySpot][xSpot] == Color.WHITE) {
            grid[ySpot][xSpot] = players[turn % numPlayers].getToken();
        } else if (ySpot - 1 >= 0 && grid[ySpot - 1][xSpot] == Color.WHITE) {
            grid[ySpot - 1][xSpot] = players[turn % numPlayers].getToken();
        } else {
            turn--;
        }
        return turn;
    }

    @Override
    public void setColor(Color c) {

    }
}
