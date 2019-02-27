
package hiekkalaatikko;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.SourceDataLine;




public class Hiekkalaatikko extends Application {
   private static Sound song;
    
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

        Slider slider = new Slider();
        slider.setMin(0);
        int mins = (int)(song.getSecondsLength()/60);
        int seconds = (int)(song.getSecondsLength()-(mins*60));
        
        StringConverter<Double> stringConverter = new StringConverter<Double>() {
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
        slider.setLabelFormatter(stringConverter);
        slider.setMax(song.getSecondsLength());
        slider.setValue(0);
        slider.setShowTickLabels(true);
        //slider.setShowTickMarks(true);
        slider.setMajorTickUnit(song.getSecondsLength());
        //slider.setMinorTickCount(5);
        slider.setBlockIncrement(1);
        
        Text timeMark = new Text("00:00");
        slider.valueProperty().addListener((observable, oldValue, newValue) ->
            timeMark.setText(stringConverter.toString(newValue.doubleValue())));

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
        grid.getChildren().addAll(slider, timeMark);
        scene.setRoot(grid);

        //Displaying the contents of the stage 
        primaryStage.show();
        
        SourceDataLine sl = AudioSystem.getSourceDataLine(song.getaFormat());
        sl.open(song.getaFormat());
        sl.start();
    }
    
    
}
