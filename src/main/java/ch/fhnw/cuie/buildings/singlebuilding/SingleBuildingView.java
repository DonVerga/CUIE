package ch.fhnw.cuie.buildings.singlebuilding;

import ch.fhnw.cuie.customControl4Height.HeightControlPane;
import ch.fhnw.cuie.buildings.BuildingPM;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * @author Dieter Holz
 */
public class SingleBuildingView extends VBox {
    private BuildingPM building;
    private Node customControlPane;
    private Node standardFormPane;
    private HeightControlPane custom;

    public SingleBuildingView(BuildingPM building) {
        this.building = building;
        initializeParts();
        layoutParts();

    }

    private void initializeParts() {
        customControlPane = new CustomControlPane(building);
        standardFormPane = new StandardFormPane(building);
        custom = new HeightControlPane(building);
    }

    private void layoutParts() {
        getChildren().addAll(custom,customControlPane, standardFormPane);
    }
}
