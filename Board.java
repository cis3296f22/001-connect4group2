import java.util.ArrayList;

public class Board {
    private int w, h;//board dimensions
    private int t;//turn counter

    private int[][] board;

    public Board(int w, int h) {
        this.w = w;
        this.h = h;

        board = new int[h][w];
        printBoard();
    }

    public void move(int col, int num)
    {
        if(!isMoveValid(col))
        {
            return;
        }
        for(int i = h-1; i >= 0; i--)
        {
            if(board[i][col] == 0)
            {
                board[i][col] = num;
                break;
            }
        }
    }
    public boolean isMoveValid(int col)
    {
        if(board[0][col] == 0)
        {
            return true;
        }
        return false;
    }
    public void printBoard() {
        for (int i = 0; i < h; i++)
        {
            for(int j = 0; j < w; j++)
            {
                System.out.print("["+board[i][j]+"]");
            }
            System.out.println();
        }
    }
    //https://codereview.stackexchange.com/questions/127091/java-connect-four-four-in-a-row-detection-algorithms
    public int checkIfWon()
    {
        int HEIGHT = this.h;
        int WIDTH = this.w;
        int EMPTY_SLOT = 0;
        for (int r = 0; r < HEIGHT; r++) { // iterate rows, bottom to top
            for (int c = 0; c < WIDTH; c++) { // iterate columns, left to right
                int player = board[r][c];
                if (player == EMPTY_SLOT)
                    continue; // don't check empty slots

                if (c + 3 < WIDTH &&
                        player == board[r][c+1] && // look right
                        player == board[r][c+2] &&
                        player == board[r][c+3])
                    return player;
                if (r + 3 < HEIGHT) {
                    if (player == board[r+1][c] && // look up
                            player == board[r+2][c] &&
                            player == board[r+3][c])
                        return player;
                    if (c + 3 < WIDTH &&
                            player == board[r+1][c+1] && // look up & right
                            player == board[r+2][c+2] &&
                            player == board[r+3][c+3])
                        return player;
                    if (c - 3 >= 0 &&
                            player == board[r+1][c-1] && // look up & left
                            player == board[r+2][c-2] &&
                            player == board[r+3][c-3])
                        return player;
                }
            }
        }
        return EMPTY_SLOT;
    }
    private boolean checkIfWonHorizontal(int player)
    {

        return false;
    }
    public int getW()
    {
        return this.w;
    }
    public int getH()
    {
        return this.h;
    }
}
