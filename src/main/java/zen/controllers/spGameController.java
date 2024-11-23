package zen.controllers;

import java.io.IOException;
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
import zen.Start;
import zen.models.Direction;
import zen.models.Game;
import zen.models.Settings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.When;
import javafx.beans.property.ReadOnlyIntegerProperty;

public class spGameController implements Initializable {
    @FXML private TilePane gridTp;
    @FXML private Label scoreLbl;
    @FXML private VBox gameOverVbx;

    private Game game;

    @Override
    public void initialize(URL url, ResourceBundle rsc) {
        game = new Game(Settings.getGridHeight(), Settings.getGridWidth()); 
        setupGrid(); // Hardcoded 50 * 25 grid
        bindGridToBoard();
        setupArrowKeys();
        scoreLbl.textProperty().bind(game.scoreProperty().asString("Score: %s"));
        gameOverVbx.visibleProperty().bind(new When(game.gameOverProperty()).then(true).otherwise(false));
    }

    public void newGame() { loadScene("game"); }
    public void returnHome() { loadScene("home"); }

    private void loadScene(String sceneName) {
        try { Start.setRoot(sceneName); }
        catch (IOException e) { System.out.println(e.getMessage()); }
    }

    private void setupArrowKeys() {
        Start.addKeyPressedEventToScene(k -> {
            if (k.getCode() == KeyCode.LEFT && !game.currentDirectionIs(Direction.RIGHT)) game.setFutureDirection(Direction.LEFT);
            else if (k.getCode() == KeyCode.RIGHT && !game.currentDirectionIs(Direction.LEFT)) game.setFutureDirection(Direction.RIGHT);
            else if (k.getCode() == KeyCode.UP && !game.currentDirectionIs(Direction.DOWN)) game.setFutureDirection(Direction.UP);
            else if (k.getCode() == KeyCode.DOWN && !game.currentDirectionIs(Direction.UP)) game.setFutureDirection(Direction.DOWN);
            if (!game.isPlaying() && !game.isGameOver()) game.play();
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

    public Game getGame() { return game; }
}