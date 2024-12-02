package com.team5.cowboy_brothers;

/**
 *
 * @author Ace
 */
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class MusicControl {
    
    private Clip clip;

    public void playMusic(String filepath) {
        try {
            // Load the audio file
            File musicPath = new File(filepath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);

                // Set the volume control here
                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue(-10.0f); // Reduce volume by 10 decibels (adjust as needed)

                // Start playing and loop continuously
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Cannot find the specified audio file: " + filepath);
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Stop the music if needed
    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
