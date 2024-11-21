package zen;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.When;
import javafx.beans.property.ReadOnlyIntegerProperty;

public class GameController implements Initializable {
    @FXML private TilePane gridTp;

    private Game game;

    @Override
    public void initialize(URL url, ResourceBundle rsc) {
        game = new Game(Settings.getGridHeight(), Settings.getGridWidth()); 
        setupGrid(); // Hardcoded 50 * 25 grid
        bindGridToBoard();
        Start.addKeyPressedEventToScene(k -> {
            if (k.getCode() == KeyCode.LEFT) game.setDirection(Direction.LEFT);
            else if (k.getCode() == KeyCode.RIGHT) game.setDirection(Direction.RIGHT);
            else if (k.getCode() == KeyCode.UP) game.setDirection(Direction.UP);
            else if (k.getCode() == KeyCode.DOWN) game.setDirection(Direction.DOWN);
        });
    }

    // No idea why it's not 750 pixels long (50 * 15), so I hardcoded lol
    private void setupGrid() {
        gridTp.setMaxWidth(760);
        gridTp.setMinWidth(760);
        gridTp.setMaxHeight(375);
        gridTp.setMinHeight(375);
        for (int i = 0; i < game.getBoardHeight() * game.getBoardWidth(); i++) {
            gridTp.getChildren().add(new Rectangle(15, 15));
        }
    }

    private void bindGridToBoard() {
        for (int y = 0; y < game.getBoardHeight(); y++) {
            for (int x = 0; x < game.getBoardWidth(); x++) {
                Rectangle rect = (Rectangle) gridTp.getChildren().get(y*game.getBoardWidth() + x);
                ReadOnlyIntegerProperty squareProperty = game.squareProperty(y, x);
                ObjectBinding<Color> w1 = new When(squareProperty.isEqualTo(0)).then(Color.BLACK).otherwise(Color.GREEN);
                rect.fillProperty().bind(new When(squareProperty.greaterThanOrEqualTo(0)).then(w1).otherwise(Color.RED));
            }
        }
    }
}