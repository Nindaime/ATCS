
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

    private final Pane pane;
    private TranslateTransition translateTransition;
    private double startX;
    private double startY;
    private DoubleProperty positionX = new SimpleDoubleProperty();
    private DoubleProperty positionY = new SimpleDoubleProperty();
    private ArrayList<Street> route = new ArrayList<>();
    private Street currentStreet;

    public Car(ArrayList<Street> route) {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        imageView.setImage(new Image(getClass().getResourceAsStream("img\\red.png")));

        this.route = route;
        currentStreet = route.get(0);
        this.pane = new Pane();
        this.pane.setLayoutX(400);
        this.pane.setLayoutY(0);

        this.pane.getChildren().add(imageView);

        play();
    }
    
    public void moveAndBend(){
    
    }

    public void play() {
        translateTransition = new TranslateTransition(Duration.millis(2000), this.pane);
        double streetWidth = currentStreet.getWidth();
        double streetHeight = currentStreet.getHeight();
        if (streetHeight > streetWidth) {
            translateTransition.setByY(streetHeight);
        } else {
            translateTransition.setByX(streetWidth);
        }
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        translateTransition.setOnFinished( (w)->{
         System.out.println("animation has finished "+w);
          
         play();
         
        });

        translateTransition.play();
        

    }

    public Pane asPane() {
        return pane;
    }

    public void pause() {
        translateTransition.pause();
    }

    public void resume() {
        translateTransition.play();
    }

}
