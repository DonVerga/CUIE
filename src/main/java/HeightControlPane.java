

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class HeightControlPane extends VBox {
    private Slider slider;
    private Label feet , meter;
    private Canvas eifeli;
    private StackPane overlay;
    private Rectangle dummyBuilding;
    private HBox labelarrange;
    private HBox mid;
    private static final double PREFERRED_SIZE = 500;
    private static final double MINIMUM_SIZE   = 200;
    private static final double MAXIMUM_SIZE   = 800;

    public HeightControlPane() {
        initialSelfe();
        initializeControls();
        layoutControls();
        addValueChangeListeners();
        addBindings();
    }

    private void initialSelfe() {
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
            mid.relocate((getWidth() - PREFERRED_SIZE) * 0.5, (getHeight() - PREFERRED_SIZE) * 0.5);
            mid.setScaleX(scalingFactor);
            mid.setScaleY(scalingFactor);
        }
    }
    private void initializeControls() {
        slider = new Slider();
        feet = new Label("feet");
        meter = new Label("meter");
        eifeli = new ResizableCanvas();
        eifeli.setWidth(135);
        eifeli.setHeight(400);
        overlay = new StackPane();
        dummyBuilding = new Rectangle(150,200,Color.CORNFLOWERBLUE);
        labelarrange = new HBox();
        mid = new HBox();
    }


    private void layoutControls() {
        slider.setOrientation(Orientation.VERTICAL);
        slider.setMin(0);
        slider.setMax(1000);
        slider.setValue(100);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(200);
        slider.setMinHeight(600);
        slider.setMaxHeight(1000);
        overlay.getChildren().addAll(slider, dummyBuilding);
        overlay.setAlignment(Pos.BOTTOM_RIGHT);
        labelarrange.getChildren().addAll(meter,feet);
        mid.getChildren().addAll(eifeli,overlay);
        getChildren().addAll(labelarrange,mid);


    }


    private void addValueChangeListeners() {

    }

    private void addBindings() {


    }

}
