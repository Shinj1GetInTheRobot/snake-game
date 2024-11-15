package zen;

import java.io.IOException;
import javafx.fxml.FXML;

public class GameController {

    @FXML
    private void switchToPrimary() throws IOException {
        Home.setRoot("home");
    }
}