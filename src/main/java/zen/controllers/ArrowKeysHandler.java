package zen.controllers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import zen.models.Direction;
import zen.models.Game;
import zen.models.Snake;
import zen.models.Status;

public class ArrowKeysHandler implements EventHandler<KeyEvent> {
    private final Game game;
    private final Snake snake;
    private final KeyCode up;
    private final KeyCode down;
    private final KeyCode left;
    private final KeyCode right;

    public ArrowKeysHandler(KeyCode up, KeyCode down, KeyCode left, KeyCode right, Game game, Snake snake) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.snake = snake;
        this.game = game;
    }

    @Override
    public void handle(KeyEvent k) {
        Direction direction = Direction.NONE;
        if (k.getCode() == left) direction = Direction.LEFT;
        else if (k.getCode() == right) direction = Direction.RIGHT;
        else if (k.getCode() == up) direction = Direction.UP;
        else if (k.getCode() == down) direction = Direction.DOWN;
        else return;
        if (snake.getStatus() == Status.LIVE) {
            if (snake.getFutureDirection().isNotSet()) {
                if (snake.getCurrentDirection().isParallelTo(direction)) return;
                snake.setFutureDirection(direction); 
                return;
            }
            if (snake.getFutureDirection().isParallelTo(direction)) return;
            snake.setFutureFutureDirection(direction);
            return;
        }
        if (snake.getStatus() == Status.DEAD) return;
        snake.setCurrentDirection(direction);
        snake.setStatusToREADY();
        game.playIfReady();
    }
    
}
