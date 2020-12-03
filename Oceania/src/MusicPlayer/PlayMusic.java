package MusicPlayer;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class PlayMusic {

    private File musicFile1;
    private File musicFile2;
    private Clip clip;
    private AudioInputStream audioInput;
    private boolean hasPlayed, hasPlayed2, isPlaying;
    private long timePlayed1, timePlayed2;

    public PlayMusic(String musicFile1, String musicFile2){
        this.musicFile1 = new File(musicFile1);
        this.musicFile2 = new File(musicFile2);
        clip = null;
        audioInput = null;
        hasPlayed = false;
        hasPlayed2 = false;
        isPlaying = false;
        timePlayed1 = 0;
        timePlayed2 = 0;
    }

    public void startMusicFile1()
    {
        try {
            if(musicFile1.exists()) {
                audioInput = AudioSystem.getAudioInputStream(musicFile1);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                if(hasPlayed)
                {
                    clip.setMicrosecondPosition(timePlayed1);
                }
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else {
                System.out.println("Can't find file");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        hasPlayed = true;
        isPlaying = true;
    }

    public void startMusicFile2()
    {
        try {
            if(musicFile2.exists()) {
                audioInput = AudioSystem.getAudioInputStream(musicFile2);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                if(hasPlayed2)
                {
                    clip.setMicrosecondPosition(timePlayed2);
                }
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else {
                System.out.println("Can't find file");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        hasPlayed2 = true;
        isPlaying = true;
    }

    public boolean isPlaying()
    {
        return isPlaying;
    }

    public void stopMusicFile1()
    {
        if(clip != null)
        {
            timePlayed1 = clip.getMicrosecondPosition();
            clip.stop();
        }
        isPlaying = false;
    }
    public void stopMusicFile2()
    {
        if(clip != null)
        {
            timePlayed2 = clip.getMicrosecondPosition();
            clip.stop();
        }
        isPlaying = false;
    }
}
