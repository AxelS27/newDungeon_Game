package com.TPOW.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    public int currentSoundIndex = -1;

    public Sound(){
        soundURL[0] = getClass().getResource("/sounds/cursor.wav");
        soundURL[1] = getClass().getResource("/sounds/coin.wav");
        soundURL[2] = getClass().getResource("/sounds/powerup.wav");
        soundURL[3] = getClass().getResource("/sounds/unlock.wav");
        soundURL[4] = getClass().getResource("/sounds/fanfare.wav");
        soundURL[5] = getClass().getResource("/sounds/hitmonster.wav");
        soundURL[6] = getClass().getResource("/sounds/receivedamage.wav");
        soundURL[7] = getClass().getResource("/sounds/swingweapon.wav");
        soundURL[8] = getClass().getResource("/sounds/jingle_bells.wav");
        soundURL[9] = getClass().getResource("/sounds/Dungeon.wav");
        soundURL[10] = getClass().getResource("/sounds/let_it_snow.wav");
    }

    public void setFile(int i){
        try {
            if (clip != null) {
                clip.close();
            }
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            currentSoundIndex = i;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void play(){
        if (clip != null) clip.start();
    }

    public void stop(){
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
        if (clip != null) {
            clip.close();
            clip = null;
        }
        currentSoundIndex = -1;
    }

    public void loop(){
        if (clip != null) clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playSE(int i) {
        new Thread(() -> {
            try {
                Clip seClip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
                seClip.open(ais);
                seClip.start();
                while (!seClip.isRunning()) Thread.sleep(1);
                while (seClip.isRunning()) Thread.sleep(1);
                seClip.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void playMusic(int i) {
        stop();
        setFile(i);
        play();
        loop();
    }
}