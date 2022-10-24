public class LocalBot extends Player{
    @Override
    public int GetMove() {
        return (int) (((Player) this).getBoard().getW() * Math.random());
    }
}
