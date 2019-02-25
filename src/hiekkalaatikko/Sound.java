/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiekkalaatikko;

import java.io.File;
import java.io.IOException;
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
    
    public Sound(String path) {
        try {
            File file = new File(path);
            this.in = AudioSystem.getAudioInputStream(file);
            this.aFormat = in.getFormat();
            this.frameLength = in.getFrameLength();
            this.frameRate = this.aFormat.getFrameRate();
            this.secondsLength = this.frameLength/this.aFormat.getFrameRate();
        } catch(UnsupportedAudioFileException | IOException e) {
            System.err.println(e.getMessage());
        }
        
    }
    
    public void play() {
        try {
            this.clip = AudioSystem.getClip();
            clip.open(this.in);
            clip.start();
        } catch(LineUnavailableException | IOException e) {
            System.err.println(e.getMessage());
        }
        
    }
    
    public void close() {
        try {
            this.in.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
    
    
    
    /*Scanner lukija = new Scanner(System.in);
        try {
            File file = new File("Beyond the Wall of Sleep.wav");
            AudioInputStream in = AudioSystem.getAudioInputStream(file);
            AudioFormat aFormat = in.getFormat();
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();

            long frameLength = in.getFrameLength();
            double length = (frameLength*1.0)/aFormat.getFrameRate();
            
            while(true) {
                System.out.println("Skip to seconds: ");
                String input = lukija.nextLine();
                if(input.equals("quit")) {
                    break;
                }
                int skip = Integer.parseInt(input);
                int frames = (int)(skip*aFormat.getFrameRate());
                System.out.println("-- Frames: " + frames);
                
                clip.setFramePosition(frames);
            }
            
            in.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }*/

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
}