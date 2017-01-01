package ch.fhnw.cuie;

import ch.fhnw.cuie.buildings.BuildingPM;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.util.StringConverter;


public class HeightControlPane extends Region {

    // *** Elemente des Custom Control für Height: *** //
    private Label feet;
    private Label meter;
    private TextField tfFeet;
    private TextField tfMeter;
    private String calcMtoFt; // Hilfsvariable für Umrechnung Meter to Feet
    private Slider slider;
    private Pane drawingPaneEifeli, drawingPaneSlider, dummyBuildPane, masterPane;
    private double pmeter;
    private double heightRect = 50;

    private static final double PREFERRED_SIZE = 300;
    private static final double MINIMUM_SIZE = 150;
    private static final double MAXIMUM_SIZE = 600;

    private double positioning = PREFERRED_SIZE - heightRect;

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

    private String calculateMtoFt(double pmeter){
        double ftValue = pmeter*3.28084;
        calcMtoFt = String.valueOf(ftValue);
        return calcMtoFt;
    }

    private void initializeControls() {
        slider = new Slider();
        feet = new Label("ft");
        meter = new Label("m");
        tfFeet = new TextField(calcMtoFt);
        tfMeter = new TextField();
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
        slider.setShowTickLabels(false); // false= keine Anzeige der Ticks

        dummyBuildPane.setLayoutX(150);
        drawingPaneEifeli.relocate(40, 230);
        feet.setLayoutX(125); // todo setLayoutY im changeListener, wenns mit der Slider-Höhe analog sein soll!
        meter.setLayoutX(85);
        tfFeet.setLayoutX(125);
        tfFeet.setLayoutY(50);
        tfMeter.setLayoutX(0);
        tfMeter.setPrefWidth(85);
        tfMeter.setLayoutY(50);

        drawingPaneSlider.getChildren().addAll(slider, feet, meter, tfFeet, tfMeter); // weil der Slider in einer Pane ist!
        masterPane.getChildren().addAll(drawingPaneSlider, drawingPaneEifeli, dummyBuildPane);
        getChildren().addAll(masterPane);
    }


    private void addValueChangeListeners() {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            dummyBuildPane.setLayoutY(PREFERRED_SIZE - (newValue.doubleValue() * 0.2));
        });
    }


    private void addBindings() {
        slider.valueProperty().bindBidirectional(pm.height_mProperty());
        dummyBuildPane.prefHeightProperty().bind(slider.valueProperty().multiply(0.2));

        // copy+paste von StandardFormPane
        StringConverter<Number> longStringConverter = new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return number.toString();
            }

            @Override
            public Number fromString(String string) {
                try {
                    return Long.valueOf(string);
                }
                catch (NumberFormatException ex){
                    return 0L;
                }
            }
        };
        StringConverter<Number> integerStringConverter = new StringConverter<Number>() {
            @Override
            public String toString(Number n) {
                return n.toString();
            }

            @Override
            public Number fromString(String string) {
                try {
                    return Integer.valueOf(string);
                }
                catch (NumberFormatException ex){
                    return 0;
                }
            }
        };

        StringConverter<Number> doubleStringConverter = new StringConverter<Number>() {
            @Override
            public String toString(Number n) {
                return n.toString();
            }

            @Override
            public Number fromString(String string) {
                try {
                    return Double.valueOf(string);
                }
                catch (NumberFormatException ex){
                    return 0.0;
                }
            }
        };
        // damit Binding zum TextField Meter klappt
        Bindings.bindBidirectional(tfMeter.textProperty(), pm.height_mProperty(), doubleStringConverter);
        tfMeter.textProperty().bindBidirectional(tfFeet.textProperty());
    }


    // Getter und Setter
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

    public Label getFeet() {
        return feet;
    }

    public void setFeet(Label feet) {
        this.feet = feet;
    }

    public Label getMeter() {
        return meter;
    }

    public void setMeter(Label meter) {
        this.meter = meter;
    }

    public TextField getTfFeet() {
        return tfFeet;
    }

    public void setTfFeet(TextField tfFeet) {
        this.tfFeet = tfFeet;
    }

    public TextField getTfMeter() {
        return tfMeter;
    }

    public void setTfMeter(TextField tfMeter) {
        this.tfMeter = tfMeter;
    }
}
