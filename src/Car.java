
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
    private Street nextStreet;
    private int numOfStreets;
    private int currentStreetIndex;
    private boolean isStreetEnd;

    public Car(ArrayList<Street> route) {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        imageView.setImage(new Image(getClass().getResourceAsStream("img\\red.png")));

        this.route = route;
        numOfStreets = route.size();
        isStreetEnd = false;
        currentStreetIndex = 0;
        currentStreet = route.get(currentStreetIndex);
        nextStreet = route.get(currentStreetIndex++);
        this.pane = new Pane();
        this.pane.setLayoutX(currentStreet.getX1());
        this.pane.setLayoutY(currentStreet.getY1());

        this.pane.getChildren().add(imageView);

        //move();
    }

    public void bendLeft() {

    }

    public void bendRight() {
    }

    public void driveLeft(double width) {
        translateTransition.setToX(width);
//        translateTransition.setToY(currentStreet.getY());
    }

    public void driveRight(double width) {
        translateTransition.setByX(width);
    }

    public void driveUp(double height) {
        translateTransition.setByY(height);
    }

    public void driveDown(double height) {
        translateTransition.setByY(height);
    }

    public void getNextStreet() {
//        prevStreet = currentStreet;
//        currentStreetIndex++;
//        if (currentStreetIndex == numOfStreets) {
//            return;
//        }
//
//        currentStreet = route.get(currentStreetIndex);
//        
//        
//        play();
    }

    public void move() {
        if(isStreetEnd){
        
        }else{
        
        }
        
        isStreetEnd = !isStreetEnd;
//        double streetWidth = currentStreet.getWidth();
//        double streetHeight = currentStreet.getHeight();
//        double speed = currentStreet.getSpeed();
//        translateTransition = new TranslateTransition(Duration.millis(speed), this.pane);
//        
//        if (streetHeight > streetWidth) {
//            
//            if( this.pane.getLayoutY() < currentStreet.getY() + streetHeight){
//                System.out.println("moving down");
//                driveDown(streetHeight);
//            }else{
//                System.out.println("moving up");
//                driveUp(currentStreet.getY() - streetHeight);
//            }
//            
//        } else {
//            System.out.println("current street width ");
//            if( this.pane.getLayoutX() < currentStreet.getX() + streetWidth){
//                System.out.println("moving right");
//                driveRight(streetWidth);
//            }else{
//                System.out.println("moving left");
//                driveLeft(currentStreet.getX() - streetWidth);
//            }
//           
//        }
//        translateTransition.setCycleCount(1);
//        translateTransition.setAutoReverse(false);
//        translateTransition.setOnFinished((w) -> {
//            System.out.println("animation has finished " + w);
//            getNextStreet();
//
//        });
//
//        translateTransition.play();

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
