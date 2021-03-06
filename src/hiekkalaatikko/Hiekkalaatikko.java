
package hiekkalaatikko;

import java.util.concurrent.TimeUnit;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;




public class Hiekkalaatikko extends Application {
   private static Sound song;
   private static Slider slider = new Slider();
    
    public static void main(String[] args) {
        song = new Sound("Beyond the Wall of Sleep.wav");
        launch(args);
        song.close();
        
        
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //creating a Group object 
        Group group = new Group();

        //Creating a Scene by passing the group object, height and width   
        Scene scene = new Scene(group, 600, 300);

        StringConverter<Double> stringConv = new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                long seconds = object.longValue();
                long minutes = TimeUnit.SECONDS.toMinutes(seconds);
                long remainingseconds = seconds - TimeUnit.MINUTES.toSeconds(minutes);
                return String.format("%02d", minutes) + ":" + String.format("%02d", remainingseconds);
            }

            @Override
            public Double fromString(String string) {
                return null;
            }
        };
        
        Text viewSongPos = new Text("00:00");
        
        slider.setLabelFormatter(stringConv);
        slider.setMin(0);
        slider.setBlockIncrement(10);
        

        //setting color to the scene 
        scene.setFill(Color.BLUE);

        //Setting the title to Stage. 
        primaryStage.setTitle("Sample Application");

        //Adding the scene to Stage 
        primaryStage.setScene(scene);
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(70);
        
        GridPane.setConstraints(slider, 1, 1);
        grid.getChildren().addAll(slider, viewSongPos);
        scene.setRoot(grid);

        //Displaying the contents of the stage 
        primaryStage.show();
        
        AnimationTimer trackSong = new AnimationTimer() {
            @Override public void handle(long currentNanoTime) {
                double newPos = song.getMp().currentTimeProperty().get().toSeconds();
                if(newPos > song.getCurrentTime()) {
                    song.updateCurrentTime();
                    slider.setValue(newPos);
                }
            }
        };
        trackSong.start();
        slider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue) {
                    //System.out.println(new Duration(slider.getValue()*1000).toSeconds());
                    song.getMp().seek(new Duration(slider.getValue()*1000));
                    song.updateCurrentTime();
                    trackSong.start();
                } else {
                    trackSong.stop();
                }
            }
            
            
        });
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                viewSongPos.textProperty().setValue(stringConv.toString((Double)newValue));
            }
            
        });
    }
    
    public static void initSlider() {
        slider.setMax(song.getSecondsLength());
        slider.setValue(0);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(song.getSecondsLength());
    }
    
    
}
