package zen.models;

import javafx.beans.property.*;

public class SingleplayerGame implements Game {
    private Snake snake;
    private Board board;
    private GameTimer timer;
    private BooleanProperty gameOver;

    public SingleplayerGame(int y, int x) {
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
        snake.stretchForward(); // Note: Snake head moves forward, but tail does not.
        if (snake.wentOffBoard() || snake.ranIntoSelfIgnoreTail()) snake.kill();
        if (snake.isDead()) {
            snake.moveDeadOnBoard();
            timer.stop();
            gameOver.set(true);
            return;
        }
        if (snake.ateApple()) {
            snake.incrGrowFactor(Settings.getGrowth());
            snake.incrScore();
            snake.moveLiveOnBoard();
            board.setRandApple();
            return;
        }
        snake.moveLiveOnBoard();
    }
    

    public ReadOnlyIntegerProperty scoreProperty() { return snake.scoreProperty(); }
    public ReadOnlyIntegerProperty squareProperty(int y, int x) { return board.squareProperty(y, x); }
    public ReadOnlyBooleanProperty gameOverProperty() { return gameOver; }

    public boolean currentDirectionIs(Direction direction) { return snake.getCurrentDirection() == direction; }
    public boolean futureDirectionIs(Direction direction) {return snake.getFutureDirection() == direction; }
    public boolean futureFutureDirectionIs(Direction direction) {return snake.getFutureFutureDirection() == direction; }
    public void setCurrentDirection(Direction direction) { snake.setCurrentDirection(direction); }
    public void setFutureDirection(Direction direction) { snake.setFutureDirection(direction); }
    public void setFutureFutureDirection(Direction direction) { snake.setFutureFutureDirection(direction); }


    public int getBoardHeight() { return board.getBoardHeight(); }
    public int getBoardWidth() { return board.getBoardWidth(); }
    public int getScore() { return snake.getScore(); }
    public boolean isPlaying() { return timer.isRunning(); }
    public boolean isGameOver() { return gameOver.get(); }
}
