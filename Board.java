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
    public int getW()
    {
        return this.w;
    }
    public int getH()
    {
        return this.h;
    }
}
