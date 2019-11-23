
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
    private Direction carDirection;
    private Double currentX;
    private Double currentY;
    private static final double WIDTH = 10;
    private static final double HEIGHT = 10;
    private boolean isStopped = false;
    private static final int DECIMAL_PLACES = 0;
    private static final double PROXIMITY_THRESHOLD = 50;
    private long pauseTime = 0;
    private long playTime;
    private long pauseStartTime;
    private long playStartTime;

    private boolean isCurrentXInitialize = false;
    private boolean isCurrentYInitialize = false;

    static File[] files = new File("src/img/cars/").listFiles();

    //expose the list of all cars
    public Car(GraphPath<String, DefaultEdge> route) {

        Random rand = new Random();

        File file = files[rand.nextInt(files.length)];

        image = new ImageView(new Image(getClass().getResourceAsStream(file.getPath().replaceAll("src", ""))));

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
        double x = seq.getNode().getTranslateX();
        if (!isCurrentXInitialize) {
            currentX = x;
            isCurrentXInitialize = true;
        }

        return x;
    }

    public double getY() {
        double y = seq.getNode().getTranslateY();
        if (!isCurrentYInitialize) {
            currentY = y;
            isCurrentYInitialize = true;
        }
        return y;
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
        return seq != null;
    }

    public Direction getDirection() {

        double xDistance = round(this.getX() - currentX, DECIMAL_PLACES);
        double yDistance = round(this.getY() - currentY, DECIMAL_PLACES);
//        System.out.printf("current x:%f  currenty:%f  new x:%f  newy:%f \n", currentX, currentY, this.getX(), this.getY());
//        System.out.printf("diff  x:%f  diff y:%f \n", xDistance, yDistance);
        currentX = this.getX();
        currentY = this.getY();

        if (!isCurrentXInitialize || !isCurrentYInitialize) {
            System.out.println("this is when it is not yet set");
            return Direction.D;
        }

        if (xDistance == 0) {
            if (yDistance < 0) {
                return Direction.N;
            } else if (yDistance > 0) {
                return Direction.S;
            }

        }

        if (yDistance == 0) {
            if (xDistance < 0) {
                return Direction.W;
            } else if (xDistance > 0) {
                return Direction.E;
            }

        }

        if (yDistance > 0 && xDistance > 0) {
            return Direction.SE;
        }

        if (yDistance < 0 && xDistance < 0) {
            return Direction.NW;
        }

        if (yDistance > 0 && xDistance < 0) {
            return Direction.SW;
        }

        if (yDistance < 0 && xDistance > 0) {
            return Direction.NE;
        }

        return Direction.D;

    }

    public void setSequenceTransition(SequentialTransition seq) {
        this.seq = seq;

        seq.setOnFinished((e) -> {
            playTime = System.currentTimeMillis() - playStartTime;

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
        isStopped = false;

    }

    public void reduceSpeed() {

        seq.setRate(0.5);
    }

    public void restoreSpeed() {
        seq.setRate(1);

    }

    public boolean isStopped() {
        return isStopped;
    }

    private double getDistanceBetween(Car car) {
        double xDistance = this.getX() - car.getX();
        double yDistance = this.getY() - car.getY();
        if (xDistance == 0) {
            return yDistance;
        }

        if (yDistance == 0) {
            return xDistance;
        }

        return (this.getY() - car.getY()) / (this.getX() - car.getX());

    }

    public void checkProximity(ArrayList<Car> cars) {

        cars.forEach(car -> {
            Direction direction = this.getDirection();

            if (!car.isSequenceSet() || !direction.equals(car.getDirection())) {
                return;
            }

            double distance = this.getDistanceBetween(car);

//            System.out.println(" Get car direction : " + this.getDirection().equals(car.getDirection()));
//            System.out.println(" the distance between both cars are : " + this.getDirection() + " : " + car.getDirection());
            System.out.printf(" distance:%f, direction: %s \n", this.getDistanceBetween(car), direction.toString());
            if (distance <= PROXIMITY_THRESHOLD && distance > 0) {
                switch (direction) {
                    case N:
                        this.reduceSpeed();
                        break;
                    case S:
                        car.reduceSpeed();
                        break;
                    case E:
                        car.reduceSpeed();
                        break;
                    case W:
                        this.reduceSpeed();
                        break;
                    case NE:
                        this.reduceSpeed();
                        break;
                    case NW:
                        car.reduceSpeed();
                        break;
                    case SE:
                        car.reduceSpeed();
                        break;
                    case SW:
                        this.reduceSpeed();
                        break;
                    default:
                }
                return;
            }

            if (distance < 0 && distance >= -PROXIMITY_THRESHOLD) {

                switch (direction) {
                    case N:
                        car.reduceSpeed();
                        break;
                    case S:
                        this.reduceSpeed();
                        break;
                    case E:
                        this.reduceSpeed();
                        break;
                    case W:
                        car.reduceSpeed();
                        break;
                    case NE:
                        car.reduceSpeed();
                        break;
                    case NW:
                        this.reduceSpeed();
                        break;
                    case SE:
                        this.reduceSpeed();
                        break;
                    case SW:
                        car.reduceSpeed();
                        break;
                    default:
                }
                return;
            }

            if (distance > PROXIMITY_THRESHOLD) {

                switch (direction) {
                    case N:
                        this.restoreSpeed();
                        break;
                    case S:
                        car.restoreSpeed();
                        break;
                    case E:
                        car.restoreSpeed();
                        break;
                    case W:
                        this.restoreSpeed();
                        break;
                    case NE:
                        this.restoreSpeed();
                        break;
                    case NW:
                        car.restoreSpeed();
                        break;
                    case SE:
                        car.restoreSpeed();
                        break;
                    case SW:
                        this.restoreSpeed();
                        break;
                    default:
                }
                return;
            }

            if (distance < -PROXIMITY_THRESHOLD) {
                switch (direction) {
                    case N:
                        car.restoreSpeed();
                        break;
                    case S:
                        this.restoreSpeed();
                        break;
                    case E:
                        this.restoreSpeed();
                        break;
                    case W:
                        car.restoreSpeed();
                        break;
                    case NE:
                        car.restoreSpeed();
                        break;
                    case NW:
                        this.restoreSpeed();
                        break;
                    case SE:
                        this.restoreSpeed();
                        break;
                    case SW:
                        car.restoreSpeed();
                        break;
                    default:
                }

            }

        });
    }

    private static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public enum Direction {
        N, W, E, S, NE, NW, SE, SW, D // D here signifies that the car is not moving 
    }
}
