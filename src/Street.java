/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author User01
 */
public class Street{

    /**
     * Initializes the controller class.
     */
    private final double width;
    private final double height;
    private final double x;
    private final double y;
    private final String name;
    
    public Street ( GraphicsContext gc,double x, double y, double width, double height, String name){
        
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        gc.setFill(Color.GREEN);
        gc.fillRect(x, y, width, height);
        gc.strokeRect(x, y, width, height);
        this.name = name;
        
        System.out.println(" I am printing the road ");
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    

    

    public String getName() {
        return name;
    }
    
}
