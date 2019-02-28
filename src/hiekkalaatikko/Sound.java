/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiekkalaatikko;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Aleksi
 */
public class Sound {
    
    private AudioInputStream in;
    private AudioFormat aFormat;
    private Clip clip;
    private long frameLength;
    private double frameRate;
    private double secondsLength;
    //private long clipPos;
    
    private Media sound;
    private MediaPlayer mp;
    private double currentTime;
    
    
    public Sound(String path) {
        /*try {
            File file = new File(path);
            this.in = AudioSystem.getAudioInputStream(file);
            this.aFormat = in.getFormat();
            this.frameLength = in.getFrameLength();
            this.frameRate = this.aFormat.getFrameRate();
            this.secondsLength = this.frameLength/this.aFormat.getFrameRate();
            this.clip = AudioSystem.getClip();
            this.clipPos = this.clip.getMicrosecondPosition();
            System.out.println("try clause");
            clip.open(this.in);
        } catch(UnsupportedAudioFileException | IOException |LineUnavailableException e) {
            System.out.println("Sound constructor");
            System.err.println(e.getMessage());
        }*/
        String mediaURI = new File(path).toURI().toString();
        System.out.println(mediaURI);
        this.sound = new Media(mediaURI);
        this.secondsLength = this.sound.getDuration().toSeconds();
        this.mp = new MediaPlayer(this.sound);
        this.currentTime = this.mp.currentTimeProperty().get().toSeconds();
        
        
    }
    
    public void play() {
        //clip.start();
        mp.play();
    }
    
    public void close() {
        try {
            this.in.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
    
    public int bytesLeft() {
        try {
        return this.clip.available();
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public AudioInputStream getIn() {
        return in;
    }

    public AudioFormat getaFormat() {
        return aFormat;
    }

    public Clip getClip() {
        return clip;
    }

    public long getFrameLength() {
        return frameLength;
    }

    public double getFrameRate() {
        return frameRate;
    }

    public double getSecondsLength() {
        return secondsLength;
    }

    public /*long*/double /*getClipPos()*/getCurrentTime() {
        return /*clipPos*/currentTime;
    }

    public void /*updateClipPos()*/updateCurrentTime() {
        //this.clipPos = clip.getMicrosecondPosition();
        this.currentTime = this.mp.currentTimeProperty().get().toSeconds();
    }

    public MediaPlayer getMp() {
        return mp;
    }
    
    
    
    
    
    
}
