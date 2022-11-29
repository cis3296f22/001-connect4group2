import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BackgroundSound {

    private Clip clip;
    private volatile boolean running,looping;
    public BackgroundSound(){
        running = false;
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(BackgroundSound.class.getResource("res/sounds/mixkit-just-chill-16.wav")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void play() {
        running = true;
        looping = true;
        clip.setFramePosition(0);
        new Thread(new Runnable(){
            public void run(){

                clip.loop(10);
                while (true) {
                    if (clip.getMicrosecondPosition() == clip.getMicrosecondLength())
                        break;
                    if (!running)
                        break;
                }

                try {
                    clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(BackgroundSound.class.getResource("res/sounds/mixkit-just-chill-16.wav")));
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        }).start();
    }
    public void pause(){//stop background music
        running = false;
        clip.stop();
    }
    public void resume()//play background music
    {

        running = true;
        new Thread(new Runnable(){
            public void run(){
                clip.start();
                while(true){
                    if(clip.getMicrosecondPosition() == clip.getMicrosecondLength())
                        break;
                    if(!running)
                        break;
                }
            }
        }).start();
    }
}//background music