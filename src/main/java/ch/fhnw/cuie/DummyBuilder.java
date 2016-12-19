package ch.fhnw.cuie;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by aggre on 18.12.2016.
 */
public class DummyBuilder extends Canvas{

    public DummyBuilder(){
        draw();

    }
    public void draw(){

        Rectangle rectangle = new Rectangle(200,500);
        rectangle.setFill(Color.BLACK);
        rectangle.setX(0);
        rectangle.setY(0);
     /*   int y = 20;
        for(int i = 0 ; i < rectangle.getHeight(); i++ ){
            Rectangle window = new Rectangle(50,50);
            window.setFill(Color.WHITE);
            window.setX(20);
            Rectangle window2 = new Rectangle(50,50);
            window2.setFill(Color.WHITE);
            window2.setX(170);
            window2.setY(y);
            y = y+20;
        }*/
    }
}
