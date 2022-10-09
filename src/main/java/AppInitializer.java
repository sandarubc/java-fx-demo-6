import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        URL resource = this.getClass().getResource("/view/TableForm.fxml");
        Scene scene = new Scene(FXMLLoader.load(resource));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Table Demo");
        primaryStage.centerOnScreen();
        primaryStage.show();

    }
}
