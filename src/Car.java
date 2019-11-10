
import java.util.ArrayList;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User01
 */
public class Car {

//    private final Pane pane;
    
   //expose the list of all cars

    public Car(ArrayList<Street> route) {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        imageView.setImage(new Image(getClass().getResourceAsStream("img\\red.png")));

        //this.route = route;
        //numOfStreets = route.size();
        //currentStreetIndex = 0;
        //currentStreet = route.get(currentStreetIndex);
        //this.pane = new Pane();
        //this.pane.setLayoutX(currentStreet.getX());
        //this.pane.setLayoutY(currentStreet.getY());

        //this.pane.getChildren().add(imageView);
        
        
        // get the closest Traffic Light group and pick one of the lights depending on direction
    }
    
    // listener to get if the light is green
    // it true play
    // if false stop

    
    public void stop(){}
    public void start(){}
    

}
