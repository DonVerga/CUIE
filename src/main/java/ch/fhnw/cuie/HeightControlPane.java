package ch.fhnw.cuie;

import javafx.geometry.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class HeightControlPane extends Region {
    private Slider slider;
    private Label feet , meter;
    private double test;
    private double height = 32;
    private double possitioning = PREFERRED_SIZE - height;
    private Pane drawingPane,drawingPaneSlider,dummyBuildPain,masterPain;
    private static final double PREFERRED_SIZE = 300;
    private static final double MINIMUM_SIZE   = 150;
    private static final double MAXIMUM_SIZE   = 600;

    public HeightControlPane() {
        initialSelf();
        initializeControls();
        layoutControls();
        addValueChangeListeners();
        addBindings();
    }

    private void initialSelf() {
        String fonts = getClass().getResource("font.css").toExternalForm();
        getStylesheets().add(fonts);

        String stylesheet = getClass().getResource("style.css").toExternalForm();
        getStylesheets().add(stylesheet);

        Insets padding           = getPadding();
        double verticalPadding   = padding.getTop() + padding.getBottom();
        double horizontalPadding = padding.getLeft() + padding.getRight();
        setMinSize(MINIMUM_SIZE + horizontalPadding, MINIMUM_SIZE + verticalPadding);
        setPrefSize(PREFERRED_SIZE + horizontalPadding, PREFERRED_SIZE + verticalPadding);
        setMaxSize(MAXIMUM_SIZE + horizontalPadding, MAXIMUM_SIZE + verticalPadding);
    }
    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        resize();
    }
    private void resize() {
        double width = getWidth() - getInsets().getLeft() - getInsets().getRight();
        double height = getHeight() - getInsets().getTop() - getInsets().getBottom();
        double size = Math.max(Math.min(Math.min(width, height), MAXIMUM_SIZE), MINIMUM_SIZE);

        double scalingFactor = size / PREFERRED_SIZE;

        if (width > 0 && height > 0) {
            masterPain.relocate((getWidth() - PREFERRED_SIZE) * 0.5, (getHeight() - PREFERRED_SIZE) * 0.5);
            masterPain.setScaleX(scalingFactor);
            masterPain.setScaleY(scalingFactor);

        }
    }
    private void initializeControls() {
        slider = new Slider();
        feet = new Label("feet");
        meter = new Label("meter");
        drawingPane = new Pane();
        drawingPaneSlider = new Pane();
        masterPain = new Pane();
        dummyBuildPain = new Pane();



    }

//test
    private void layoutControls() {

        drawingPane.getStyleClass().addAll("drawingPane");
        drawingPaneSlider.getStyleClass().addAll("drawingPaneSlider");
        dummyBuildPain.getStyleClass().addAll("dummyBuild");
        slider.setOrientation(Orientation.VERTICAL);
        slider.setPrefHeight(PREFERRED_SIZE);
        slider.setMinHeight(PREFERRED_SIZE);
        slider.setMajorTickUnit(200);
        slider.setMax(1000);
        slider.setShowTickLabels(true);
        slider.getStyleClass().addAll("Slider");
        drawingPaneSlider.getChildren().addAll(slider);
        dummyBuildPain.setPrefHeight(height);
        dummyBuildPain.setLayoutY(possitioning);
        dummyBuildPain.setLayoutX(150);
        dummyBuildPain.getStyleClass().addAll("dummyBuildPane");
        masterPain.getChildren().addAll(drawingPaneSlider,drawingPane,dummyBuildPain);
        drawingPane.relocate(40,180);
        masterPain.getStyleClass().addAll("master");
        getChildren().addAll(masterPain);

    }


    private void addValueChangeListeners() {

    }

    private void addBindings() {


    }

}
