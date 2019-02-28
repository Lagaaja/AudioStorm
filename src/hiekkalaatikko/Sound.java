package hiekkalaatikko;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
    
    private double secondsLength;
    
    private final Media sound;
    private final MediaPlayer mp;
    private double currentTime;
    
    
    public Sound(String path) {
        
        String mediaURI = new File(path).toURI().toString();
        System.out.println(mediaURI);
        this.sound = new Media(mediaURI);
        this.mp = new MediaPlayer(this.sound);
        
        this.mp.setOnReady(new Runnable() {
            @Override
            public void run() {
                secondsLength = mp.getStopTime().toSeconds();
                Hiekkalaatikko.initSlider();
                mp.play();
            }
            
        });
        this.currentTime = this.mp.currentTimeProperty().get().toSeconds();
        
        
    }
    
    public void play() {
        mp.play();
    }
    
    public void close() {
        this.mp.dispose();

    }

    public double getSecondsLength() {
        return secondsLength;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public void updateCurrentTime() {
        this.currentTime = this.mp.currentTimeProperty().get().toSeconds();
    }

    public MediaPlayer getMp() {
        return mp;
    }  
    
    
    
}
