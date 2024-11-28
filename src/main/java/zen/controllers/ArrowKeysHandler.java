package zen.controllers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import zen.models.Direction;
import zen.models.Game;

public class ArrowKeysHandler implements EventHandler<KeyEvent> {
    private final Game game;
    private final KeyCode up;
    private final KeyCode down;
    private final KeyCode left;
    private final KeyCode right;

    public ArrowKeysHandler(KeyCode up, KeyCode down, KeyCode left, KeyCode right, Game game) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.game = game;
    }

    @Override
    public void handle(KeyEvent k) {
        Direction direction = Direction.NONE;
        if (k.getCode() == left) direction = Direction.LEFT;
        else if (k.getCode() == right) direction = Direction.RIGHT;
        else if (k.getCode() == up) direction = Direction.UP;
        else if (k.getCode() == down) direction = Direction.DOWN;
        if (game.isPlaying()) {
            if (game.futureDirectionIs(Direction.NONE)) {
                if (game.currentDirectionIs(Direction.getOpposite(direction))) return;
                game.setFutureDirection(direction);
                return;
            }
            if (game.futureDirectionIs(Direction.getOpposite(direction))) return; 
            game.setFutureFutureDirection(direction);
            return;
        }
        if (game.isGameOver()) return;
        game.setCurrentDirection(direction);
        game.play();
    }
    
}
