package zen.models;

import javafx.beans.property.*;

public class SingleplayerGame implements Game {
    private Snake snake;
    private Board board;
    private GameTimer timer;
    private SimpleObjectProperty<Status> status;

    public SingleplayerGame(int y, int x) {
        board = new Board(y, x);
        snake = new Snake(Settings.getGrowth(), 1, board);
        board.setSquare(snake.getHead(), snake.id);
        board.setRandApple();
        timer = new GameTimer(this, Settings.getSpeed());
        status = new SimpleObjectProperty<Status>(Status.READY);
    }

    public void playIfReady() {
        if (status.get() != Status.READY) return;
        status.set(Status.LIVE);
        snake.setStatusToLIVE();
        timer.start();
    }
    
    public void nextFrame() {
        snake.stretchForward(); // Note: Snake head moves forward, but tail does not.
        if (snake.wentOffBoard() || snake.ranIntoSelfIgnoreTail()) snake.kill();
        if (snake.isDead()) {
            timer.stop();
            snake.moveDeadOnBoard();
            status.set(Status.DEAD);
            return;
        }
        if (snake.ateApple()) {
            snake.moveLiveOnBoard();
            snake.incrGrowFactor(Settings.getGrowth());
            snake.incrScore();
            board.setRandApple();
            return;
        }
        snake.moveLiveOnBoard();
    }
    
    public ReadOnlyObjectProperty<Status> statusProperty() { return status; }

    public Board getBoard() { return board; }
    public Status getStatus() { return status.get(); }
    public Snake getSnake(int id) { return snake; } // Warning: Since single-player mode has only 1 snake, just return directly
}
