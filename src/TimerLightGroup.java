

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TimerLightGroup implements TrafficLightGroup {

    private final ObservableList<TrafficLight> trafficLights;
    private final TrafficLight northLight;
    private final TrafficLight southLight;
    private final TrafficLight westLight;
    private final TrafficLight eastLight;
    private TrafficLight greenTrafficLight;
    private TrafficLight yellowTrafficLight;
    private final Timer timer;
    private static final long TIMER_WAIT_TIME = 10000;

    public TimerLightGroup(double x, double y) {
        timer = new Timer();
        northLight = new TrafficLight(x, y, TrafficLightDirection.NORTH);
        southLight = new TrafficLight(x, y, TrafficLightDirection.SOUTH);
        westLight = new TrafficLight(x, y, TrafficLightDirection.WEST);
        eastLight = new TrafficLight(x, y, TrafficLightDirection.EAST);

        trafficLights = FXCollections.observableArrayList();

        trafficLights.add(westLight);
        trafficLights.add(northLight);
        trafficLights.add(southLight);
        
        trafficLights.add(eastLight);
        

        eastLight.setLightColor("red",TrafficApp.cars2);
        northLight.setLightColor("red",TrafficApp.cars2);
        southLight.setLightColor("red",TrafficApp.cars2);
        westLight.setLightColor("green",TrafficApp.cars2);
        
        greenTrafficLight = westLight;
        
        
        
        TimerTask changeLight = new TimerTask() {

            AtomicInteger counter = new AtomicInteger();
            @Override
            public void run() {
                
                counter.compareAndSet(4, 0);
                
                yellowTrafficLight =  trafficLights.get(counter.getAndIncrement());
                yellowTrafficLight.setLightColor("yellow",TrafficApp.cars2);
                try {
                    TimeUnit.SECONDS.sleep(4);
                    greenTrafficLight.setLightColor("red",TrafficApp.cars2);
                    greenTrafficLight = yellowTrafficLight;
                    greenTrafficLight.setLightColor("green",TrafficApp.cars2);
                    
                    
                    
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(TimerLightGroup.class.getName()).log(Level.SEVERE, null, ex);
                }
                

            }
        };
        
        timer.scheduleAtFixedRate(changeLight, 0, TIMER_WAIT_TIME);
    }
    
    
    public void checkTrafficLight() throws InterruptedException {
        
        trafficLights.forEach(light->{
        light.getNumberCarsInSensorZone(TrafficApp.cars2);
        });
    }
    
    @Override
    public ObservableList<TrafficLight> getTrafficLights() {
        return trafficLights;
    }
    
    
    
    public void cancelTimer(){
        timer.cancel();
    }
}
