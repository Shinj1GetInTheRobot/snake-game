package zen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class HomeController implements Initializable {
    @FXML private Button playBtn;
    @FXML private Button howToPlayBtn;
    @FXML private Button settingsBtn;

    @Override
    public void initialize(URL url, ResourceBundle rsc) {
        playBtn.setOnAction(EventHandler -> switchToScene("game"));
        howToPlayBtn.setOnAction(EventHandler -> switchToScene("howToPlay"));
        settingsBtn.setOnAction(EventHandler -> switchToScene("settings"));
    }

    private void switchToScene(String sceneName) {
        try { Start.setRoot(sceneName); }
        catch (IOException e) { System.out.println(e.getMessage()); }
    }
}
