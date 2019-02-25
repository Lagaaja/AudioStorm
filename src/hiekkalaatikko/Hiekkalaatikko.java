
package hiekkalaatikko;

import java.io.File;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;




public class Hiekkalaatikko extends Application {
   private static Sound song;
    
    public static void main(String[] args) {
        song = new Sound("Beyond the Wall of Sleep.wav");
        launch(args);
        
        
        
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
        slider.setMax(song.getSecondsLength());
        slider.setValue(0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
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
        grid.getChildren().add(slider);
        scene.setRoot(grid);

        //Displaying the contents of the stage 
        primaryStage.show();
    }
    
}
