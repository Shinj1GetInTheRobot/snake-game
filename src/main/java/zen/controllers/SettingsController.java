package zen.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import zen.Start;
import zen.models.Settings;

public class SettingsController implements Initializable {

    @FXML private Slider speedSldr;
    @FXML private Slider growthSldr;
    @FXML private Button playBtn;
    @FXML private Button backBtn;

    @Override
    public void initialize(URL url, ResourceBundle rsc) {
        speedSldr.setValue(Settings.getSpeed());
        speedSldr.valueProperty().addListener((observable, oldValue, newValue) -> Settings.setSpeed((int)Math.round((double)newValue)));
        growthSldr.setValue(Settings.getGrowth());
        growthSldr.valueProperty().addListener((observable, oldValue, newValue) -> Settings.setGrowth((int)Math.round((double)newValue)));
        playBtn.setOnAction(EventHandler -> Start.setRoot("singleplayerGame"));
        backBtn.setOnAction(EventHandler -> Start.setRoot("home"));
    }

}
