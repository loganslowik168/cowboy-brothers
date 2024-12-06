package com.team5.cowboy_brothers;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MusicControl {

    private Clip clip;

    public void playMusic(InputStream musicStream) {
        try {
            // Wrap the InputStream with a BufferedInputStream to support mark and reset
            BufferedInputStream bufferedStream = new BufferedInputStream(musicStream);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(bufferedStream);
            clip = AudioSystem.getClip();
            clip.open(audioInput);

            // Set the volume control (optional)
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-10.0f); // Adjust volume as needed

            // Start playing and loop continuously
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
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
