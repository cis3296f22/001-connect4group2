import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;

/**
 * plays background music
 */
public class Sound implements Runnable
{
    private boolean running = true;
    private Thread thread;

    public Sound()
    {
        this.start();
    }

    public void start()
    {
        if(running)
            return;
        this.thread = new Thread(this);
        this.running = true;
        this.thread.start();
    }

    //
    private boolean playSong = true;
    private AudioInputStream inputStream;
    private String url;
    private Clip clip;

    @Override
    public void run()
    {
        while(running)
        {
            if(inputStream == null && playSong)
            {
                this.playSong = false;
                try
                {
                    this.inputStream = AudioSystem.getAudioInputStream(Sound.class.getResource(url));
                    this.clip.open(inputStream);
                    this.clip.loop(10);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void playBackGround(String string) // call to play .wav file
    {
        if(this.clip != null)
        {
            this.clip.stop();
            this.clip.close();
        }
        try
        {
            this.clip = AudioSystem.getClip();
        }
        catch(LineUnavailableException e)
        {
            e.printStackTrace();
        }
        url = string;
        this.playSong = true;
        this.inputStream = null;
    }

    public void disposeSound()
    {


        this.playSong = false;
        this.inputStream = null;
        this.run();
    }
}