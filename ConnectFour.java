import java.awt.*;

public class ConnectFour {

    //default numPlayers



    public static void main(String[] args) throws Exception {
        // initialize board;
        DrawGrid board;

        //Play BGM
        try {
            Sound s = new Sound();
            s.playBackGround("Sounds\\mixkit-just-chill-16.wav");
            Thread t1 = new Thread(s);
            t1.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // this section will be deleted
        int x = 200;
        int y = 200;
        int width = 600;
        int height = 400;
        // save layout info in a obj
        LayoutDetails ld = new LayoutDetails(x, y, width, height);

        // draw menu
        new DrawMenu(ld);

    }


}
