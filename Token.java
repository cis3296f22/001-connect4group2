import java.awt.*;
import java.util.Random;

/**
 * Token object
 */
public class Token {

    Color colorToken;

    /**
     * constructor default with white token
     */
    public Token(){
        colorToken = Color.white;
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

    /**
     * generates random token
     * @return token color
     */
    public Color genToken(){
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }
    public void setToken(int[] color){
        int red = color[0];
        int green = color[1];
        int blue = color[2];
        colorToken = new Color(red,green,blue);
    }
    public Color getColorToken(){
        return colorToken;
    }


}
