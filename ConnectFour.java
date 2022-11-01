import java.awt.*;

public class ConnectFour {

    //default numPlayers
    public static int numPlayers = 2;
    private static int playerChooseColorToken = 1;

    public static void main(String[] args) throws Exception {
        // initialize board;
        DrawGrid board;

        // this section will be deleted
        int x = 200;
        int y = 200;
        int width = 600;
        int height = 400;
        // save layout info in a obj
        LayoutDetails ld = new LayoutDetails(x,y,width,height);

        // draw menu
        DrawMenu menu = new DrawMenu(ld);

        while(menu.getMode() == 0){
            // block the program
            System.out.println("****//////******");
        }

        // pvp
        if(menu.getMode() == 1){
            // get number of players   ~  should get from user
            // Token class will implement custom token(need more work)
            Token[] tokens = new Token[numPlayers];
            Player[] players = new Player[numPlayers];
            setPlayers(players, tokens);


            System.out.println("Players has been created.");
            menu.hideFrame();
            updateLocation(menu.getlocation(),ld);
            board = new DrawGrid(players, ld);      // will be used later


            // player vs ai
        }else if(menu.getMode() == 2){

            //
        }





    }


    public static void setPlayers(Player[] p, Token[] t){
        for(int i = 0; i < p.length; i++){
            t[i] = new Token(playerChooseColorToken);
            p[i] = new Player(t[i].getColorToken());

            System.out.println("ConnectFour.Player[" + (i+1) + "] has been created.");
        }
    }

    public static void updateLocation(Point p, LayoutDetails ld){
        ld.setX(p.x);
        ld.setY(p.y);
    }


    public static class Player {
        private Color token;

        public Player() {
            this.token = Color.WHITE;
        }

        public Player(Color t) {
            this.token = t;
        }

        public Color getToken() {
            return this.token;
        }
    }
}
