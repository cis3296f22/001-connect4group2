import java.awt.*;
import java.util.Random;

public class Token {

    Color colorToken;
    public Token(){

    }
    // if t = 2, then set an image instead of pure color(need more work)
    public Token(int t){
        switch (t){
            case 1:
                colorToken = genToken();
                break;
            default:
                colorToken = Color.white;
        }
    }

    public static Color genToken(){
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }

    public Color getColorToken(){
        return colorToken;
    }


}
