package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/frozen_winter.wav");
        soundURL[1] = getClass().getResource("/sound/powerup.wav");
        soundURL[2] = getClass().getResource("/sound/swingweapon.wav");
        soundURL[3] = getClass().getResource("/sound/Select 1.wav");
        soundURL[4] = getClass().getResource("/sound/Text 1.wav");
        soundURL[5] = getClass().getResource("/sound/Suck 1.wav");
        soundURL[6] = getClass().getResource("/sound/receivedamage.wav");
        soundURL[7] = getClass().getResource("/sound/hitmonster.wav");

    }

    public void setFile(int index) {
        try {
            // open audio file formate in java
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

}
