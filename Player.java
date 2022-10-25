import java.awt.*;
import java.util.Random;

public class Player {
    private Color token;



    public Player(){
        token = Color.WHITE;
    }
    public Player(Color t){
        token = t;
    }


    public Color getToken(){
        return token;
    }
}
