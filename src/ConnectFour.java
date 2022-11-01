import java.awt.*;
import java.util.Random;

public class ConnectFour {

    //default numPlayers
    public static int numPlayers = 2;
    private static int playerChooseColorToken = 1;

    public static void main(String[] args) throws Exception {
        // this section will be deleted
        int x = 200;
        int y = 200;
        int width = 600;
        int height = 400;
        // save layout info in a obj
        LayoutDetails ld = new LayoutDetails(x,y,width,height);

        // draw menu
        DrawMenu menu = new DrawMenu(ld);



        // get number of players   ~  should get from user
        // Token class will implement custom token(need more work)
        Token[] tokens = new Token[numPlayers];
        Player[] players = new Player[numPlayers];
        setPlayers(players, tokens);


        System.out.println("Players has been created.");

        new DrawGrid(players, ld);


    }


    public static void setPlayers(Player[] p, Token[] t){
        for(int i = 0; i < p.length; i++){
            t[i] = new Token(playerChooseColorToken);
            p[i] = new Player(t[i].getColorToken());

            System.out.println("Player[" + (i+1) + "] has been created.");
        }
    }


}
