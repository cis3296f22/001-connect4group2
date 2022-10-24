import java.util.ArrayList;

public class MainTest {


    public static void main(String[] args)
    {
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new LocalPlayer();
        Player p2 = new LocalPlayer();
        Player b1 = new LocalBot();
        players.add(p1);
        players.add(b1);
        Game g = new Game(7,6, players);

        g.play();
    }
}
