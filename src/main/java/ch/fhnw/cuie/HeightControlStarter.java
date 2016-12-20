package ch.fhnw.cuie;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Created by aggre on 05.12.2016.
 */
public class HeightControlStarter extends Application{

        @Override
        public void start(Stage primaryStage) throws Exception {
        Region rootPanel = new HeightControlPane();
        Scene scene = new Scene(rootPanel);

        primaryStage.setTitle("Height Reference");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();

    }
}
