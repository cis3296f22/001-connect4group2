import java.awt.*;

public interface AI {

    public int generateAIMove(int turn, int numPlayers, int cols, int rows, Player[] players, Color[][] grid);

}
