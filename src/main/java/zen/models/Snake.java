package zen.models;

import java.util.Deque;
import java.util.List;
import java.util.ArrayDeque;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Snake {
    private final Board board;
    public final int id;
    private final Deque<Square> squares;
    private int growFactor;
    private Direction currentDir;
    private Direction futureDir; // Where the snake will go in the next frame
    private Direction futureFutureDir; // Where the snake will go in 2 frames (for more responsive gameplay)
    private Status status;
    private IntegerProperty score;

    public static List<Snake> createNewSnakes(int total, int length, Board board) {
        List<Snake> snakes = new ArrayList<Snake>();
        for (int i = 1; i <= total; i++) snakes.add(new Snake(length, i, board));
        return snakes;
    }

    public Snake(int length, int id, Board board) {
        this.board = board;
        this.id = id;
        growFactor = length - 1;
        squares = new ArrayDeque<Square>();
        squares.add(board.newRandomEmptySquare());
        score = new SimpleIntegerProperty(0);
        currentDir = Direction.NONE;
        futureDir = Direction.NONE;
        futureFutureDir = Direction.NONE;
        status = Status.NOT_READY;
    }

    // Unsafe! Snake can move off the board or crash into object!
    // Note: Does not remove tail square!
    public void stretchForward() {
        updateDirections();
        addSquareAhead();
    }

    public void moveLiveOnBoard() {
        moveOnBoard(id);
    }
    public void moveDeadOnBoard() {
        moveOnBoard(Board.DEAD);
    }
    private void moveOnBoard(int val) {
        if (growFactor == 0) board.resetSquare(squares.removeLast());
        else growFactor--;
        board.setSquare(getHead(), val);
    }

    private void addSquareAhead() {
        Square head = squares.getFirst();
        switch (currentDir) {
            case LEFT:
                squares.addFirst(new Square(head.y, head.x - 1)); break;
            case RIGHT:
                squares.addFirst(new Square(head.y, head.x + 1)); break;
            case UP:
                squares.addFirst(new Square(head.y - 1, head.x)); break;
            case DOWN:
                squares.addFirst(new Square(head.y + 1, head.x)); break;
            default:
        }
    }

    private void updateDirections() {
        if (futureDir != Direction.NONE) {
            currentDir = futureDir;
            if (futureFutureDir != Direction.NONE) {
                futureDir = futureFutureDir;
                futureFutureDir = Direction.NONE;
            }
            else futureDir = Direction.NONE;
        }
    }

    public boolean wentOffBoard() {
        Square head = squares.getFirst();
        return (head.y == 0 || head.y == board.getHeight() - 1 ||
                head.x == 0 || head.x == board.getWidth() - 1);
    }

    public boolean ranIntoSnakeIgnoreTail(Snake snake) {
        Square head = this.squares.getFirst();
        for (Square square : snake.getSquares()) {
            if (head.overlaps(square) && square != head && 
                (square != snake.getSquares().getLast() || snake.getGrowFactor() != 0)) return true;
            // Note: Ignores the tail square of a snake (which has not been moved yet)
        }
        return false;
    }
    public boolean ranIntoSelfIgnoreTail() {
        return ranIntoSnakeIgnoreTail(this);
    }

    public boolean ateApple() {
        return (board.getValue(squares.getFirst()) == Board.APPLE);
    }
    
    private final int getGrowFactor() { return growFactor; }
    public final Deque<Square> getSquares() { return squares; }
    public final Status getStatus() { return status; }
    public final Square getHead() { return squares.getFirst(); }
    public final Direction getCurrentDirection() { return currentDir; }
    public final int getScore() { return score.get(); }
    public final Direction getFutureFutureDirection() { return futureFutureDir; }
    public final Direction getFutureDirection() { return futureDir; }
    public final ReadOnlyIntegerProperty scoreProperty() { return score; }

    public void setCurrentDirection(Direction currentDir ) { this.currentDir = currentDir; }
    public void setFutureDirection(Direction futureDir) { this.futureDir = futureDir; }
    public void setFutureFutureDirection(Direction futureFutureDir) { this.futureFutureDir = futureFutureDir; }
    
    public void setStatusToLIVE() { status = Status.LIVE; }
    public void setStatusToREADY() { status = Status.READY; }
    public void kill() { status = Status.DEAD; }
    public boolean isDead() { return (status == Status.DEAD); }
    public boolean isReady() { return (status == Status.READY); }
    public boolean isLive() { return (status == Status.LIVE); }
    public void incrGrowFactor(int incr) { growFactor += incr; }
    public void incrScore() { score.set(score.get() + 1); }

    @Override
    public String toString() {
        String s = "\n";
        s += String.format("Snake %d)\n", id);
        s += String.format("   squares = %s\n", squares);
        s += String.format("   currentDir = %s, futureDir = %s, futureFutureDir = %s\n", currentDir, futureDir, futureFutureDir);
        s += String.format("   growFactor = %s, status = %s, score = %s\n", growFactor, status, score.get());
        return s;
    }
}
