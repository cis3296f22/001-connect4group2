import java.util.ArrayList;

public class Game {
    private Board board;
    private ArrayList<Player> p;
    private int turn = 0;
    private int limit = 0;

    public Game(int w, int h, ArrayList<Player> p) {
        this.limit = w * h;
        this.board = new Board(w, h);
        this.p = p;
        for(int i = 0; i < p.size(); i++)
        {
            p.get(i).setBoard(this.board);
        }
    }

    public void nextTurn() {
        int move;
        while(!board.isMoveValid((move = p.get(turn % p.size()).GetMove())));
        board.move(move, (turn % p.size()) + 1);
        board.printBoard();
        System.out.println(p.size());
        turn++;
    }

    public void play()
    {
        while(getTurn() < getLimit())
        {
            nextTurn();
        }
    }
    //g/s
    public int getTurn()
    {
        return turn;
    }
    public int getLimit()
    {
        return limit;
    }
    public int checkIfWon()//0 means game isnt over, any other number means that player won
    {

    }
}
