
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User01
 */
public class TrafficLight {

    private String isOn;
    private String name;
    private boolean isGreen;// should be observable
    private boolean isYellow;
    private int numberOfCarsInSensorZone;// should be observable
    private final Circle circle;
    private SensorArea sensorArea;

    private List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();

    public TrafficLight(double x, double y, TrafficLightDirection direction) {
        // these dimensions are not particularly for the traffic light but for 
        // the sensor zones.
        double newX = x;
        double newY = y;

        int distanceFromCenter = 7;
        switch (direction) {
            case NORTH:
                newY = y - distanceFromCenter;
                sensorArea = new SensorArea(newX - 30, newY - 60, 25, 40);
                name = "north";
                break;
            case SOUTH:
                newY = y + distanceFromCenter;
                sensorArea = new SensorArea(newX, newY + 24, 30, 40);
                name = "south";
                break;
            case EAST:
                newX = x + distanceFromCenter;
                sensorArea = new SensorArea(newX + 20, newY - 27, 40, 30);
                name = "east";
                break;
            case WEST:
                newX = x - distanceFromCenter;
                sensorArea = new SensorArea(newX - 60, newY - 4, 40, 25);
                name = "west";
                break;
        }
        circle = new Circle();
        circle.setCenterX(newX);
        circle.setCenterY(newY);
        circle.setFill(Color.BLUE);
        circle.setRadius(3);

        setLightColor("red");

    }

    public Circle getTrafficLight() {
        return circle;
    }
    
    public String getName(){
        return name;
    }

    public Rectangle getSensor() {

        return sensorArea;
    }

    private void startCar() {

        ArrayList<Car> cars = new ArrayList<>();
        cars.addAll(TrafficApp.cars);

        cars.forEach((Car car) -> {

            if (car.isSequenceSet()) {
                var x = car.getX() + car.getWidth() / 2;
                var y = car.getY() + car.getHeight() / 2;

                boolean a = sensorArea.getLayoutX() < x && sensorArea.getLayoutY() < y
                        && sensorArea.getLayoutX() + sensorArea.getWidth() > x
                        && sensorArea.getLayoutY() + sensorArea.getHeight() > y;

                if (a) {

                    car.start();

                }
            }

        });
    }
    
    private void stopCar() {

        ArrayList<Car> cars = new ArrayList<>();
        cars.addAll(TrafficApp.cars);

        cars.forEach((Car car) -> {

            if (car.isSequenceSet()) {
                var x = car.getX() + car.getWidth() / 2;
                var y = car.getY() + car.getHeight() / 2;

                boolean a = sensorArea.getLayoutX() < x && sensorArea.getLayoutY() < y
                        && sensorArea.getLayoutX() + sensorArea.getWidth() > x
                        && sensorArea.getLayoutY() + sensorArea.getHeight() > y;

                if (a) {

                    car.stop();

                }
            }

        });
    }

    public void setLightColor(String color) {

        Paint paint;

        if (null == color) {
            paint = Color.RED;
        } else {
            switch (color) {
                case "green":
                    paint = Color.BLUE;
                    isGreen = true;
                    startCar();
                    break;
                case "yellow":
                    paint = Color.YELLOW;
                    isGreen = false;
                    stopCar();
                    break;
                case "red":
                    paint = Color.RED;
                    isGreen = false;
                    stopCar();
                    break;
                default:
                    paint = Color.RED;
                    isGreen = false;
                    stopCar();
                    break;
            }
        }

        circle.setFill(paint);
    }

    public void amaobi() {

        System.out.println("sensor x" + sensorArea.getLayoutX());
        System.out.println("sensor y" + sensorArea.getLayoutY());
        System.out.println("sensor long" + (sensorArea.getLayoutX() + sensorArea.getWidth()));
        System.out.println("sensor tall" + (sensorArea.getLayoutY() + sensorArea.getHeight()) + "\n\n");
    }

    public int getNumberCarsInSensorZone() {
//        System.out.println(" get the number of cars in sensor zone");
        // get all cars from the main app

        // get cars in the dimension for the zone;
        // return the .number of cars in the zone
        // if 20seconds has past or number of cars in zone is zero
        // set isGreen to false
        ArrayList<Car> cars = new ArrayList<>();
        cars.addAll(TrafficApp.cars);
        AtomicInteger counter = new AtomicInteger();

        cars.forEach((Car car) -> {

            if (car.isSequenceSet()) {
                var x = car.getX() + car.getWidth() / 2;
                var y = car.getY() + car.getHeight() / 2;

                boolean a = sensorArea.getLayoutX() < x && sensorArea.getLayoutY() < y
                        && sensorArea.getLayoutX() + sensorArea.getWidth() > x
                        && sensorArea.getLayoutY() + sensorArea.getHeight() > y;

                if (a) {

                    counter.getAndIncrement();
                    System.out.println("found " + counter.get());
                }
            }

        });
        return counter.get();
    }

    public void setIsGreen(boolean value) {
        if (!value) {

            return;
        }
        notifyListeners("isGreen", this.isGreen, this.isGreen = value);

        while (value) {

            if (getNumberCarsInSensorZone() == 0) {
                setIsGreen(false);
                break;
            }

        }
    }

    public void setIsYellow(boolean value) {
        this.isYellow = value;

    }

    private void notifyListeners(String property, boolean oldValue, boolean newValue) {

        for (PropertyChangeListener name : listener) {
//            System.out.println("one of the values has changed");
            name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
        }
    }

    public void addChangeListener(PropertyChangeListener newListener) {
        listener.add(newListener);
    }

    private class SensorArea extends Rectangle {
//        double x,y;

        public SensorArea(double x, double y, double width, double height) {
            this.setLayoutX(x);
            this.setLayoutY(y);
            this.setWidth(width);
            this.setHeight(height);
            this.setFill(Color.BLACK);

        }

    }

}
