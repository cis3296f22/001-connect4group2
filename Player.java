public abstract class Player {
    private Board board;
    public abstract int GetMove();
    public Board getBoard()
    {
        return this.board;
    }
    public void setBoard(Board board)
    {
        this.board = board;
    }
}
