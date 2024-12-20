package zen.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import zen.Start;

public class HomeController implements Initializable {
    @FXML private Button singleplayerBtn;
    @FXML private Button multiplayerBtn;
    @FXML private Button settingsBtn;

    @Override
    public void initialize(URL url, ResourceBundle rsc) {
        singleplayerBtn.setOnAction(EventHandler -> Start.setRoot("singleplayerGame"));
        multiplayerBtn.setOnAction(EventHandler -> Start.setRoot("multiplayerGame"));
        settingsBtn.setOnAction(EventHandler -> Start.setRoot("settings"));
    }
}
