package ch.fhnw.cuie.buildings.singlebuilding;

import ch.fhnw.cuie.buildings.BuildingPM;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

/**
 * @author Dieter Holz
 */
public class CustomControlPane extends VBox {
    private final BuildingPM building;

    private Label height_mLabel;
    private Label buildingLabel;
    private Slider heigthSlider;

    public CustomControlPane(BuildingPM building) {
        this.building = building;
        initializeSelf();
        initializeParts();
        layoutParts();
        setupBindings();
    }

    private void initializeSelf() {
        setPadding(new Insets(10));

    }

    private void initializeParts() {
        buildingLabel = new Label();
        buildingLabel.setStyle("-fx-font-size: 32;");

        height_mLabel = new Label();
        height_mLabel.setStyle("-fx-font-size: 32;");

        heigthSlider = new Slider (0,1500,0);
    }

    private void layoutParts() {
        getChildren().addAll(buildingLabel, height_mLabel, heigthSlider);
    }

    private void setupBindings() {
        buildingLabel.textProperty().bind(building.buildingProperty());
        height_mLabel.textProperty().bind(building.height_mProperty().asString());

        heigthSlider.valueProperty().bindBidirectional(building.height_mProperty());

    }
}
