package zen.controllers;

import zen.Start;
import zen.models.Board;
import zen.models.SingleplayerGame;
import zen.models.Status;
import zen.models.Settings;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.When;
import javafx.beans.property.ReadOnlyIntegerProperty;

public class SPGameController implements Initializable {
    @FXML private TilePane gridTp;
    @FXML private Label scoreLbl;
    @FXML private VBox gameOverVbx;

    private SingleplayerGame game;

    @Override
    public void initialize(URL url, ResourceBundle rsc) {
        game = new SingleplayerGame(Settings.getGridHeight() + 2, Settings.getGridWidth() + 2);
        // + 2 adds hidden outer layer around the grid (used when a snake goes out of bounds)
        setupGrid(); // Hardcoded 50 * 25 grid
        bindGridToBoard();
        setupArrowKeys();
        scoreLbl.textProperty().bind(game.getSnake(1).scoreProperty().asString("Score: %s"));
        gameOverVbx.visibleProperty().bind(game.statusProperty().isEqualTo(Status.DEAD));
    }

    public void newGame() { Start.setRoot("singleplayerGame"); }
    public void returnHome() { Start.setRoot("home"); }

    private void setupArrowKeys() {
        Start.addKeyPressedEventToScene(
            new ArrowKeysHandler(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT, game, game.getSnake(1))
        );
        Start.addKeyPressedEventToScene(
            new ArrowKeysHandler(KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D, game, game.getSnake(1))
        );
    }
    
    private void setupGrid() {
        gridTp.setMaxWidth(790); // No idea why it's not 780 pixels long (52 * 15), so I hardcoded lol
        gridTp.setMinWidth(790);
        gridTp.setMaxHeight(405);
        gridTp.setMinHeight(405);
        for (int i = 0; i < game.getBoard().getHeight() * game.getBoard().getWidth(); i++) {
            gridTp.getChildren().add(new Rectangle(15, 15));
        }
    }

    private void bindGridToBoard() {
        for (int y = 0; y < game.getBoard().getHeight(); y++) {
            for (int x = 0; x < game.getBoard().getWidth(); x++) {
                Rectangle rect = (Rectangle) gridTp.getChildren().get(y*game.getBoard().getWidth() + x);
                ReadOnlyIntegerProperty squareProperty = game.getBoard().squareProperty(y, x);
                ObjectBinding<Color> w1 = new When(squareProperty.isEqualTo(Board.EMPTYSQUARE)).then(Color.BLACK).otherwise(Color.GREEN);
                ObjectBinding<Color> w2 = new When(squareProperty.isEqualTo(Board.OUTOFBOUNDS)).then(Color.rgb(27, 27, 27)).otherwise(w1);
                ObjectBinding<Color> w3 = new When(squareProperty.isEqualTo(Board.DEAD)).then(Color.GREY).otherwise(w2);
                rect.fillProperty().bind(new When(squareProperty.isEqualTo(Board.APPLE)).then(Color.RED).otherwise(w3));
            }
        }
    }
}