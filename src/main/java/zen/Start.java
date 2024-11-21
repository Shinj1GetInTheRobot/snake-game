package zen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
public class Start extends Application {

    private static Scene scene;

    @SuppressWarnings("exports")
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("home"), 960, 540);
        stage.setScene(scene);
        stage.setTitle("Snake Game");
        stage.getIcons().add(new Image(Start.class.getResourceAsStream("snake_icon.png")));
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}