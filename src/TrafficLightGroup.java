
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TrafficLightGroup implements PropertyChangeListener {
    
    private ObservableList<TrafficLight> trafficLights;
    private TrafficLight northLight;
    private TrafficLight southLight;
    private TrafficLight westLight;
    private TrafficLight eastLight;
    private TrafficLight greenTrafficLight;
    private TrafficLight yellowTrafficLight;
    
    public TrafficLightGroup(double x, double y) {
        
        northLight = new TrafficLight(x, y, x + 50, y + 50);
        southLight = new TrafficLight(x, y, x + 50, y + 50);
        westLight = new TrafficLight(x, y, x + 50, y + 50);
        eastLight = new TrafficLight(x, y, x + 50, y + 50);

//        this.trafficLights = FXCollections.observableList();
//        this.trafficLights.addListener( new ListChangeListener<TrafficLight> (){
//            
//            public void onChanged(Change <? extends TrafficLight> change){
//                System.out.println("this is the change that happened "+change.getList());
//            }
//        });
        northLight.addChangeListener(this);
        var a = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    northLight.setIsGreen(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TrafficLightGroup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        a.run();
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
            System.out.println("one of the values has changed"+e.getNewValue());
            
        } else if ("numberOfCarsInSensorZone".equals(propertyName)) {
        }
        
    }
    
}
