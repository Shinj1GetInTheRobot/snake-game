package zen.models;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiplayerGame implements Game {
    private final List<Snake> snakes;
    private final List<Snake> liveSnakes;
    private final Board board;
    private final GameTimer timer;
    private final SimpleObjectProperty<Status> status;

    public MultiplayerGame(int y, int x, int players) {
        board = new Board(y, x);
        snakes = Snake.createNewSnakes(players, Settings.getGrowth(), board);
        for (Snake snake : snakes) board.setSquare(snake.getHead(), snake.id);
        board.setRandApples(players);
        timer = new GameTimer(this, Settings.getSpeed());
        status = new SimpleObjectProperty<Status>(Status.READY);
        liveSnakes = new ArrayList<Snake>();
    }

    public void playIfReady() {
        if (!allSnakesAreReady()) return;
        status.set(Status.LIVE);
        for (Snake snake : snakes) {
            snake.setStatusToLIVE();
            liveSnakes.add(snake);
        }
        timer.start();
    }
    
    public void nextFrame() {
        for (Snake snake : liveSnakes) snake.stretchForward(); // Note: Snake head moves forward, but tail does not.
        Iterator<Snake> snakeIterator = liveSnakes.iterator();
        while (snakeIterator.hasNext()) {
            Snake snake = snakeIterator.next();
            if (killSnakeIfDeserveIt(snake)) {
                snake.moveDeadOnBoard();
                snakeIterator.remove();
            }
            else if (snake.ateApple()) {
                snake.moveLiveOnBoard();
                snake.incrGrowFactor(Settings.getGrowth());
                snake.incrScore();
                board.setRandApple();
            }
            else snake.moveLiveOnBoard();
        }
        if (liveSnakes.size() <= 1) {
            timer.stop();
            status.set(Status.DEAD);
        }
    }

    // Returns true if snake was killed
    private boolean killSnakeIfDeserveIt(Snake snake1) {
        if (snake1.wentOffBoard()) {
            snake1.kill();
            return true;
        }
        for (Snake snake2 : snakes) {
            if (snake1.ranIntoSnakeIgnoreTail(snake2)) {
                snake1.kill();
                return true;
            }
        }
        return false;
    }

    private boolean allSnakesAreReady() {
        for (Snake snake : snakes) if (!snake.isReady()) return false;
        return true;
    }
    
    public ReadOnlyObjectProperty<Status> statusProperty() { return status; }

    public Board getBoard() { return board; }
    public Status getStatus() { return status.get(); }
    public Snake getSnake(int id) { return snakes.get(id-1); }
}
