/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author User01
 */
public class Street {

    /**
     * Initializes the controller class.
     */
    private final double width;
    private final double height;
    private final double x;
    private final double y;
    private final String name;
    private final double speed;
    

    public Street(Canvas canvas,double x, double y, double width, double height, String name, double allowedSpeed) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = allowedSpeed;
        GraphicsContext gc = canvas.getGraphicsContext2D();
       
        fillColor(gc,x,y,width,height,Color.BLACK);
        this.name = name;

        if (height > width) {
            
            fillColor(gc,x + width/2,y,10,height,Color.WHITE);
        } else if (width > height) {
            
            fillColor(gc,x,y+height/2,width,10,Color.WHITE);
        }

        System.out.println(" I am printing the road ");
    }
    
    private void fillColor(GraphicsContext gc,double x,double y,double width,double height,Paint color){
            gc.setFill(color);
            gc.fillRect(x, y, width, height);
            gc.strokeRect(x, y, width, height);
    }

    

    public double getLaneX1() {
        return x;
    }

    public double getLaneY1() {
        return y;
    }
    
    public double getLaneX2() {
        
        return x;
    }

    public double getLaneY2() {
        if (height > width) {
            
            return x;
        } else if (width > height) {
            
            
        }
        return y + height;
    }
    
    

    public double getReverseLaneX1() {
        return x;
    }

    public double getReverseLaneY1() {
        return y;
    }
    
    public double getReverseLaneX2() {
        return x + width;
    }

    public double getReverseLaneY2() {
        return y + height;
    }

    public double getSpeed() {
        return this.speed;
    }

    public String getName() {
        return name;
    }

}
