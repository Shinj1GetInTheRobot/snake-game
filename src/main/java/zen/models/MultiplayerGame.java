package zen.models;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class MultiplayerGame implements Game {
    private Snake snake;
    private Board board;
    private GameTimer timer;
    private BooleanProperty gameOver;

    public MultiplayerGame(int y, int x) {
        board = new Board(y, x);
        snake = new Snake(Settings.getGrowth(), 1, board);
        board.setSquare(snake.getHead(), snake.id);
        board.setRandApple();
        timer = new GameTimer(this, Settings.getSpeed());
        gameOver = new SimpleBooleanProperty(false);
    }

    public void play() {
        timer.start();
    }
    
    public void nextFrame() {
        
    }

    

    public ReadOnlyIntegerProperty scoreProperty() { return snake.scoreProperty(); }
    public ReadOnlyIntegerProperty squareProperty(int y, int x) { return board.squareProperty(y, x); }
    public ReadOnlyBooleanProperty gameOverProperty() { return gameOver; }

    public boolean currentDirectionIs(Direction direction) { return snake.getCurrentDirection() == direction; }
    public boolean futureDirectionIs(Direction direction) {return snake.getFutureDirection() == direction; }
    public boolean futureFutureDirectionIs(Direction direction) {return snake.getFutureFutureDirection() == direction; }
    public void setFutureDirection(Direction direction) { snake.setFutureDirection(direction); }
    public void setFutureFutureDirection(Direction direction) { snake.setFutureFutureDirection(direction); }


    public int getBoardHeight() { return board.getBoardHeight(); }
    public int getBoardWidth() { return board.getBoardWidth(); }
    public int getScore() { return snake.getScore(); }
    public boolean isPlaying() { return timer.isRunning(); }
    public boolean isGameOver() { return gameOver.get(); }
}
