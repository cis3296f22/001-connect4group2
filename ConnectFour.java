import java.awt.*;
import java.util.Random;

public class ConnectFour {
    public static void main(String[] args) throws Exception {

        // get number of players   ~  should get from user
        Player[] players = new Player[2];
        setPlayers(players);

/*
        Player a = new Player();
        a.setToken(genToken());
        Player b = new Player();  */
       // players[1].setToken(genToken());
        //System.out.println(players[0].getToken());
        System.out.println("Players have been created.");


        new DrawGrid(players);





    }


    public static void setPlayers(Player[] p){
        for(int i = 0; i < p.length; i++){
            p[i] = new Player(genToken());
        }
    }
    public static Color genToken(){
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
       // return new Color((int)(Math.random()*0x1000000));
    }
}
