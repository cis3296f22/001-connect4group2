import java.awt.*;

/**
 * base implementation of AI interface
 */
public interface AI {
    public int generateAIMove(int turn, int numPlayers, int cols, int rows, Player[] players, Color[][] grid);
    public void setColor(Color c);
}
