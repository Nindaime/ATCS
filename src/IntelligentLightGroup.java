
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User01
 */
public class IntelligentLightGroup implements PropertyChangeListener,TrafficLightGroup {

    private final ObservableList<TrafficLight> trafficLights;
    private final TrafficLight northLight;
    private final TrafficLight southLight;
    private final TrafficLight westLight;
    private final TrafficLight eastLight;
    private TrafficLight greenTrafficLight;
    private TrafficLight yellowTrafficLight;

    public IntelligentLightGroup(double x, double y) {

        northLight = new TrafficLight(x, y, TrafficLightDirection.NORTH);
        southLight = new TrafficLight(x, y, TrafficLightDirection.SOUTH);
        westLight = new TrafficLight(x, y, TrafficLightDirection.WEST);
        eastLight = new TrafficLight(x, y, TrafficLightDirection.EAST);

        trafficLights = FXCollections.observableArrayList();

        trafficLights.add(northLight);
        trafficLights.add(southLight);
        trafficLights.add(westLight);
        trafficLights.add(eastLight);
        
         trafficLights.forEach(e -> {
            e.setLightColor("green",TrafficApp.cars2);
        });

    }

    

    private void makeChange() {
        trafficLights.forEach(e -> {
            e.setLightColor("red",TrafficApp.cars);
        });
//        greenTrafficLight.setIsGreen(true);
        greenTrafficLight.setLightColor("green",TrafficApp.cars);
    }

    public void checkTrafficLight() throws InterruptedException {
        var allCars = TrafficApp.cars;
        

        if (greenTrafficLight != null && greenTrafficLight.getNumberCarsInSensorZone(allCars) <= 2) {

            TrafficLight element = trafficLights.stream()
                    .max((t1, t2) -> t1.getNumberCarsInSensorZone(allCars) - t2.getNumberCarsInSensorZone(allCars))
                    .get();

            if (element == greenTrafficLight) {
                return;
            }

            if (element.getNumberCarsInSensorZone(allCars) > 0) {
                yellowTrafficLight = element;
                yellowTrafficLight.setLightColor("yellow",TrafficApp.cars);
                TimeUnit.SECONDS.sleep(3);

                greenTrafficLight = yellowTrafficLight;
//                System.out.println("name of the element 1: " + element.getName());
                makeChange();
            }

            return;
        }

        TrafficLight element = trafficLights.stream()
                .max((t1, t2) -> t1.getNumberCarsInSensorZone(allCars) - t2.getNumberCarsInSensorZone(allCars))
                .get();

        if (element.getNumberCarsInSensorZone(allCars) > 0) {
//            TimeUnit.SECONDS.sleep(2);
//            System.out.println("name of the element 2: " + element.getName());
            greenTrafficLight = element;
            makeChange();
        }

//            System.out.println("Changed Traffic light");
//        System.out.println("No changed detected");
    }

    @Override
    public ObservableList<TrafficLight> getTrafficLights() {
        return trafficLights;
    }

    // observe all the cars that are in each zone after after isGreen
    public void setTrafficLightToGreen() {
    }

    public void setTrafficLightToYellow() {
    }

    public void getTrafficLightForCars(String direction) {
        // if the direction is from one place then set to that traffic light
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {

        String propertyName = e.getPropertyName();
        if ("isGreen".equals(propertyName)) {
            System.out.println("one of the values has changed" + e.getNewValue());

        } else if ("numberOfCarsInSensorZone".equals(propertyName)) {
        }

    }

}
