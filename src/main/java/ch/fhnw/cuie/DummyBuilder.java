package ch.fhnw.cuie;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by aggre on 18.12.2016.
 */
public class DummyBuilder extends Pane{

    private double height;
    private    Rectangle rectangle;


    @Override
    public void setHeight(double height) {
        this.height = height;
    }

    public DummyBuilder(double height){
    this.height = height;
    }
    public Rectangle draw(){

     rectangle = new Rectangle(100,height);
        rectangle.setFill(Color.BLACK);

        return rectangle;
    }

}
