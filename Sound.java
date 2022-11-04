import javax.swing.*;
import java.awt.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Sound {
    private Object stop;

    public static void main(String[] args)throws InterruptedException {
        JFrame f = new JFrame();

        Sound Sound = new Sound();
        Sound.Run("mixkit-just-chill-16");


        Object stop1 = Sound.stop;
        //Sound.Run("Rejekt_-_Crank.wav");
    }

    private void Run(String s) {
    }
}
