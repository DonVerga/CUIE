package ch.fhnw.cuie.buildings.singlebuilding;

import ch.fhnw.cuie.buildings.BuildingPM;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SingleBuildingStarter extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent rootPanel = new SingleBuildingView(BuildingPM.getBuildings().get(0));

		Scene scene = new Scene(rootPanel);

		primaryStage.setTitle("Buildings");
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
