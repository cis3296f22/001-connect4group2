import java.awt.*;

public class ConnectFour {

    //default numPlayers
    public static int numPlayers = 2;
    private static int playerChooseColorToken = 1;

    public static void main(String[] args) throws Exception {
        // initialize board;
        DrawGrid board;

        //Play BGM
        try{
            System.out.println("here");
            Sound s = new Sound();
            s.playBackGround("Sounds\\mixkit-just-chill-16.wav");
            Thread t1 = new Thread(s);
            t1.start();
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        // this section will be deleted
        int x = 200;
        int y = 200;
        int width = 600;
        int height = 400;
        // save layout info in a obj
        LayoutDetails ld = new LayoutDetails(x,y,width,height);

        Gate gate = new Gate(false);     // true -> halt the loop, false -> run the loop
        // draw menu
        DrawMenu menu = new DrawMenu(ld,gate);

        while(menu.getMode() != 9) {
            // block the program
            while(gate.getValue() == true){
                System.out.println("****//////******");
            }
            // halt the loop, by setting true
            gate.setValue(true);
            // pvp
            if (menu.getMode() == 1) {
                // get number of players   ~  should get from user
                // Token class will implement custom token(need more work)
                Token[] tokens = new Token[numPlayers];
                Player[] players = new Player[numPlayers];
                setPlayers(players, tokens);

                System.out.println("Players has been created.");
                menu.hideFrame();
                updateLocation(menu.getlocation(), ld);

                // release the loop by clicking menu in frame
                new DrawGrid(players, ld, false, menu, gate);


                // player vs ai
            } else if (menu.getMode() == 2) {
                Token[] tokens = new Token[numPlayers];
                Player[] players = new Player[numPlayers];
                setPlayers(players, tokens);

                System.out.println("Players has been created.");
                menu.hideFrame();
                updateLocation(menu.getlocation(), ld);

                // release the loop by clicking menu in frame
                new DrawGrid(players, ld, true, menu, gate);

            }

        }




    }



    public static void setPlayers(Player[] p, Token[] t){
        for(int i = 0; i < p.length; i++){
            t[i] = new Token(playerChooseColorToken);
            p[i] = new Player(t[i].getColorToken());

            System.out.println("Player[" + (i+1) + "] has been created.");
        }
    }

    public static void updateLocation(Point p, LayoutDetails ld){
        ld.setX(p.x);
        ld.setY(p.y);
    }


}
