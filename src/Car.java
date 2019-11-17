
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;

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

    private Pane pane;
    private ImageView image;
    private GraphPath<String, DefaultEdge> route;
    private SequentialTransition seq;
    private static final double WIDTH = 10;
    private static final double HEIGHT = 10;
    private boolean isStopped = false;

    private long pauseTime = 0;
    private long playTime;
    private long pauseStartTime;
    private long playStartTime;

    //expose the list of all cars
    public Car(GraphPath<String, DefaultEdge> route) {
        image = new ImageView(new Image(getClass().getResourceAsStream("img/red.png")));
        image.setFitHeight(HEIGHT);
        image.setPreserveRatio(true);
        pane = new Pane();
        pane.setPrefSize(WIDTH, HEIGHT);
        pane.getChildren().add(image);
        this.route = route;

        // get the closest Traffic Light group and pick one of the lights depending on direction
        pauseStartTime = System.currentTimeMillis();
    }

    // listener to get if the light is green
    // it true play
    // if false stop
    public Pane getCar() {

        return pane;
    }

    public double getSize() {
        return seq.getNode().layoutXProperty().get();
    }

    public double getX() {
//        System.out.println("this is the sequence" + seq);
        return seq.getNode().getTranslateX();
    }

    public double getY() {
        return seq.getNode().getTranslateY();
    }

    public double getWidth() {
//        System.out.println("this is the sequence" + seq);
        return WIDTH;
    }

    public double getHeight() {
        return HEIGHT;
    }

    public List getVertexList() {
        return route.getVertexList();
    }

    public List getEdgeList() {
        return route.getEdgeList();
    }

    public boolean isSequenceSet() {
//        System.out.println("seq"+seq);
        return seq != null;
    }

    public void setSequenceTransition(SequentialTransition seq) {
        this.seq = seq;

        seq.setOnFinished((e) -> {
            playTime = System.currentTimeMillis() - playStartTime;
            System.out.println("total time played " + playTime);
            System.out.println("total time paused " + pauseTime);

        });

        seq.setNode(pane);
        playStartTime = System.currentTimeMillis();
        start();

    }

    public long getTotalDelayTime() {
        return pauseTime;
    }

    public long getTotalPlayTime() {
        return playTime;
    }

    public void stop() {

        seq.pause();
        pauseStartTime = System.currentTimeMillis();

        isStopped = true;

    }

    public void start() {
        seq.play();
        pauseTime += (System.currentTimeMillis() - pauseStartTime);
        System.out.println("total time paused " + pauseTime);
        isStopped = false;

    }

    public boolean isStopped() {
        return isStopped;
    }

}
