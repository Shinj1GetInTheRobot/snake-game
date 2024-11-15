package zen;

import java.io.IOException;
import javafx.fxml.FXML;

public class HomeController {

    @FXML
    private void switchToSecondary() throws IOException {
        Home.setRoot("game");
    }
}
