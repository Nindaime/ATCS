
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

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
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private boolean isGreen;// should be observable
    private boolean isYellow;
    private int numberOfCarsInSensorZone;// should be observable
    private Circle circle;

    private List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();

    public TrafficLight(double x, double y) {
        // these dimensions are not particularly for the traffic light but for 
        // the sensor zones.
        circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setFill(Color.BLUE);
        circle.setRadius(3);
        
        setLightColor("red");

    }

    public Circle getTrafficLight() {
        return circle;
    }

    public void setLightColor(String color) {

        Paint paint;

        if (null == color) {
            paint = Color.RED;
        } else {
            switch (color) {
                case "green":
                    paint = Color.GREEN;
                    break;
                case "yellow":
                    paint = Color.YELLOW;
                    break;
                case "red":
                    paint = Color.YELLOW;
                    break;
                default:
                    paint = Color.RED;
                    break;
            }
        }

        circle.setFill(paint);
    }

    public int getNumberCarsInSensorZone() {
        // get all cars from the main app

        // get cars in the dimension for the zone;
        // return the .number of cars in the zone
        // if 20seconds has past or number of cars in zone is zero
        // set isGreen to false
        return 1;
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

}
