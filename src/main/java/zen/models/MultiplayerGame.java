package zen.models;

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
        snake = new Snake(Settings.getGrowth(), board.newRandomEmptySquare());
        board.setSnakeHead(snake);
        board.setRandApple();
        timer = new GameTimer(this, Settings.getSpeed());
        gameOver = new SimpleBooleanProperty(false);
    }

    public void play() {
        timer.start();
    }

    public boolean isPlaying() {
        return timer.isRunning();
    }

    public ReadOnlyBooleanProperty gameOverProperty() { return gameOver; }
    public boolean isGameOver() { return gameOver.get(); }

    public void nextFrame() {
        try {
            snake.move();
            int squareMovedVal = board.getValue(snake.getHead());
            // Throws IndexOutOfBoundsException if snake head ran off board
            if (squareMovedVal == 0) {} // Square moved was empty -> Skip
            else if (squareMovedVal > 0) { throw new Exception(); } // Snake hit itself
            else if (squareMovedVal == -1) { // Snake consumed an apple
                board.adjPositiveBoardValues(Settings.getGrowth());
                snake.incrLength(Settings.getGrowth());
                board.setRandApple();
                snake.incrScore();
            }
            else {} // Add extra functionalities for new game elements here!
            board.adjPositiveBoardValues(-1);
            board.setSnakeHead(snake);
        }
        catch (Exception e) {
            timer.stop();
            gameOver.set(true);
        }
    }

    public ReadOnlyIntegerProperty scoreProperty() { return snake.scoreProperty(); }
    public int getScore() { return snake.getScore(); }

    public void setFutureDirection(Direction direction) { 
        snake.setFutureDirection(direction);
    }
    public boolean currentDirectionIs(Direction direction) {
        return snake.getCurrentDirection() == direction;
    }

    public ReadOnlyIntegerProperty squareProperty(int y, int x) {
        return board.squareProperty(y, x);
    }

    public int getBoardHeight() { return board.getBoardHeight(); }
    public int getBoardWidth() { return board.getBoardWidth(); }
}
