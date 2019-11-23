
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
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

    private String name;
    private boolean isGreen;// should be observable

    private final Circle circle;
    private SensorArea sensorArea;

    public TrafficLight(double x, double y, TrafficLightDirection direction) {
        // these dimensions are not particularly for the traffic light but for 
        // the sensor zones.
        double newX = x;
        double newY = y;

        int distanceFromCenter = 7;
        switch (direction) {
            case NORTH:
                newY = y - distanceFromCenter;
                sensorArea = new SensorArea(newX - 25, newY - 60, 25, 50);
                name = "north";
                break;
            case SOUTH:
                newY = y + distanceFromCenter;
                sensorArea = new SensorArea(newX, newY - 10 + 24, 30, 50);
                name = "south";
                break;
            case EAST:
                newX = x + distanceFromCenter;
                sensorArea = new SensorArea(newX + 14, newY - 27, 40, 30);
                name = "east";
                break;
            case WEST:
                newX = x - distanceFromCenter;
                sensorArea = new SensorArea(newX - 70, newY + 8, 40, 25);
                name = "west";
                break;
        }
        circle = new Circle();
        circle.setCenterX(newX);
        circle.setCenterY(newY);
        circle.setFill(Color.BLUE);
        circle.setRadius(3);

    }

    public Circle getTrafficLight() {
        return circle;
    }

    public String getName() {
        return name;
    }

    public Rectangle getSensor() {

        return sensorArea;
    }

    private void startCar(ArrayList<Car> trafficCars) {

        ArrayList<Car> cars = new ArrayList<>();
        cars.addAll(trafficCars);

        cars.forEach((Car car) -> {

            if (car.isSequenceSet()) {
                var x = car.getX() ;
                var y = car.getY() ;

                boolean a = sensorArea.getLayoutX() < x && sensorArea.getLayoutY() < y
                        && sensorArea.getLayoutX() + sensorArea.getWidth() > x
                        && sensorArea.getLayoutY() + sensorArea.getHeight() > y;

                if (a && car.isStopped()) {

                    car.start();
                    isGreen = true;

                }
            }

        });
    }

    private void stopCar(ArrayList<Car> trafficCars) {

        ArrayList<Car> cars = new ArrayList<>();
//        cars.addAll(TrafficApp.cars);
        cars.addAll(trafficCars);

        cars.forEach((Car car) -> {

            if (car.isSequenceSet()) {
                var x = car.getX() ;
                var y = car.getY() ;

                boolean a = sensorArea.getLayoutX() < x && sensorArea.getLayoutY() < y
                        && sensorArea.getLayoutX() + sensorArea.getWidth() > x
                        && sensorArea.getLayoutY() + sensorArea.getHeight() > y;

                if (a && !car.isStopped()) {

                    car.stop();
                    isGreen = false;
                    System.out.println("Stopping car" + this.getName());

                }
            }

        });
    }

    public void setLightColor(String color, ArrayList<Car> trafficCars) {

        Paint paint;

            switch (color) {
                case "green":
                    paint = Color.GREEN;

                    startCar(trafficCars);
                    break;
                case "yellow":
                    paint = Color.YELLOW;

                    stopCar(trafficCars);
                    break;
                case "red":
                    paint = Color.RED;

                    stopCar(trafficCars);
                    break;
                default:
                    paint = Color.RED;

                    stopCar(trafficCars);
                    break;
            }
        

        circle.setFill(paint);
    }

    public int getNumberCarsInSensorZone(ArrayList<Car> c) {

        ArrayList<Car> cars = new ArrayList<>();
        cars.addAll(c);
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
                    if (!isGreen) {
                        car.stop();
                    }

                }
            }

        });
        return counter.get();
    }

    

    private class SensorArea extends Rectangle {
//        double x,y;

        public SensorArea(double x, double y, double width, double height) {
            this.setLayoutX(x);
            this.setLayoutY(y);
            this.setWidth(width);
            this.setHeight(height);
            this.setFill(Color.WHITE);
            this.setOpacity(0.15f);

        }

    }

}
