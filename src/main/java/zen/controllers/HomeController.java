package zen.controllers;

import java.io.IOException;
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
        singleplayerBtn.setOnAction(EventHandler -> switchToScene("singleplayerGame"));
        multiplayerBtn.setOnAction(EventHandler -> switchToScene("multiplayerGame"));
        settingsBtn.setOnAction(EventHandler -> switchToScene("settings"));
    }

    private void switchToScene(String sceneName) {
        try { Start.setRoot(sceneName); }
        catch (IOException e) { e.printStackTrace(); }
    }
}
