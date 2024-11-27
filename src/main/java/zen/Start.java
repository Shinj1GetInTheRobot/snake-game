package zen;

import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
public class Start extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("home"), 960, 540);
        stage.setScene(scene);
        stage.setTitle("Snake Game");
        stage.getIcons().add(new Image(Start.class.getResourceAsStream("snake_icon.png")));
        stage.show();
    }

    public static void setRoot(String fxml) {
        try { scene.setRoot(loadFXML(fxml)); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public static void addKeyPressedEventToScene(EventHandler<KeyEvent> e) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, e);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}