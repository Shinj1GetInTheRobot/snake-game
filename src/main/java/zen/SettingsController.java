package zen;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class SettingsController {

    @FXML private Slider speedSldr;
    @FXML private Slider growthSldr;
    @FXML private Button playBtn;
    @FXML private Button backBtn;

    @FXML
    private void initialize() {
        speedSldr.setValue(Settings.getSpeed());
        speedSldr.valueProperty().addListener((observable, oldValue, newValue) -> Settings.setSpeed((int)Math.round((double)newValue)));
        growthSldr.setValue(Settings.getGrowth());
        growthSldr.valueProperty().addListener((observable, oldValue, newValue) -> Settings.setGrowth((int)Math.round((double)newValue)));
        playBtn.setOnAction(EventHandler -> switchToScene("game"));
        backBtn.setOnAction(EventHandler -> switchToScene("home"));
    }

    private void switchToScene(String sceneName) {
        try { Start.setRoot(sceneName); }
        catch (IOException e) { System.out.println(e.getMessage()); }
    }
}
