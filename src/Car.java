
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

//    private final Pane pane;
    private ImageView car;
    private GraphPath<String,DefaultEdge> route;
   //expose the list of all cars

    public Car(GraphPath<String,DefaultEdge> route) {
        car = new ImageView(new Image(getClass().getResourceAsStream("img/red.png")));
        car.setFitHeight(20);
        car.setPreserveRatio(true);
this.route = route;
        
        
        // get the closest Traffic Light group and pick one of the lights depending on direction
    }
    
    // listener to get if the light is green
    // it true play
    // if false stop

    public ImageView getCar(){
    
        return car;
    }
    
    public List getVertexList(){
        return route.getVertexList();
    }
    
    public List getEdgeList(){
        return route.getEdgeList();
    }
    public void stop(){}
    public void start(){}
    

}
