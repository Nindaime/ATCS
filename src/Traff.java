
import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//public class Traff extends Application {
//
//    
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        
//        
//        
//        Parent root = FXMLLoader.load(getClass().getResource("RoadFXML.fxml"));
//         
//        
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
//        
//        
//    }
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
public class Traff {

    // method to automatically add car to the road and attaching paths to them
    public static void main(String args[]) {
        //create TrafficLightGroup which are five;
        var t = new TrafficLightGroup(1,2);
        
    }

}
