/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author User01
 */
public class RoadFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML Canvas canvas;
    @FXML Pane pane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // initialize streets
        Street street1 = new Street( gc, 400,0,40,300, "Alfred Riwani");
        Street street2 = new Street( gc, 0,350,350,40, "Saka Tinubu");
        Street street3 = new Street( gc, 500,350,300,40, "Ozumba Mbadiwe");
        Street street4 = new Street( gc, 400,450,40,300, "Adedeji Drive");
//        Street street5 = new Street( gc, 10,10,40,700, "Adeola Odeku");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CarFXML.fxml"));
//        loader.setLocation( getClass().getResource("RoadFXML.fxml"));
//        CarFXMLController car = new CarFXMLController();
//        loader.setController(car);
//        Parent root = loader.load();
          ArrayList<Street> route = new ArrayList<>();
          route.add(street1);
          route.add(street2);
          Car car = new Car( route );
          pane.getChildren().add( car.asPane());
          
          //intialize Cars
          //CarFXMLController car =
        
        
        
        

    }    
    
}
