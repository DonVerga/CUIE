package ch.fhnw.cuie;

import ch.fhnw.cuie.buildings.BuildingPM;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;


public class HeightControlPane extends Region {

    // *** Elemente des Custom Control: *** //
    private Label feet, meter;
    private Slider slider;
    private Pane drawingPaneEifeli, drawingPaneSlider, dummyBuildPane, masterPane;
    private double pmeter;

    private double heightRect= 50;
    private double positioning = PREFERRED_SIZE - heightRect;
    private static final double PREFERRED_SIZE = 300;
    private static final double MINIMUM_SIZE = 150;
    private static final double MAXIMUM_SIZE = 600;

    private final BuildingPM pm;

    // Properties for binding:
    //private final DoubleProperty sliderValue = new SimpleDoubleProperty();
    private final DoubleProperty rectValue = new SimpleDoubleProperty();


    // Constructor:
    public HeightControlPane(BuildingPM pm) {
        this.pm = pm;
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

        // Resizing für Pane gem. Holz:
        Insets padding = getPadding();
        double verticalPadding = padding.getTop() + padding.getBottom();
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
            masterPane.relocate((getWidth() - PREFERRED_SIZE) * 0.5, (getHeight() - PREFERRED_SIZE) * 0.5);
            masterPane.setScaleX(scalingFactor);
            masterPane.setScaleY(scalingFactor);
        }
    }

    private void initializeControls() {
        slider = new Slider();
        feet = new Label("feet");
        meter = new Label("meter");
        drawingPaneEifeli = new Pane();
        drawingPaneSlider = new Pane();
        masterPane = new Pane();
        dummyBuildPane = new Pane();
        pmeter = pm.getHeight_m();
    }

    private void layoutControls() {
        // Verbindung zu style.css
        drawingPaneEifeli.getStyleClass().addAll("drawingPaneEifeli");
        drawingPaneSlider.getStyleClass().addAll("drawingPaneSlider");
        dummyBuildPane.getStyleClass().addAll("dummyBuild");
        dummyBuildPane.getStyleClass().addAll("dummyBuildPane");
        masterPane.getStyleClass().addAll("master");
        slider.getStyleClass().addAll("Slider");

        // Slider configurations:
        slider.setOrientation(Orientation.VERTICAL);
        slider.setPrefHeight(PREFERRED_SIZE);
        slider.setMinHeight(PREFERRED_SIZE);
        slider.setMajorTickUnit(200);
        slider.setMax(1500);
        slider.setShowTickLabels(false);

       // dummyBuildPane.setPrefHeight(heightRect);
      //  dummyBuildPane.setLayoutY(positioning); // damit das Dummy-Building nach oben wächst
        dummyBuildPane.setLayoutX(150);
        drawingPaneEifeli.relocate(40, 230);

        drawingPaneSlider.getChildren().addAll(slider); // weil der Slider in einer Pane ist!
        masterPane.getChildren().addAll(drawingPaneSlider, drawingPaneEifeli, dummyBuildPane);
        getChildren().addAll(masterPane);
    }


    private void addValueChangeListeners() {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            dummyBuildPane.setLayoutY(PREFERRED_SIZE - (newValue.doubleValue() *0.2));
        });

    }

    private void addBindings() {
    slider.valueProperty().bindBidirectional(pm.height_mProperty());
        dummyBuildPane.prefHeightProperty().bind(slider.valueProperty().multiply(0.2));
       // dummyBuildPane.layoutYProperty().bind(dummyBuildPane.prefHeightProperty());

    }

    public double getHeightRect() {
        return heightRect;
    }

    public void setHeightRect(double heightRect) {
        this.heightRect = heightRect;
    }

    public double getRectValue() {
        return rectValue.get();
    }

    public DoubleProperty rectValueProperty() {
        return rectValue;
    }

    public void setRectValue(double rectValue) {
        this.rectValue.set(rectValue);
    }
}
