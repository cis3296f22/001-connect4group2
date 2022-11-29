import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


/**
 * AI_hard implements the AI class because it is an AI type.
 * This is the hard version of the AI
 */

public class AI_hard implements AI{

    private Color c;

    /**
     * generateAIMove creates the AI move by checking for many
     * possibilities to make it harder for the player
     * @param turn number of turns
     * @param numPlayers number of players
     * @param cols number of cols of grid
     * @param rows number of rows of grid
     * @param players array of players
     * @param grid the actual board
     * @return number of turns incremented by 1
     */
    public int generateAIMove(int turn, int numPlayers, int cols, int rows, Player[] players, Color[][] grid) {
        int[] validCols = findValidCols(grid);
        System.out.println("COLOR OF AI: " + c.toString());

        ArrayList<Integer> danger_col;
        int move = -1;

        //PRIORITY #1: WIN THE GAME

        for (int i = 0; i < validCols.length; i++) {
            Color[][] checkGrid = generateGridAfterMove(grid, validCols[i], this.c);
            if (checkIfWon(checkGrid) == c) {
                System.out.println("AI CAN WIN! MAKING THAT MOVE!!");
                move = i;
                break;
            }
        }
        int[] safeCols = new int[validCols.length];
        int[] colBlackList = new int[cols*2];
        int colBlackListCounter = 0;
        if(move == -1)
        {

            //PRIORITY #2: AVOID ENEMY 4 IN A ROW

            for (int i = 0; i < validCols.length; i++) {
                Color[][] checkGrid = generateGridAfterMove(grid, validCols[i], players[(turn + 1) % numPlayers].getToken());
                if (checkIfWon(checkGrid) == players[(turn + 1) % numPlayers].getToken()) {
                    System.out.println("ENEMY COULD WIN!! MAKING THAT MOVE!!");
                    move = i;
                    break;
                }
            }

            for (int i = 0; i < validCols.length; i++) {
                for (int j = 0; j < validCols.length; j++) {
                    safeCols[j] = validCols[j];
                }
                Color[][] checkGrid = generateGridAfterMove(grid, validCols[i], this.c);

                int[] Depth1ValidCols = findValidCols(checkGrid);
                for (int j = 0; j < Depth1ValidCols.length; j++) {

                    Color[][] checkGridDepth1 = generateGridAfterMove(checkGrid, Depth1ValidCols[j], players[(turn + 1) % numPlayers].getToken());
                    if (checkIfWon(checkGridDepth1) == players[(turn + 1) % numPlayers].getToken()) {
                        System.out.println("AI IS IN DANGER! OPPONENT CAN WIN IF I GO IN COLUMN " + (i + 1) + "!");
                        colBlackList[colBlackListCounter++] = i;
                    }
                }
            }
        }

        //PRIORITY #3: AVOID TILES WHICH WOULD LET THE OTHER PLAYER WIN THE GAME

        //PRIORITY #4: STACK 3s

        //PRIORITY #5: BLOCK 3s

        //CHOOSE
        int count = 0;
        for (int i = 0; i < safeCols.length; i++) {
            for (int j = 0; j < colBlackList.length; j++) {
                if (safeCols[i] == colBlackList[j])
                {
                    safeCols[i] = -1;
                    count++;
                }
            }
        }
        System.out.println(count+" different columns that I think are dangerous.");
        Random rand = new Random();
        if(move == -1)
        {
            if(colBlackList.length < 7)
            {
                for (int i = 0; i < safeCols.length; i++) {
                    if (safeCols[i] != -1)
                    {
                        move = safeCols[i];
                    }
                }
            }

            //otherwise go anywhere, bot has already lost.
            if(move == -1)
            {
                move = validCols[rand.nextInt(validCols.length)];
            }
        }

        makeMove(move, turn, numPlayers, cols, rows, players, grid);
        return turn;
    }

    /**
     * helper class to recolor the grid
     * @param move the type of move
     * @param turn number of turns
     * @param numPlayers number of players
     * @param cols num cols
     * @param rows num rows
     * @param players player array
     * @param grid the board
     */
    private void makeMove(int move, int turn, int numPlayers, int cols, int rows, Player[] players, Color[][] grid)
    {
        int ySpot = 0;
        Random rand = new Random();
        int xSpot = move;
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
    }

    /**
     * checks wins
     * @param grid the board
     * @return Color of winner or Color.white if there is no winner
     */
    private Color checkIfWon(Color[][] grid)
    {
        int HEIGHT = grid.length;
        int WIDTH = grid[grid.length-1].length;
        Color EMPTY_SLOT = Color.WHITE;
        for (int r = 0; r < HEIGHT; r++) { // iterate rows, bottom to top
            for (int c = 0; c < WIDTH; c++) { // iterate columns, left to right
                Color player = grid[r][c];
                if (player == EMPTY_SLOT)
                    continue; // don't check empty slots

                if (c + 3 < WIDTH &&
                        player == grid[r][c+1] && // look right
                        player == grid[r][c+2] &&
                        player == grid[r][c+3])
                    return player;
                if (r + 3 < HEIGHT) {
                    if (player == grid[r+1][c] && // look up
                            player == grid[r+2][c] &&
                            player == grid[r+3][c])
                        return player;
                    if (c + 3 < WIDTH &&
                            player == grid[r+1][c+1] && // look up & right
                            player == grid[r+2][c+2] &&
                            player == grid[r+3][c+3])
                        return player;
                    if (c - 3 >= 0 &&
                            player == grid[r+1][c-1] && // look up & left
                            player == grid[r+2][c-2] &&
                            player == grid[r+3][c-3])
                        return player;
                }
            }
        }
        return EMPTY_SLOT;
    }

    @Override
    public void setColor(Color c) {
        this.c = c;
    }

    /**
     * helper function find valid columns
     * @param grid the board
     * @return the valid column
     */
    private int[] findValidCols(Color[][] grid) {
        int count = 0;
        //System.out.println("Grid Length: " + grid.length);
        //System.out.println("Grid Length Length: " + grid[grid.length-1].length);
        for(int i = 0; i < grid[grid.length-1].length; i++)
        {
            if(grid[0][i] == Color.WHITE)
            {
                count++;
            }
        }
        int [] result = new int[count];
        count = 0;
        for(int i = 0; i < grid[grid.length-1].length; i++)
        {
            if(grid[0][i] == Color.WHITE)
            {
                //System.out.println("Found at space: "+(grid.length-1)+", "+i);
                result[count++] = i;
            }
        }
        return result;
    }
    //should only be called with c as a valid move

    /**
     * predicts what the grid looks like after one of the moves without actually committing
     * @param grid the board
     * @param c the move type
     * @param checkColor the color of the piece to drop
     * @return the grid
     */
    private Color[][] generateGridAfterMove(Color[][] grid, int c, Color checkColor) {
        int last = 0;
        Color[][] result = new Color[grid.length][grid[grid.length-1].length];
        //create a copy of our grid
        for (int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                result[i][j] = grid[i][j];
            }
        }
        for (int i = 0; i < result.length; i++)
        {
            if(result[0+i][c] == Color.WHITE)
            {
                last = i;
            }
        }
        result[last][c] = checkColor;
        return result;
    }
}
