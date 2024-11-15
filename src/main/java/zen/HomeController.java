package zen;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {
    @FXML private Button playBtn;
    @FXML private Button howToPlayBtn;
    @FXML private Button settingsBtn;

    @FXML
    private void initialize() {
        playBtn.setOnAction(EventHandler -> switchToScene("game"));
        howToPlayBtn.setOnAction(EventHandler -> switchToScene("howToPlay"));
        settingsBtn.setOnAction(EventHandler -> switchToScene("settings"));
    }

    private void switchToScene(String sceneName) {
        try { Start.setRoot(sceneName); }
        catch (IOException e) { System.out.println(e.getMessage()); }
    }
}
