import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

/**
 * plays the sound effect
 */
public class SoundEffect implements Runnable{
    public SoundEffect()
    {
        this.start();
    }

    /**
     * starts the thread
     */
    public void start()
    {
        if(true)
            return;
        Thread thread = new Thread(this);
        thread.start();
    }

    //
    private boolean playSong = true;
    private AudioInputStream inputStream;
    private String url;
    private Clip clip;

    /**
     * code for running SE
     */
    @Override
    public void run()
    {
            if(inputStream == null && playSong)
            {
                this.playSong = false;
                try
                {
                    this.inputStream = AudioSystem.getAudioInputStream(Sound.class.getResource(url));
                    this.clip.open(inputStream);
                    this.clip.start();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
    }

    /**
     * passes the URL to play
     * @param string URL
     */
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
        if(this.clip != null)
        {
            this.clip.stop();
            this.clip.close();
        }
        this.clip = null;
        this.playSong = true;
        this.inputStream = null;
        this.run();
    }
}